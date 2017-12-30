package org.densebrain.sls.hotjava

import io.javalin.Javalin
import org.slf4j.LoggerFactory

class InterceptServer {

  companion object {
    private val log = LoggerFactory.getLogger(InterceptServer::class.java)
  }

  init {
    val app = Javalin.start(8000)

    app.before { ctx ->
      val request = ctx.request()

      val uri = request.requestURI
      val method = request.method

      log.info("Received ${method} for ${uri}")

      val res = ctx.response()
      res.status = 200
      res.outputStream.use {
        it.write("thx".toByteArray())
      }

      //ctx.result("thx")
    }


  }
}