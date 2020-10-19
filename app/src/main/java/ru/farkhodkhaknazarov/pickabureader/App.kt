package ru.farkhodkhaknazarov.pickabureader

import android.app.Application
import ru.farkhodkhaknazarov.pickabureader.data.room.RoomDataBaseImpl
import ru.farkhodkhaknazarov.pickabureader.di.component.AppComponent

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        AppComponent.init(this@App)
        RoomDataBaseImpl.init(this)
    }
}