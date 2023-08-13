package com.bk.km.data.cache

import com.bk.km.data.domain.model.Note
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

interface Cache {
  fun loadNotes(): Flow<List<Note>>
  fun cacheNotes(notes:List<Note>)
}

class DbCache : Cache {
  override fun loadNotes(): Flow<List<Note>> {
    return flow {
      emit(List(5) { index -> Note(index.toLong(), "Note $index") })
    }
  }

  override fun cacheNotes(notes: List<Note>) {
    //todo
  }
}

class MemoryCache : Cache {
  private val notesCache: MutableList<Note> = mutableListOf()
  private val f = flowOf(notesCache)
  override fun loadNotes(): Flow<List<Note>> {
    return f
  }

  override fun cacheNotes(notes: List<Note>) {
    notesCache.clear()
    notesCache.addAll(notes)
  }
}