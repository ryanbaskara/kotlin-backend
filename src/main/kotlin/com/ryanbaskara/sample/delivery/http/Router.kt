package com.ryanbaskara.sample.delivery.http

import com.ryanbaskara.sample.delivery.http.handler.UserHandler
import io.vertx.core.Vertx
import io.vertx.ext.web.Router

fun setupRouter(vertx: Vertx, handler: UserHandler): Router {
    val router = Router.router(vertx)
    router.get("/health").handler(handler::health)
    router.get("/users").handler(handler::getAllUsers)
    router.post("/users").handler(handler::createUser)
    router.get("/users/:id").handler(handler::getUserById)
    return router
}