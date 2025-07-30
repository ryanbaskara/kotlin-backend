package com.ryanbaskara.sample.domain.repository

import com.ryanbaskara.sample.domain.model.User
import io.vertx.core.Future

interface UserRepository {
    suspend fun getAll(): List<User>
    suspend fun create(user: User): Boolean

    fun getUserById(id: Int): Future<User?>
}