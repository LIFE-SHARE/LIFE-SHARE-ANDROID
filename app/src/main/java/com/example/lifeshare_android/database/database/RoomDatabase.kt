package com.example.lifeshare_android.database.database

import android.content.Context

import androidx.room.Database
import androidx.room.Room

import com.example.lifeshare_android.database.dao.UserDao
import com.example.lifeshare_android.model.user.User

@Database(entities = [User::class], version = 2, exportSchema = false)
abstract class RoomDatabase : androidx.room.RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {

        private var instance: RoomDatabase? = null

        @Synchronized
        fun getInstance(context: Context): RoomDatabase? {
            if (instance == null) {
                instance = Room.databaseBuilder(context.applicationContext,
                        RoomDatabase::class.java, "livetogether_database")
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build()
            }
            return instance
        }
    }
}
