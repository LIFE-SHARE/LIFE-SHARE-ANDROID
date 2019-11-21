package com.example.lifeshare_android.model.user

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class Member(@field:PrimaryKey
                  var id: String,
                  var name: String?,
                  var phone_number: String?,
                  var gender: Int?,
                  var age: Int?, // TODO 여자 0, 남자 1
                  var auth: Int?, // TODO : 집 주인 0, 손님 1
                  var join_date: String?)