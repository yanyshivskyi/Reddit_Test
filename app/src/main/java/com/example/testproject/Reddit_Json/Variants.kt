package com.example.testproject.Reddit_Json

import com.example.testproject.Reddit_Json.Gif
import com.example.testproject.Reddit_Json.Mp4
import com.example.testproject.Reddit_Json.Nsfw
import com.example.testproject.Reddit_Json.Obfuscated

data class Variants(
    val gif: Gif,
    val mp4: Mp4,
    val nsfw: Nsfw,
    val obfuscated: Obfuscated
)