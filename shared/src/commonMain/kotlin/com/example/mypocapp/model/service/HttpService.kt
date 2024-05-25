package com.example.mypocapp.model.service

import com.example.mypocapp.model.type.UserData
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class HttpService {
    // HttpClient for API - standard procedure of Ktor library
    private val client = HttpClient {
        install(ContentNegotiation) {
            json()
        }
    }

    /**
     * Connects to API endpoint and returns a map of user data.
     */
    suspend fun getApiData(link: String): Map<String, UserData> {
        val response: HttpResponse = client.get(link)
        val responseBody: String = response.bodyAsText()
        val json = Json { ignoreUnknownKeys = true }
        client.close() // close client after use - obsolete??
        return json.decodeFromString<Map<String, UserData>>(responseBody)
    }
}