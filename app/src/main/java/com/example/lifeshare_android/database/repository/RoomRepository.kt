package com.example.lifeshare_android.database.repository

import android.app.Application

import com.example.lifeshare_android.database.dao.UserDao
import com.example.lifeshare_android.database.database.RoomDatabase
import com.example.lifeshare_android.model.user.Member

import io.reactivex.Completable
import io.reactivex.Single

class RoomRepository(application: Application) {

    private var userDao: UserDao

    init {
        val database = RoomDatabase.getInstance(application)!!
        userDao = database.userDao()
    }

    fun insertUser(entity: Member): Completable {
        return userDao.insert(entity)
    }
    fun updateUser(entity: Member): Completable {
        return userDao.update(entity)
    }
    fun deletUser(entity: Member): Completable {
        return userDao.delete(entity)
    }
    fun getUser(id: String): Single<Member> {
        return userDao.getUser(id)
    }
    fun getUserPhoneNumber(id: String): String {
        return userDao.getUserPhoneNumber(id)
    }
}
