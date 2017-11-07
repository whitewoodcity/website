package com.whitewoodcity

import io.vertx.config.ConfigRetriever
import io.vertx.config.ConfigRetrieverOptions
import io.vertx.config.ConfigStoreOptions
import io.vertx.core.DeploymentOptions
import io.vertx.core.Vertx
import io.vertx.core.json.JsonObject
import io.vertx.ext.unit.Async
import io.vertx.ext.unit.TestContext
import io.vertx.ext.unit.junit.VertxUnitRunner
import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(VertxUnitRunner.class)
class WebsiteVerticleTest{
    static Vertx vertx = Vertx.vertx()

    @BeforeClass
    static void setup(TestContext context){
        Async async = context.async()

        ConfigStoreOptions fileStore = new ConfigStoreOptions()
                .setType("file")
                .setConfig(new JsonObject().put("path", "config.json"))

        ConfigRetrieverOptions options = new ConfigRetrieverOptions()
                .addStore(fileStore)

        ConfigRetriever retriever = ConfigRetriever.create(vertx, options)

        retriever.getConfig(context.<Map>asyncAssertSuccess{ jsonObject ->
            DeploymentOptions deploymentOptions = new DeploymentOptions().setConfig(new JsonObject(jsonObject.get("web")))
            vertx.deployVerticle(WebsiteVerticle.class.getName(),deploymentOptions, context.asyncAssertSuccess{_ -> async.complete()})
        })
    }

    @AfterClass
    static void teardown(TestContext context){
        vertx.close(context.asyncAssertSuccess())
    }

    @Test
    void test(TestContext context){

        Async async = context.async()
        vertx.createHttpClient().getNow(8080, "localhost", "/") {response ->
            response.handler{body ->
                context.assertTrue(body.toString().contains("白木城"))
                async.complete()
            }
        }
    }
}

