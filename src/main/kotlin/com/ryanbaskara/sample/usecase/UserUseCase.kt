package com.ryanbaskara.sample.usecase

import com.ryanbaskara.sample.domain.model.User
import com.ryanbaskara.sample.domain.repository.UserRepository

class UserUseCase(private val repository: UserRepository) {
    suspend fun getAllUsers(): List<User> = repository.getAll()
    suspend fun createUser(user: User): Boolean = repository.create(user)
}