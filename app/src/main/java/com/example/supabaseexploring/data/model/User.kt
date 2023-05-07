package com.example.supabaseexploring.data.model


@kotlinx.serialization.Serializable
data class User(
    val id : Int = 0,
    val first_name : String = "",
    val last_name : String = "",
    val email : String = ""
)
