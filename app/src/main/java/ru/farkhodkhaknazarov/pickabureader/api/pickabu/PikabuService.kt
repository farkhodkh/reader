package ru.farkhodkhaknazarov.pickabureader.api.pickabu

import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import ru.farkhodkhaknazarov.pickabureader.api.model.PostItem

interface PikabuService {
    @GET("/page/interview/mobile-app/test-api/feed.php")
    fun getPosts(): Single<List<PostItem>>

    @GET("https://pikabu.ru/page/interview/mobile-app/test-api/story.php")
    fun getPostById(@Query("id") id : Int): Observable<PostItem>
}