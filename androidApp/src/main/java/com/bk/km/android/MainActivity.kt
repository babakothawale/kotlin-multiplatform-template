package com.bk.km.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.bk.km.Greeting
import com.bk.km.data.domain.model.Note

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      MyApplicationTheme {
        Surface(
          modifier = Modifier.fillMaxSize(),
          color = MaterialTheme.colors.background
        ) {
          val viewModel: MainViewModel by viewModels()
          NotesList(list = viewModel.noteState.collectAsState().value)
          viewModel.loadNotes()
         // GreetingView(Greeting().greet())
        }
      }
    }
  }
}


@Composable
fun NotesList(list: List<Note>) {
  LazyColumn {
    items(list, key = { item -> item.id }) { note ->
      Text(text = note.title)
    }
  }
}

@Preview
@Composable
fun DefaultPreview() {
  MyApplicationTheme {
    NotesList(List(2) { Note(it.toLong(), "it $it") })
  }
}
