package ru.farkhodkhaknazarov.pickabureader.di.module

import dagger.Module
import dagger.Provides
import ru.farkhodkhaknazarov.pickabureader.di.scope.PerPikabu
import ru.farkhodkhaknazarov.pickabureader.api.PikabuModel

@Module
class PikabuModule {
    @PerPikabu
    @Provides
    fun providesAppContext(): PikabuModel = PikabuModel
}