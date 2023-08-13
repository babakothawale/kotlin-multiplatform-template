package com.bk.km.data

import com.bk.km.data.cache.Cache
import com.bk.km.data.cache.MemoryCache
import com.bk.km.data.domain.model.Note
import com.bk.km.data.network.NetworkClient.httpClient
import com.bk.km.data.network.NoteApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch


interface Repository {
  fun getNotes(): Flow<List<Note>>
  suspend fun loadNotes()
}

class RepositoryImpl(private val noteApi: NoteApi, private val cache: Cache) : Repository {
  override fun getNotes(): Flow<List<Note>> {
    return cache.loadNotes().catch {
      it.printStackTrace()
    }
  }

  override suspend fun loadNotes() {
    fetchNotes()
  }

  private suspend fun fetchNotes() {
    val data = noteApi.fetchNotes().map { Note(it.id, it.title.orEmpty()) }
    println("data: $data")
    cache.cacheNotes(data)
  }

  companion object {
    private var repository: Repository? = null
    fun getInstance(): Repository {
      return repository ?: RepositoryImpl(
        NoteApi(httpClient = httpClient), MemoryCache()
      ).also { repository = it }
    }
  }

}