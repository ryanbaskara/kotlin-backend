package com.ryanbaskara.sample.usecase

import com.ryanbaskara.sample.domain.model.User
import com.ryanbaskara.sample.domain.repository.UserRepository
import io.vertx.core.Future

class UserUseCase(private val repository: UserRepository) {
    suspend fun getAllUsers(): List<User> = repository.getAll()
    suspend fun createUser(user: User): Boolean = repository.create(user)
    fun getUserByID(id: Int): Future<User?> {
        return repository.getUserById(id)
    }
}