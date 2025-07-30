package com.ryanbaskara.sample.infrastructure.database

import com.ryanbaskara.sample.domain.model.User
import com.ryanbaskara.sample.domain.repository.UserRepository
import io.vertx.core.Future
import io.vertx.kotlin.coroutines.await
import io.vertx.sqlclient.Pool
import io.vertx.sqlclient.Row
import io.vertx.sqlclient.SqlClient
import io.vertx.sqlclient.Tuple

class UserRepositoryImpl(
    private val client: SqlClient
    ) : UserRepository {

    private fun rowToUser(row: Row): User = User(
        id = row.getInteger("id"),
        name = row.getString("name"),
        email = row.getString("email")
    )

    override suspend fun getAll(): List<User> {
        val result = client.query("SELECT * FROM users").execute().await()
        return result.map { rowToUser(it) }
    }

    override suspend fun create(user: User): Boolean {
        val result = client
            .preparedQuery("INSERT INTO users (name, email) VALUES (?, ?)")
            .execute(Tuple.of(user.name, user.email)).await()

        return result.rowCount() > 0
    }

    override fun getUserById(id: Int): Future<User?> {
        val query = "SELECT id, name, email FROM users WHERE id = ?"
        return client.preparedQuery(query)
            .execute(Tuple.of(id))
            .map { rowSet ->
                val row: Row? = rowSet.firstOrNull()
                row?.let {
                    rowToUser(it)
                }
            }
    }
}