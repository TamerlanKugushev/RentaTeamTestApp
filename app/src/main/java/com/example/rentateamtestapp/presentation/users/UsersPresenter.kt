package com.example.rentateamtestapp.presentation.users

import android.util.Log
import com.example.rentateamtestapp.Screens
import com.example.rentateamtestapp.data.User
import com.example.rentateamtestapp.domain.UsersInteractor
import com.example.rentateamtestapp.utils.BaseApplication
import com.example.rentateamtestapp.utils.BasePresenter
import com.github.terrakok.cicerone.Router
import com.jakewharton.rxrelay2.BehaviorRelay
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class UsersPresenter() : BasePresenter<UsersView>() {

    private val usersInteractor = UsersInteractor()
    private val router: Router = BaseApplication.instance.router
    private val usersSubject = BehaviorRelay.create<List<User>>()
    private var usersScreenStates = UsersScreenStates.START

    init {
        loadAllUsers()
    }

    override fun bindView(view: UsersView) {
        super.bindView(view)
        subscribeUsers()
    }

    fun navigateToDetailsScreen(
        id: String,
        email: String,
        first_name: String,
        last_name: String,
        avatar: String
    ) {
        router.navigateTo(Screens.DetailScreen(id, email, first_name, last_name, avatar))
    }

    private fun loadAllUsers() {
        usersInteractor.getUsers()
            .subscribeOn(Schedulers.io())
            .subscribeBy(
                onNext = {
                    usersSubject.accept(it)
                },
                onError = {
                    if (usersSubject.value.orEmpty().isEmpty()) {
                        usersScreenStates = UsersScreenStates.ERROR
                        getView()?.updateState(usersScreenStates)
                    }
                    Log.e("USERS", it.message.toString())
                }
            ).addTo(dataCompositeDisposable)
    }

    private fun subscribeUsers() {
        usersSubject.hide()
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                usersScreenStates = UsersScreenStates.LOADING
                getView()?.updateState(usersScreenStates)
            }
            .subscribeBy(
                onNext = {
                    usersScreenStates = UsersScreenStates.CONTENT
                    getView()?.updateState(usersScreenStates)
                    getView()?.updateUsers(it)
                },
                onError = {
                    Log.e("USERS", it.message.toString())
                }
            ).addTo(viewCompositeDisposable)
    }

}