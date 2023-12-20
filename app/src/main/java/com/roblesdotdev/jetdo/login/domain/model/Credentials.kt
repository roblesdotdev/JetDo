package com.roblesdotdev.jetdo.login.domain.model

@JvmInline
value class Password(val value: String)

@JvmInline
value class Email(val value: String)

data class Credentials(
    val email: Email = Email(""),
    val password: Password = Password("")
)
