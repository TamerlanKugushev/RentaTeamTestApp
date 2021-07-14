package com.example.rentateamtestapp.domain

import com.example.rentateamtestapp.data.User
import com.example.rentateamtestapp.data.repository.UsersRepository
import io.reactivex.Flowable
import io.reactivex.Single

class UsersInteractor {

    fun getUsers(): Flowable<List<User>> {
        return UsersRepository.getUsers()
    }
}