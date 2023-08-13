package com.bk.km.android

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bk.km.data.Repository
import com.bk.km.data.RepositoryImpl
import com.bk.km.data.domain.model.Note
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MainViewModel() : ViewModel() {
  private val repository: Repository = RepositoryImpl.getInstance()

  val noteState: MutableStateFlow<List<Note>> = MutableStateFlow(listOf())
  fun loadNotes() {
    viewModelScope.launch {
      repository.getNotes().collect {
        Log.d("TAG", "loadNotes: $it")
        if (it.isEmpty()) {
          repository.loadNotes()
        } else {
          noteState.value = it
        }
      }
    }
  }

}