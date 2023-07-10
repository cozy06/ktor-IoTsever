package com.cozy06.routes

//import com.cozy06.GPIO.GPIO
import cozy06.com.data.receive.*
import cozy06.com.routes.Act
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

val project_path = System.getProperty("user.dir")

fun Route.httpProtocol() {
    post(path = "/IoTList") {
        val responseData = Act().getIoTListData()
        when(responseData) {
            null -> call.respond(HttpStatusCode.BadRequest)
            else -> call.respond(HttpStatusCode.OK, responseData)
        }
    }

    post("/info") {
        val InfoRequest = call.receive<Info>()
        val responseData = Act().Info(InfoRequest.Name)
        when(responseData) {
            null -> call.respond(HttpStatusCode.BadRequest)
            else -> call.respond(HttpStatusCode.OK, responseData)
        }
    }

    post("/power") {
        //param 정보 읽음.
        val PowerRequest = call.receive<Power>()
        val responseData = Act().Power_ONOFF(PowerRequest.Name, PowerRequest.Power)
        when(responseData) {
            null -> call.respond(HttpStatusCode.BadRequest)
            else -> call.respond(HttpStatusCode.OK, responseData)
        }
    }
}