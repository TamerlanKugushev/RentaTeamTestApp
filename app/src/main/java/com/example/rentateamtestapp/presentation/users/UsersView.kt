package com.example.rentateamtestapp.presentation.users

import com.example.rentateamtestapp.data.User
import com.example.rentateamtestapp.utils.BaseView

interface UsersView : BaseView {

    fun updateUsers(users: List<User>)

    fun updateState(usersScreenStates: UsersScreenStates)
}