package com.example.github.api

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.github.data.DetailUserResponse
import com.example.github.data.UserResponse
import kotlinx.coroutines.delay
import retrofit2.http.Query

class Repository(private val apiService: ApiService) {

    private val usersLiveData = MutableLiveData<UserResponse>()
    // error on initializing with ToDo()
//    private val followersLiveData = MutableLiveData<DetailUserResponse>()
//    private val followingLiveData = MutableLiveData<DetailUserResponse>()


    val users: LiveData<UserResponse>
    get() = usersLiveData

    suspend fun getUsers(searchQuery: String){
        val result = apiService.getUsers(searchQuery)
        if (result.incomplete_results != null){
            usersLiveData.postValue(result)
        }
        else {
            Log.d("github","No Users Found.")
        }
    }



//    suspend fun getFollowing(following: String){
//        val result = apiService.getFollowing(following)
//        if (result.incomplete_results != null){
//            usersLiveData.postValue(result)
//        }
//        else {
//            Log.d("github","No Users Found.")
//        }
//    }

//    suspend fun getFollowers(followers: String){
//        val result = apiService.getFollowers(followers)
//        if (result.incomplete_results != null){
//            usersLiveData.postValue(result)
//        }
//        else {
//            Log.d("github","No Users Found.")
//        }
//    }
}