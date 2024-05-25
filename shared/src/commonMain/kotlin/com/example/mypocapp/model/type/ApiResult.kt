package com.example.mypocapp.model.type

/**
 * Generic class for API result.
 */
data class ApiResult<out T>(
    val data: T? = null,
    val error: String? = null
)