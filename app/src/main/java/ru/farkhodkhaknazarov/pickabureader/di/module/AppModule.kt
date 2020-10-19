package ru.farkhodkhaknazarov.pickabureader.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(context: Context)  {
    private val context = context.applicationContext

    @Singleton
    @Provides
    fun providesAppContext(): Context = context
}