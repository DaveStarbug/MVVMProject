package com.star.programmingtechnologies.repository

import com.star.programmingtechnologies.data.api.UserApi
import com.star.programmingtechnologies.data.local.UserDao
import com.star.programmingtechnologies.data.model.User
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userApi: UserApi,
    private val userDao: UserDao
) {

    suspend fun getUsersFromApi(): List<User> = userApi.getUsers()

    suspend fun getMoreUsersFromApi(): List<User> = userApi.getMoreUsers()

    suspend fun getUsersWithError(): List<User> = userApi.getUsersWithError()

    suspend fun getUsersFromDb(): List<User> = userDao.getUsers()

    suspend fun insertUsersIntoDb(users: List<User>) = userDao.insertUsers(users)

}