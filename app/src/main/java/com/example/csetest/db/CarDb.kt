package com.example.csetest.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CarEntity::class], version = 1, exportSchema = false)
abstract class CarDb : RoomDatabase() {

    abstract fun carDao(): CarDao

    companion object {
        @Volatile
        private var instance: CarDb? = null

        fun getInstance(context: Context): CarDb {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context, CarDb::class.java, "cars.db")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build()
    }
}