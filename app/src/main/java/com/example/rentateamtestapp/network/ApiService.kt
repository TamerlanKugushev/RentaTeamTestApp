package com.example.rentateamtestapp.network

import com.example.rentateamtestapp.data.ResultReviews
import io.reactivex.Flowable
import io.reactivex.Single
import retrofit2.http.GET

interface ApiService {

    companion object {
        private const val USERS = "users"
    }

    @GET(USERS)
    fun getUsers(): Single<ResultReviews>

}