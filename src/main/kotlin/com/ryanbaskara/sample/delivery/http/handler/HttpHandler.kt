package com.ryanbaskara.sample.delivery.http.handler

import com.ryanbaskara.sample.domain.model.User
import com.ryanbaskara.sample.usecase.UserUseCase
import io.vertx.core.json.JsonObject
import io.vertx.ext.web.RoutingContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserHandler(private val useCase: UserUseCase) {

    fun health(ctx: RoutingContext) {
        ctx.response().setStatusCode(200).end("OK")
    }

    fun getAllUsers(ctx: RoutingContext) {
        CoroutineScope(Dispatchers.Default).launch {
            val users = useCase.getAllUsers()
            ctx.response()
                .putHeader("Content-Type", "application/json")
                .end(JsonObject.mapFrom(mapOf("data" to users)).encode())
        }
    }

    fun createUser(ctx: RoutingContext) {
        CoroutineScope(Dispatchers.Default).launch {
            val user = ctx.bodyAsJson.mapTo(User::class.java)
            val success = useCase.createUser(user)

            if (success) {
                ctx.response().setStatusCode(201).end("User created")
            } else {
                ctx.response().setStatusCode(500).end("Failed to create user")
            }
        }
    }
}
