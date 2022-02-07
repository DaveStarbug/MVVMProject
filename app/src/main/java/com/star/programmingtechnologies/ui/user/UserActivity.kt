package com.star.programmingtechnologies.ui.user

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.star.programmingtechnologies.R
import com.star.programmingtechnologies.data.model.User
import com.star.programmingtechnologies.data.util.Status
import com.star.programmingtechnologies.data.util.ViewModelFactory
import com.star.programmingtechnologies.ui.adapter.UserAdapter
import kotlinx.android.synthetic.main.activity_user.*
import javax.inject.Inject

class UserActivity: AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var userViewModel : UserViewModel
    private lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        setupUI()
        setupViewModel()
        setupObserver()

    }

    private fun setupUI() {
        rvUsers.layoutManager = LinearLayoutManager(this)
        userAdapter =
            UserAdapter(
                mutableListOf()
            )
        rvUsers.addItemDecoration(
            DividerItemDecoration(
                rvUsers.context,
                (rvUsers.layoutManager as LinearLayoutManager).orientation
            )
        )
        rvUsers.adapter = userAdapter
    }

    private fun setupObserver() {
        userViewModel.getUsers().observe(this) {
            when (it.status) {
                Status.SUCCESS -> {
                    pbLoading.visibility = View.GONE
                    it.data?.let { users -> renderListUser(users) }
                    rvUsers.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    pbLoading.visibility = View.VISIBLE
                    rvUsers.visibility = View.GONE
                }
                Status.ERROR -> {
                    pbLoading.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()

                }
            }
        }
    }

    fun setupViewModel(){
        val factory = viewModelFactory
        userViewModel = ViewModelProvider(this,factory).get(UserViewModel::class.java)
//        userViewModel = UserViewModel(
//            ApiModule.providesRepository(
//            ApiModule.provideApiService(
//                ApiModule.provideRetrofit(
//                    ApiModule.providesOkHttpClient(
//                        ApiModule.providesHttpLoggingInterceptor()
//                    )
//                )
//            )))
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun renderListUser(users: List<User>) {
        userAdapter.addData(users)
        userAdapter.notifyDataSetChanged()
    }


}