package com.example.testapplication.db.entities

data class SignUpData(
    val username: String,
    val email: String,
    val password: String,
    val repeatPassword: String
) {
    fun validate() {
        if (email.isBlank()) throw Exception()
        if (username.isBlank()) throw Exception()
        if (password.isBlank()) throw Exception()
        if (password != repeatPassword) throw Exception()
    }
}