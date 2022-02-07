package com.star.programmingtechnologies.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class User(
    @SerializedName("id")
    @PrimaryKey val id : Int,
    @SerializedName("name")
    @ColumnInfo(name = "name")
    val name : String?,
    @SerializedName("email")
    @ColumnInfo(name = "email")
    val email : String?,
    @SerializedName("avatar")
    @ColumnInfo(name = "avatar")
    val avatar : String
)
