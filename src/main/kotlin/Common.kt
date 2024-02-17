import com.google.gson.*

import java.io.File
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse


class Common {
    companion object {
        data class Config(val Cookie: String)

        fun loadConfig(env: String): Config? {
            val configFile = File(System.getProperty("user.dir")+ "/src/main/resources/config.$env.json")
            val json = configFile.readText()
         return  Gson().fromJson(json, Config::class.java)
        }
         fun getData (day: Int, s: String) : String {
             val env = loadConfig(s)
            val client = HttpClient.newBuilder().build()
            val request = HttpRequest.newBuilder()
                .uri(URI.create("https://adventofcode.com/2023/day/"+ day.toString() + "/input"))
                .header("Cookie", env?.Cookie ?: "")
                .build()
            val response = client.send(request, HttpResponse.BodyHandlers.ofString())
            return  response.body()

         }
        }

    }
