package com.whitewoodcity;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;

public class MainVerticle extends AbstractVerticle{//
    public void start() {
//        vertx.deployVerticle("scala:com.whitewoodcity.TestServer");
//        vertx.deployVerticle("kotlin:com.whitewoodcity.WebsiteVerticle");
        vertx.deployVerticle("verticles/test_server.groovy");
    }
}