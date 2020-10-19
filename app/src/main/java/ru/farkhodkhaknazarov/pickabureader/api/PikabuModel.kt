package ru.farkhodkhaknazarov.pickabureader.api

import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import org.jetbrains.annotations.NotNull
import ru.farkhodkhaknazarov.pickabureader.api.model.PostItem
import ru.farkhodkhaknazarov.pickabureader.api.pickabu.PikabuServiceImpl
import ru.farkhodkhaknazarov.pickabureader.common.warning
import ru.farkhodkhaknazarov.pickabureader.data.room.RoomDataBaseImpl

object PikabuModel: IPikabuModel {

    private val savedPostList = BehaviorSubject.create<List<PostItem>>()
    private val postList = BehaviorSubject.create<List<PostItem>>()

    override fun observePostsDataList(): Observable<List<PostItem>> = postList

    override fun observeSavedPostsDataList(): Observable<List<PostItem>> = savedPostList

    var dataBase: RoomDataBaseImpl

    init {
        this.dataBase = RoomDataBaseImpl.instance
    }

    fun getPostsFromNetwork() {
        Single.zip(
            PikabuServiceImpl.getApiService().getPosts(),
            dataBase.reposDao().allRepos(),
            BiFunction { repo1: List<PostItem>, repo2: List<PostItem> ->
                return@BiFunction Pair(repo1, repo2)
            }
        )
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    updatePostList(it.first, it.second)
                }
            ) { warning(it) }
    }

    fun updatePostList(networkList: List<PostItem>, dataBaseList: List<PostItem>) {
        networkList.forEach {
            if (dataBaseList.indexOf(it) >= 0) {
                it.saved = true
            }
        }
        if (networkList.size == 0) {
            val item = PostItem(0, "", "Не удалось скачать посты", emptyList())
            postList.onNext(networkList.plus(item))
        } else {
            postList.onNext(networkList)
        }
    }

    fun updateSavedPosts() {
        val observable = dataBase.reposDao().allRepos()

        observable.subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { responseData: List<PostItem> ->
                if (responseData.size == 0) {
                    val item = PostItem(0, "", "Сохраненные посты не обнаружены", emptyList())
                    savedPostList.onNext(responseData.plus(item))
                } else {
                    savedPostList.onNext(responseData)
                }
            }
    }

    fun savePostItem(@NotNull item: PostItem) {
        when (item.saved) {
            false -> {
                try {
                    dataBase.reposDao()
                        .insertPostItem(item)
                        .subscribeOn(Schedulers.io())
                        .subscribe({
                            updateSavedPosts()
                            //TODO - не самое лучшее решение, заплатка
                            getPostsFromNetwork()
                        })
                } catch (ex: Exception) {
                    warning(ex)
                }
            }
            true -> {
                try {
                    dataBase.reposDao()
                        .deletePostItem(item)
                        .subscribeOn(Schedulers.io())
                        .subscribe({
                            updateSavedPosts()
                            //TODO - не самое лучшее решение, заплатка
                            getPostsFromNetwork() })
                } catch (ex: Exception) {
                    warning(ex)
                }
            }
        }

    }

    fun closeModel() {
        dataBase.close()
    }
}