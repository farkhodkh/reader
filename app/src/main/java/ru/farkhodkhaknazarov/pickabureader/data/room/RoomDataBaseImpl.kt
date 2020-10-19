package ru.farkhodkhaknazarov.pickabureader.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.farkhodkhaknazarov.pickabureader.api.model.PostItem
import ru.farkhodkhaknazarov.pickabureader.data.room.dao.PostItemDao

@Database(entities = arrayOf(PostItem::class), version = 1, exportSchema = false)
@TypeConverters(TypeConverter::class)
abstract class RoomDataBaseImpl : RoomDatabase() {
    abstract fun reposDao(): PostItemDao
    companion object {
        lateinit var instance: RoomDataBaseImpl

        fun init(context: Context) {
            if (!::instance.isInitialized) {
                instance = Room.databaseBuilder(
                    context,
                    RoomDataBaseImpl::class.java,
                    "roomdb"
                )
                    .fallbackToDestructiveMigration()
                    .build()
            }
        }
    }
}