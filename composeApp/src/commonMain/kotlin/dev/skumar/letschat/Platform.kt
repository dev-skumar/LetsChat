package dev.skumar.letschat

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform