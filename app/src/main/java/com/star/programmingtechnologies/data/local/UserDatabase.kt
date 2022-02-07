package com.star.programmingtechnologies.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.star.programmingtechnologies.data.model.User
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject
import javax.inject.Provider

@Database(entities = [User::class], version = 1)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userDao() : UserDao

    class Callback @Inject constructor(
        private val database : Provider<UserDatabase>,
        private val applicationScope : CoroutineScope
    ): RoomDatabase.Callback()
}