package verticles

import io.vertx.config.ConfigRetriever
import io.vertx.config.ConfigRetrieverOptions
import io.vertx.config.ConfigStoreOptions
import io.vertx.core.AbstractVerticle
import io.vertx.core.DeploymentOptions
import io.vertx.core.Vertx
import io.vertx.core.json.JsonObject

public class MainVerticle extends AbstractVerticle {//
    public void start() {

        ConfigStoreOptions fileStore = new ConfigStoreOptions()
                .setType("file")
                .setConfig(new JsonObject().put("path", "config.json"))

        ConfigRetrieverOptions options = new ConfigRetrieverOptions()
                .addStore(fileStore)

//Vertx vertx = vertx

        ConfigRetriever retriever = ConfigRetriever.create(vertx, options)

        retriever.getConfig({ ar ->
            if (ar.failed()) {
                // Failed to retrieve the configuration
                println("The configuration file: config.json does not exist or in wrong format, start failed!")
                vertx.close()
            } else {
                JsonObject config = ar.result()

                DeploymentOptions webServerOptions = new DeploymentOptions().setConfig(config.getJsonObject("web"))
//        DeploymentOptions asyncClientOptions = new DeploymentOptions().setConfig(config.getJsonObject("db"))

                vertx.deployVerticle("kotlin:com.whitewoodcity.WebsiteVerticle", webServerOptions)
            }
        })

    }
}