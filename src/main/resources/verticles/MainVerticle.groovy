package verticles

import io.vertx.core.Vertx

Vertx vertx = vertx

//vertx.deployVerticle("scala:com.whitewoodcity.TestServer")
vertx.deployVerticle("kotlin:com.whitewoodcity.WebsiteVerticle")