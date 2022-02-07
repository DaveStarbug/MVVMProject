package com.star.programmingtechnologies.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.star.programmingtechnologies.data.model.User

@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    suspend fun getUsers() : List<User>

    @Insert
    suspend fun insertUsers(users: List<User>)

    @Delete
    suspend fun deleteUser(user: User)

}