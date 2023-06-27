package com.example.github.ui.main

import com.example.github.data.UserResponse

interface ClickListener  {
    fun onClick(index: Int, user : UserResponse.Item)
}