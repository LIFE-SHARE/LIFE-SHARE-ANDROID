package com.example.lifeshare_android.database.dao

import androidx.room.Dao
import androidx.room.Query

import com.example.lifeshare_android.base.BaseDao
import com.example.lifeshare_android.model.user.User

import io.reactivex.Single

@Dao
interface UserDao : BaseDao<User> {

    @Query("SELECT * FROM user_table WHERE id=:id")
    fun getUser(id: String?): Single<User>

    @Query("SELECT phoneNumber FROM user_table WHERE id=:id")
    fun getUserPhoneNumber(id: String?): String
}
