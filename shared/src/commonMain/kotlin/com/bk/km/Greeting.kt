package com.bk.km

class Greeting {
    private val platform: Platform = getPlatform()

    fun greet(): String {
        return "Running on , ${platform.name}! "
    }
}