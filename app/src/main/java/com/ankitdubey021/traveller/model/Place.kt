package com.ankitdubey021.traveller.model

data class Place(
    val id : Int,
    val title : String,
    val body : String,
    val imageUrl : String,
    val place : String,
    val tag : List<String>
)
