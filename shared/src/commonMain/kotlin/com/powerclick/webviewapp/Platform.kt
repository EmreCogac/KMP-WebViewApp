package com.powerclick.webviewapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform