package com.bk.km

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform