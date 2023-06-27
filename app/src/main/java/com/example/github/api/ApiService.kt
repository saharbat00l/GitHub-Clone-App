package com.example.github.api

import com.example.github.data.DetailUserResponse
import com.example.github.data.UserResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface ApiService {

    @GET("search/users")
    suspend fun getUsers(@Query("q") searchQuery: String)
    : UserResponse

    @GET("users/{username}")
    suspend fun getDetails(@Path("username") username:String)
    : DetailUserResponse

    @GET("users/{username}/followers")
    suspend fun getFollowers(@Path("followers") followers: String)
    : UserResponse.Item

    @GET("users/{username}/following")
    suspend fun getFollowing(@Path("following") following: String)
    : UserResponse.Item

}