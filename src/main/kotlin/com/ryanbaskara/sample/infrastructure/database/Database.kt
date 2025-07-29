package com.ryanbaskara.sample.infrastructure.database

import io.vertx.core.Vertx
import io.vertx.jdbcclient.JDBCPool
import io.vertx.sqlclient.PoolOptions
import io.vertx.core.json.JsonObject
import io.vertx.jdbcclient.JDBCConnectOptions

object Database {
    fun connect(vertx: Vertx): JDBCPool {
        val connectOptions = JDBCConnectOptions()
            .setJdbcUrl("jdbc:mysql://localhost:3306/testdb")
            .setUser("root")
            .setPassword("password")

        val poolOptions = PoolOptions().setMaxSize(5)

        return JDBCPool.pool(vertx, connectOptions, poolOptions)
    }

    fun createTable(client: JDBCPool) {
        client.query("""
            CREATE TABLE IF NOT EXISTS users (
                id INT AUTO_INCREMENT PRIMARY KEY,
                name VARCHAR(100),
                email VARCHAR(100)
            )
        """).execute()
    }
}