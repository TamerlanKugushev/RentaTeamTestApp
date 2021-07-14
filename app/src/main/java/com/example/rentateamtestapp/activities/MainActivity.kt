package com.example.rentateamtestapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.rentateamtestapp.R
import com.example.rentateamtestapp.Screens
import com.example.rentateamtestapp.utils.BaseApplication
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val navigator = AppNavigator(this, R.id.fragmentContainer)
    private val navigatorHolder = BaseApplication.instance.navigatorHolder
    private val router: Router = BaseApplication.instance.router

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            router.newRootScreen(Screens.UsersScreen())
        }
        bottom_navigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.users -> {
                    router.newRootScreen(Screens.UsersScreen())
                    true
                }
                R.id.info -> {
                    router.newRootScreen(Screens.InfoScreen())
                    true
                }
            }
            true
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }
}