package com.example.github.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.github.api.Repository

class MainViewModelFactory(private val repository: Repository):ViewModelProvider.Factory {
    public override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }
}