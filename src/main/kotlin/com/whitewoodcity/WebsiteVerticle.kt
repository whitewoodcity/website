package com.whitewoodcity

import io.vertx.core.AbstractVerticle
import io.vertx.ext.web.Router

class WebsiteVerticle : AbstractVerticle() {
    override fun start() {
        val server = vertx.createHttpServer()

        val router = Router.router(vertx)

        router.route().handler({ routingContext ->

            // This handler will be called for every request
            val response = routingContext.response()
            response.putHeader("content-type", "text/plain")

            // Write to the response and end it
            response.end("Hello World from Vert.x-Web!")
        })

        server.requestHandler({ router.accept(it) }).listen(8080)

    }
}