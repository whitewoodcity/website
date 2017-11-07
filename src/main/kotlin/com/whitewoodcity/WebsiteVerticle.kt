package com.whitewoodcity

import io.vertx.core.AbstractVerticle
import io.vertx.ext.web.Router
import io.vertx.ext.web.handler.StaticHandler

class WebsiteVerticle : AbstractVerticle() {
    override fun start() {
        val server = vertx.createHttpServer()

        val router = Router.router(vertx)

        router.route().handler(StaticHandler.create())

        router.route().handler({ routingContext ->

//            // This handler will be called for every request
//            val response = routingContext.response()
//            response.putHeader("content-type", "text/plain")
//
//            // Write to the response and end it
//            response.end("Hello World from Vert.x-Web!")

            routingContext.next()
        })

        server.requestHandler({ router.accept(it) }).listen(config().getInteger("port"))

    }
}