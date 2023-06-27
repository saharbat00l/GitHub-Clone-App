package com.example.github.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.github.R
import com.example.github.api.Repository
import com.example.github.data.UserResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository): ViewModel() {

    private var _selectedIndex = 0
    val selectedIndex = _selectedIndex

    private var _selectedUser = UserResponse.Item()
//    val selectedGitUser = _selectedUser

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getUsers("Android")
        }
    }

    fun searchUser(value: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getUsers(value)
        }
    }

    val users: LiveData<UserResponse>
    get() = repository.users

    fun setSelectedIndex(index: Int) {
        _selectedIndex = index
    }

    fun setSelectedUser(user: UserResponse.Item) {
        _selectedUser = user
    }

    fun getSelectedUser(): UserResponse.Item? {
        return _selectedUser
    }
    //for index
//    fun getSelectedUser(): UserResponse.Item? {
//        return users.value?.items?.get(_selectedIndex)
//    }
}