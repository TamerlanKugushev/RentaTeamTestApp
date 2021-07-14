package com.example.rentateamtestapp.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.rentateamtestapp.data.User
import com.example.rentateamtestapp.utils.BaseApplication

@Database(entities = arrayOf(User::class), version = 1)
abstract class UsersDatabase : RoomDatabase() {

    abstract fun usersDao(): UsersDao

    companion object {
        private var INSTANCE: UsersDatabase? = null

        fun getDatabase(): UsersDatabase {
            val tempInstance = INSTANCE
            return if (tempInstance != null) {
                tempInstance
            } else {
                val instance = Room.databaseBuilder(
                    BaseApplication.instance.applicationContext,
                    UsersDatabase::class.java,
                    "user_database"
                ).build()
                INSTANCE = instance
                instance

            }
        }
    }

}
