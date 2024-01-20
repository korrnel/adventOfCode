import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import kotlin.time.measureTime

class Common {
    companion object {

         fun getData (day : Int) : String {
            val client = HttpClient.newBuilder().build()
            val request = HttpRequest.newBuilder()
                .uri(URI.create("https://adventofcode.com/2023/day/"+ day.toString() + "/input"))
                .header("Cookie","_ga=GA1.2.295804623.1701507354; session=53616c7465645f5fdc32da916c518d3393edaaa0247aeb85199d6fecd1523812773242e88beea220dbb162009b81e0b6d048d1185a617d21a9e0c6efddc82fda; _gid=GA1.2.1613609882.1705752355; _gat=1; _ga_MHSNPJKWC7=GS1.2.1705761165.81.1.1705761172.0.0.0")
                .build()
            val response = client.send(request, HttpResponse.BodyHandlers.ofString())
            return  response.body()

         }
        }

    }
