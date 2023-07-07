package cozy06.com.plugins

import com.cozy06.routes.httpProtocol
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.http.content.*
import io.ktor.server.application.*

fun Application.configureRouting() {

    routing {
        get("/") {
            call.respondText("Hello World!")
        }

        httpProtocol()

        static("/static") {
            resources("static")
        }
    }
}
