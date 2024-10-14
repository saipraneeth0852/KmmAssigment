package org.example.project.data

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

expect fun httpClient(config: HttpClientConfig<*>.() -> Unit = {}): HttpClient

class BreachApi {
    private val client = httpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                isLenient = true
                coerceInputValues = true
            })
        }
    }

    private val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
        coerceInputValues = true
    }

    suspend fun getBreaches(): List<Breach> = withContext(Dispatchers.Default) {
        try {
            println("Starting API call...")
            val response: String = client.get("https://haveibeenpwned.com/api/v2/breaches").body()
            println("Raw API response received. Length: ${response.length}")
            println("First 500 characters of raw response: ${response.take(500)}")

            val breaches: List<Breach> = json.decodeFromString(ListSerializer(Breach.serializer()), response)
            println("Deserialization successful. Number of breaches: ${breaches.size}")

            if (breaches.isNotEmpty()) {
                val firstBreach = breaches.first()
                println("Details of first breach:")
                println("Name: ${firstBreach.Name}")
                println("Title: ${firstBreach.Title}")
                println("Domain: ${firstBreach.Domain}")
                println("BreachDate: ${firstBreach.BreachDate}")
                println("PwnCount: ${firstBreach.PwnCount}")
                println("Description: ${firstBreach.Description.take(100)}...") // First 100 characters
                println("DataClasses: ${firstBreach.DataClasses.joinToString(", ")}")
            } else {
                println("No breaches found in the response")
            }
            breaches
        } catch (e: Exception) {
            println("Error in getBreaches: ${e.message}")
            e.printStackTrace()
            throw e
        }
    }
}