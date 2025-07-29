package com.ryanbaskara.sample

import com.ryanbaskara.sample.delivery.http.handler.UserHandler
import com.ryanbaskara.sample.delivery.http.setupRouter
import com.ryanbaskara.sample.infrastructure.database.Database
import com.ryanbaskara.sample.infrastructure.database.UserRepositoryImpl
import com.ryanbaskara.sample.usecase.UserUseCase
import io.vertx.core.Vertx
import kotlinx.coroutines.runBlocking

fun main(args: Array<String>) {
    runBlocking {
        val vertx = Vertx.vertx()
        val dbClient = Database.connect(vertx)
//    Database.createTable(dbClient)

        val userRepo = UserRepositoryImpl(dbClient)
        val userUseCase = UserUseCase(userRepo)
        val userHandler = UserHandler(userUseCase)

        val router = setupRouter(vertx, userHandler)

        vertx.createHttpServer()
            .requestHandler(router)
            .listen(7725) { ar ->
                if (ar.succeeded()) {
                    println("✅ Server running at http://localhost:7725")
                } else {
                    println("❌ Failed to start server: ${ar.cause().message}")
                }
            }
    }
}