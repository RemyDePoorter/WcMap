package com.android.example.wcmap.model.user

import androidx.lifecycle.LiveData

// Repository, allows to use the DAO.
class UserRepository(private val userDao: UserDao) {

    fun readAllEmails(): LiveData<List<String>> {
        return userDao.getAll()
    }

    fun addUser(user: User) {
        userDao.insert(user)
    }

    fun updateUser(user: User) {
        userDao.update(user)
    }
}