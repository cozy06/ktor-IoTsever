package cozy06.com.routes

import cozy06.com.data.send.IoTList
import com.cozy06.routes.project_path
import com.github.cozy06.File.Companion.readFile
import com.github.cozy06.File.Companion.toJson
import cozy06.com.data.device.Python
import cozy06.com.data.send.Info
import java.io.File


class Act {
    fun getIoTListData(): Any {
        val folder = File("$project_path/IoT_List")
        val files = folder.listFiles()

        return when (folder.exists() && (files.isNotEmpty())) {
            true -> {
                var list = ""
                for (i in files.indices) {
                    list += "${files[i].toString().split("/").last().split(".")[0]}|"
                }
                list = list.substring(0, list.length - 1);
                IoTList(list)
            }
            false -> IoTList("Nothing")
        }
    }

    fun Info(name: String): Any {

        val path = "$project_path/IoT_List/${name}.json"

        val jsonString = File(path).readFile()
        val Loc = jsonString.toJson().getString("loc")
        val Type = jsonString.toJson().getString("type")

        return Info(name, Loc, Type)
    }

    fun Power_ONOFF(name: String, power: String): Any {
        val path = "$project_path/IoT_List/${name}.json"

        val jsonString = File(path).readFile()
        val address = jsonString.toJson().getString("port")
        val type = jsonString.toJson().getString("type")

        when(type) {
            "python" -> {
                val result: String = Python().connect(address, "${name}_${power}")
                val ok = result.substring(result.length-3, result.length)

                return when(ok) {
                    "END" -> true
                    else -> false
                }
            }
            "wifi" -> {return false}
            else -> return false
        }
    }

}