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

data class UserPatchRequestModel<T> (
    @NotNull
    public var op: String,
    @NotNull
    public var path: String,
    @NotNull
    public var value: T
)
