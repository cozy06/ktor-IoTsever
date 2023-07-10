package cozy06.com.data.device

import com.cozy06.routes.project_path
import java.io.BufferedReader
import java.io.InputStreamReader


class Python {
    fun connect(address: String, arg: String): String {
        val processBuilder = ProcessBuilder("python3", "$project_path/IoT_Python/connect.py", address, arg)
        val process = processBuilder.start()
        val inputStream = process.inputStream
        val reader = BufferedReader(InputStreamReader(inputStream))

        var result: String = ""
        var line: String?
        while (reader.readLine().also { line = it } != null) {
            // 실행 결과 처리
            result += line
        }
        println(result)
        return result
    }
}