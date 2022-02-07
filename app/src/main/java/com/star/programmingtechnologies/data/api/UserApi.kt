package com.star.programmingtechnologies.data.api

import com.star.programmingtechnologies.data.model.User
import retrofit2.http.GET

interface UserApi {

    @GET("users")
    suspend fun getUsers() : List<User>

    @GET("more-users")
    suspend fun getMoreUsers() : List<User>

    @GET("error")
    suspend fun getUsersWithError() : List<User>

}