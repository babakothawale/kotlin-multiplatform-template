package com.bk.km.data.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class NoteApi(private val httpClient: HttpClient) {

  suspend fun fetchNotes(): List<NoteResponse> {
    val url = "https://drive.google.com/uc?export=download&id=1gihLOpdEv0NhKxNZmaFzSEK6towhe87z"
    return httpClient.get(url).body()
  }
}