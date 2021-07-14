@file:Suppress("FunctionName")

package com.example.rentateamtestapp

import com.example.rentateamtestapp.presentation.details.DetailsFragment
import com.example.rentateamtestapp.presentation.info.InfoFragment
import com.example.rentateamtestapp.presentation.users.UsersFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {

    fun UsersScreen() = FragmentScreen { UsersFragment.newInstance() }
    fun DetailScreen(id: String, email: String, first_name: String, last_name: String, avatar: String) =
                    FragmentScreen { DetailsFragment.newInstance(id, email, first_name, last_name, avatar) }
    fun InfoScreen() = FragmentScreen { InfoFragment.newInstance() }

}