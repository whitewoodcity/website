package com.whitewoodcity;

import io.vertx.core.AbstractVerticle;

public class MainVerticle extends AbstractVerticle {
    public void start() {
        System.out.println(111);
//        vertx.deployVerticle(MyFirstVerticle.class.getName());
    }
}