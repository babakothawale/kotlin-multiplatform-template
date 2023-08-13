package com.bk.km.data.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class NoteResponse(
  @SerialName("id") val id: Long,
  @SerialName("title") val title: String?,
)