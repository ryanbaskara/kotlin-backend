package com.ryanbaskara.sample.infrastructure.database

import io.github.cdimascio.dotenv.dotenv
import io.vertx.core.Vertx
import io.vertx.sqlclient.PoolOptions
import io.vertx.mysqlclient.MySQLConnectOptions
import io.vertx.sqlclient.Pool

object Database {
    fun connect(vertx: Vertx): Pool {
        val env = dotenv()
        val host = env.get("DB_MYSQL_HOST")
        val port = env.get("DB_MYSQL_PORT")
        val username = env.get("DB_MYSQL_USERNAME")
        val password = env.get("DB_MYSQL_PASSWORD")
        val db = env.get("DB_MYSQL_DATABASE")

        val connectOptions = MySQLConnectOptions()
            .setPort(port.toInt())
            .setHost(host)
            .setDatabase(db)
            .setUser(username)
            .setPassword(password)

        val poolOptions = PoolOptions().setMaxSize(5)

        return Pool.pool(vertx, connectOptions, poolOptions)
    }
}