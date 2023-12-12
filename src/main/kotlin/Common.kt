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
                .header("Cookie","_ga=GA1.2.295804623.1701507354; _gid=GA1.2.131787216.1701507354; session=53616c7465645f5fda3577ccfc12109342e8ec2e219ce4ccdc420e56d526eb4f59b00e396a73b67de8c5c4686db7b47d1bd6343dc6c28fd6fd35bce3818065d4; _ga_MHSNPJKWC7=GS1.2.1701511847.2.0.1701511847.0.0.0")
                .build()
            val response = client.send(request, HttpResponse.BodyHandlers.ofString())
            return  response.body()

         }
        }

    }
