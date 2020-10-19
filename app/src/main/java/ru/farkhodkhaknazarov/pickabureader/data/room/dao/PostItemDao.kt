package ru.farkhodkhaknazarov.pickabureader.data.room.dao

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Single
import ru.farkhodkhaknazarov.pickabureader.api.model.PostItem

@Dao
interface PostItemDao {
    @Query("SELECT id, title, body, images, 1 as saved FROM posts")
    fun allRepos(): Single<List<PostItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPostItem(item: PostItem): Completable

    @Delete
    fun deletePostItem(item: PostItem): Completable

    @Query("DELETE FROM posts")
    fun deleteAllRepos(): Completable

    @Query("SELECT * FROM posts WHERE id= :id")
    fun getrepoById(id: Int): Single<PostItem>

}