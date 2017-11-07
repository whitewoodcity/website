$vertx.create_http_server().request_handler() { |req|
  req.response()
    .put_header("content-type", "text/plain")
    .end("Hello from Vert.x!")
}.listen(8080)