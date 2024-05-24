package com.example.musicapp.models.requests

import org.jetbrains.annotations.NotNull

data class UserRequestModel (
    @NotNull
    public var name: String,
    @NotNull
    public var phoneNumber: String,
    @NotNull
    public var password: String,
)

