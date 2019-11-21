package com.example.lifeshare_android.database.repository

import android.app.Application

import com.example.lifeshare_android.database.dao.UserDao
import com.example.lifeshare_android.database.database.RoomDatabase
import com.example.lifeshare_android.model.user.User

import io.reactivex.Completable
import io.reactivex.Single

class RoomRepository(application: Application) {

    private var userDao: UserDao

    init {
        val database = RoomDatabase.getInstance(application)!!
        userDao = database.userDao()
    }

    fun insertUser(entity: User): Completable {
        return userDao.insert(entity)
    }
    fun updateUser(entity: User): Completable {
        return userDao.update(entity)
    }
    fun deletUser(entity: User): Completable {
        return userDao.delete(entity)
    }
    fun getUser(id: String): Single<User> {
        return userDao.getUser(id)
    }
    fun getUserPhoneNumber(id: String): String {
        return userDao.getUserPhoneNumber(id)
    }
}
