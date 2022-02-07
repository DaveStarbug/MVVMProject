package com.star.programmingtechnologies.ui.user

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.star.programmingtechnologies.data.model.User
import com.star.programmingtechnologies.data.util.Resource
import com.star.programmingtechnologies.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository
): ViewModel() {

    private val users = MutableLiveData<Resource<List<User>>>()

    init {
        fetchUsers()
    }

    private fun fetchUsers(){
        viewModelScope.launch {
            users.postValue(Resource.loading(null))

            try {
                val usersFromDb = userRepository.getUsersFromDb()
                if (usersFromDb.isEmpty()) {
                    val usersFromApi = userRepository.getUsersFromApi()
                    users.postValue(Resource.success(usersFromApi))
                } else {
                    users.postValue(Resource.success(usersFromDb))
                }
            }
            catch (e: Exception){
                users.postValue(Resource.error("Some Error Occured\n ${e.localizedMessage}", null))
            }
        }

    }

    fun getUsers() : MutableLiveData<Resource<List<User>>> = users

}