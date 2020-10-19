package ru.farkhodkhaknazarov.pickabureader.di.api

import android.content.Context
import ru.farkhodkhaknazarov.pickabureader.App

interface ApplicationApi {
    fun inject(app: App)
    fun context(): Context
}