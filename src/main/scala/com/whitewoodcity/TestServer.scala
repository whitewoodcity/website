package com.whitewoodcity

import io.vertx.lang.scala.ScalaVerticle
class TestServer extends ScalaVerticle {
  override def start(): Unit = {
    vertx
      .createHttpServer()
      .requestHandler(_.response()
        .putHeader("content-type", "text/plain")
        .end("Hello from Vert.x"))
      .listen(8080)
  }
}
