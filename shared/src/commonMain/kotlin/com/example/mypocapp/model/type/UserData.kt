package com.example.mypocapp.model.type

import kotlinx.serialization.Serializable

@Serializable
data class UserData(
    val id: Int,
    val name: String,
    val email: String,
    val date: String,
    val buttons: List<String>
)