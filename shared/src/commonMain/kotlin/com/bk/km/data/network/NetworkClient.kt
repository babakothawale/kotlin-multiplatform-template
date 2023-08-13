package com.bk.km.data.network

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object NetworkClient {
  val httpClient: HttpClient = HttpClient(){
    install(ContentNegotiation) {
      json(Json {
        explicitNulls = false
        prettyPrint = true
        isLenient = true
        ignoreUnknownKeys = true
        useAlternativeNames = false
      })
    }
  }
}