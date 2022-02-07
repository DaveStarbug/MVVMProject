package com.star.programmingtechnologies.data.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.star.programmingtechnologies.repository.UserRepository
import com.star.programmingtechnologies.ui.user.UserViewModel
import javax.inject.Inject

class ViewModelFactory @Inject constructor(val userRepository: UserRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return UserViewModel(userRepository) as T
    }
}