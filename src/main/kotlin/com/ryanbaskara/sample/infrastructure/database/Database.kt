package com.ryanbaskara.sample.infrastructure.database

import io.github.cdimascio.dotenv.dotenv
import io.vertx.core.Vertx
import io.vertx.jdbcclient.JDBCPool
import io.vertx.sqlclient.PoolOptions
import io.vertx.core.json.JsonObject
import io.vertx.jdbcclient.JDBCConnectOptions

object Database {
    fun connect(vertx: Vertx): JDBCPool {
        val env = dotenv()
        val host = env.get("DB_MYSQL_HOST")
        val port = env.get("DB_MYSQL_PORT")
        val username = env.get("DB_MYSQL_USERNAME")
        val password = env.get("DB_MYSQL_PASSWORD")
        val db = env.get("DB_MYSQL_DB")

        val connectOptions = JDBCConnectOptions()
            .setJdbcUrl("jdbc:mysql://${host}:${port}/${db}")
            .setUser(username)
            .setPassword(password)

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