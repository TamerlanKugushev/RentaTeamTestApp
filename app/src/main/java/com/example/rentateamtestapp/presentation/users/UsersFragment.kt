package com.example.rentateamtestapp.presentation.users

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.setFragmentResult
import com.example.rentateamtestapp.R
import com.example.rentateamtestapp.adapters.UsersAdapter
import com.example.rentateamtestapp.data.User
import com.example.rentateamtestapp.utils.BaseApplication
import com.example.rentateamtestapp.utils.BaseFragment
import com.example.rentateamtestapp.utils.PresentersStorage
import kotlinx.android.synthetic.main.fragment_users.*

class UsersFragment : BaseFragment(), UsersView {

    companion object {
        fun newInstance(): UsersFragment {
            return UsersFragment()
        }
    }

    private lateinit var presenter: UsersPresenter
    private lateinit var usersAdapter: UsersAdapter

    override fun attachPresenter() {
        val presenter = PresentersStorage.getPresenter(viewId)
        if (presenter !is UsersPresenter) {
            this.presenter = UsersPresenter()
            return
        }
        this.presenter = presenter
    }

    override fun getPresenter(): UsersPresenter {
        return presenter
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_users, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        usersAdapter = UsersAdapter(presenter::navigateToDetailsScreen)
        usersRecyclerView.setHasFixedSize(true)
        usersRecyclerView.adapter = usersAdapter
    }

    override fun onStart() {
        super.onStart()
        presenter.bindView(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.unbindView()
    }

    override fun updateUsers(users: List<User>) {
        val listCopy = users.toMutableList().map { it.copy() }
        usersAdapter.submitList(listCopy)
    }

    @SuppressLint("ShowToast")
    override fun updateState(usersScreenStates: UsersScreenStates) {
        usersRecyclerView.visibility = View.GONE
        progressBar.visibility = View.GONE
        when (usersScreenStates) {
            UsersScreenStates.START -> {
                usersRecyclerView.visibility = View.GONE
                progressBar.visibility = View.GONE
            }
            UsersScreenStates.LOADING -> {
                usersRecyclerView.visibility = View.GONE
                progressBar.visibility = View.VISIBLE
            }
            UsersScreenStates.CONTENT ->{
                usersRecyclerView.visibility = View.VISIBLE
                progressBar.visibility = View.GONE
            }
            UsersScreenStates.ERROR -> {
                Toast.makeText(requireActivity(),"Проверьте интернет-соединение",Toast.LENGTH_LONG)
            }
        }
    }
}