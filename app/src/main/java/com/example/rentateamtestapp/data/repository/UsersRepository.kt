package com.example.rentateamtestapp.data.repository

import android.annotation.SuppressLint
import com.example.rentateamtestapp.data.User
import com.example.rentateamtestapp.db.UsersDatabase
import com.example.rentateamtestapp.network.RetrofitHolder
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single
import io.reactivex.rxkotlin.Flowables

object UsersRepository {

    private val apiService = RetrofitHolder.apiService
    private val usersDao = UsersDatabase.getDatabase().usersDao()

    fun getUsers(): Flowable<List<User>> {
        val databaseFlowable = getUsersFromData().toFlowable()
        val apiFlowable = getUsersFromApiAndSave().toFlowable()
        return Flowable.concat(databaseFlowable, apiFlowable)
    }

    private fun getUsersFromData(): Maybe<List<User>> {
        return usersDao.getAllUsers()
    }

    private fun getUsersFromApiAndSave(): Single<List<User>> {
        return getUsersFromApi()
            .flatMap(::saveUsersInDatabase)
    }

    private fun saveUsersInDatabase(users: List<User>): Single<List<User>> {
        return usersDao
            .insertUsers(users)
            .toSingle { users }
    }

    private fun getUsersFromApi(): Single<List<User>> {
        return apiService
            .getUsers()
            .map { it.data }
    }
}