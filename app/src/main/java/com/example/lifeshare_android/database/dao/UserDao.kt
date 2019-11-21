package com.example.lifeshare_android.database.dao

import androidx.room.Dao
import androidx.room.Query

import com.example.lifeshare_android.base.BaseDao
import com.example.lifeshare_android.model.user.Member

import io.reactivex.Single

@Dao
interface UserDao : BaseDao<Member> {

    @Query("SELECT * FROM user_table WHERE id=:id")
    fun getUser(id: String?): Single<Member>
}
