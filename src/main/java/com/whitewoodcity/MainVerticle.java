package com.whitewoodcity;

import io.vertx.config.ConfigRetriever;
import io.vertx.config.ConfigRetrieverOptions;
import io.vertx.config.ConfigStoreOptions;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.json.JsonObject;

public class MainVerticle extends AbstractVerticle {
    public void start() {
//        vertx.deployVerticle("scala:com.whitewoodcity.TestServer");
//        vertx.deployVerticle("kotlin:com.whitewoodcity.WebsiteVerticle");
//        vertx.deployVerticle("verticles/test_server.groovy");


        ConfigStoreOptions fileStore = new ConfigStoreOptions()
                .setType("file")
                .setConfig(new JsonObject().put("path", "config.json"));

        ConfigRetrieverOptions options = new ConfigRetrieverOptions()
                .addStore(fileStore);

        ConfigRetriever retriever = ConfigRetriever.create(vertx, options);

        retriever.getConfig(ar -> {
            if (ar.failed()) {
                // Failed to retrieve the configuration
                System.out.println("The configuration file: config.json does not exist or in wrong format, start failed!");
                vertx.close();
            } else {
                JsonObject config = ar.result();

                DeploymentOptions webServerOptions = new DeploymentOptions().setConfig(config.getJsonObject("web"));

                vertx.deployVerticle("kotlin:com.whitewoodcity.WebsiteVerticle", webServerOptions);
            }
        });

    }
}