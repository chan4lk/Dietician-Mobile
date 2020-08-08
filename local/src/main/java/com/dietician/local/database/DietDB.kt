package com.dietician.local.database

import android.content.Context
import androidx.annotation.NonNull
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dietician.local.mapper.Converters
import com.dietician.local.model.PlanLocal
import com.dietician.local.model.TokenLocal

@Database(
    entities = [TokenLocal::class, PlanLocal::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class DietDB : RoomDatabase() {
    companion object {
        private val LOCK = Any()
        private const val DATABASE_NAME = "bank_buddy.db"

        @Volatile
        private var INSTANCE: DietDB? = null

        fun getInstance(@NonNull context: Context): DietDB {
            if (INSTANCE == null) {
                synchronized(LOCK) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context,
                            DietDB::class.java,
                            DATABASE_NAME
                        ).build()
                    }
                }
            }

            return INSTANCE!!
        }
    }

    abstract fun getTokenDao(): TokenDAO

    abstract fun getPlanDao(): PlanDAO
}