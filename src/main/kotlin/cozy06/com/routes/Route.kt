package com.cozy06.routes

import com.cozy06.GPIO.GPIO
import com.cozy06.data.IoTList
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.json.JSONObject
import java.io.BufferedReader
import java.io.File
import java.io.FileReader

val project_path = System.getProperty("user.dir")

fun Route.httpProtocol() {
    post(path = "/IoTList") {
        val ListData = getIoTListData()
        if (ListData == null) {
            // 해당 객체가 없을경우 400 에러 응답
            call.respond(HttpStatusCode.BadRequest)
        } else {
            // 해당 객체 응답.
            call.respond(HttpStatusCode.OK, ListData)
        }
    }

    post("/power") {
        //param 정보 읽음.
        val ThingsClickRequest = call.receive<ThingsClickRequest>()
        val ThingsClickData = ThingsClick(ThingsClickRequest.product_name)
        if (ThingsClickData == null) {
            // 해당 객체가 없을경우 400 에러 응답
            call.respond(HttpStatusCode.BadRequest)
        } else {
            // 해당 객체 응답.
            call.respond(HttpStatusCode.OK, ThingsClickData)
        }
    }

    post("/power") {
        //param 정보 읽음.
        val PowerRequest = call.receive<PowerRequest>()
        val PowerData = Power_ONOFF(PowerRequest.product_name, PowerRequest.power)
        if (PowerData == null) {
            // 해당 객체가 없을경우 400 에러 응답
            call.respond(HttpStatusCode.BadRequest)
        } else {
            // 해당 객체 응답.
            call.respond(HttpStatusCode.OK, PowerData)
        }
    }

}

private fun getIoTListData(): Any {

    val folder = File("${project_path}/IoT_List")
    val files = folder.listFiles()
    for (i in files.indices) {
        System.out.println(files[i])
    }

    return when (folder.exists() and (files.isNotEmpty())) {
        true -> {
            IoTList("${files.joinToString("|")}")
        }
        false -> IoTList("Nothing")
    }
}

private fun ThingsClick(name: String): Any {

    val path = "${project_path}/IoT_List/${name}.json"

    val jsonString = FileReader(File(path))
    val buffer = BufferedReader(jsonString)
    val temp = buffer.readLine()
    buffer.close()
    println(temp.toString())

    val jsonObject = JSONObject(temp.toString())

    return jsonObject.getString("ID")
}

private fun Power_ONOFF(name: String, power: String): Any {

    val path = "${project_path}/IoT_List/${name}.json"

    val jsonString = FileReader(File(path))
    val buffer = BufferedReader(jsonString)
    val temp = buffer.readLine()
    buffer.close()

    val jsonObject = JSONObject(temp.toString())
    val Pin = jsonObject.getString("PIN").toInt()

    when(power){
        "ON" -> GPIO().control(Pin, true)
        "OFF" -> GPIO().control(Pin, false)
    }
    println("---------------------------\n$power\n----------------------------")

    return "True"
}
