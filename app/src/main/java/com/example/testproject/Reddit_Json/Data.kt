package com.example.testproject.Reddit_Json

import com.example.testproject.Reddit_Json.Children

data class Data(
    val after: String,
    val before: Any,
    val children: List<Children>,
    val dist: Int,
    val geo_filter: String,
    val modhash: String
)