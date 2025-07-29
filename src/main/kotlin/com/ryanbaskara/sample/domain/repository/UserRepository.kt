package com.ryanbaskara.sample.domain.repository

import com.ryanbaskara.sample.domain.model.User

interface UserRepository {
    suspend fun getAll(): List<User>
    suspend fun create(user: User): Boolean
}