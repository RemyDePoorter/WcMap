package com.android.example.wcmap.viewModel

import android.app.Application
import android.util.Patterns
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.android.example.wcmap.model.user.User
import com.android.example.wcmap.model.user.UserDatabase
import com.android.example.wcmap.model.user.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ConnexionViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: UserRepository
    private var _listOfEmail: LiveData<List<String>>

    val listOfEmail: LiveData<List<String>>
        get() = _listOfEmail

    init {
        val userDao = UserDatabase.getInstance(application).userDao()
        repository = UserRepository(userDao)
        _listOfEmail = repository.readAllEmails()
    }

    fun contains(email: String): Boolean {
        if (_listOfEmail.value?.contains(email) != null) {
            return true
        }
        return false
    }

    fun checkEmail(editText: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(editText).matches()
    }

    fun addUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)
        }
    }

    fun updateUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateUser(user)
        }
    }
}