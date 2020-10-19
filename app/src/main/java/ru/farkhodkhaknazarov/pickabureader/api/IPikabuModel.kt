package ru.farkhodkhaknazarov.pickabureader.api

import io.reactivex.Observable
import ru.farkhodkhaknazarov.pickabureader.api.model.PostItem

interface IPikabuModel {
    fun observePostsDataList(): Observable<List<PostItem>>
    fun observeSavedPostsDataList(): Observable<List<PostItem>>
}