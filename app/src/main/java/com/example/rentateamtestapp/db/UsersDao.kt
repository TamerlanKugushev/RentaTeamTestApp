package com.example.rentateamtestapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rentateamtestapp.data.User
import io.reactivex.*

@Dao
interface UsersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUsers(users: List<User>): Completable

    @Query("SELECT * FROM user_table")
    fun getAllUsers(): Maybe<List<User>>

}