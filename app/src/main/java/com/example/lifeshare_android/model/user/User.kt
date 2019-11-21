package com.example.lifeshare_android.model.user

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User(@field:PrimaryKey
                var id: String,
                var name: String?,
                var phoneNumber: String?)
