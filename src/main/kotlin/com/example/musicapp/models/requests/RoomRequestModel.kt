package com.example.musicapp.models.requests

import com.example.musicapp.models.SmolUser
import org.jetbrains.annotations.NotNull
import org.springframework.data.annotation.Id

data class RoomRequestModel (
    @NotNull
    public var hostId: String,
    @NotNull
    public var roomPassword: String
)
data class RoomPatchRequestModel<T> (
    @NotNull
    public var op: String,
    @NotNull
    public var path: String,
    @NotNull
    public var value: T
)

public data class RoomUpdateJoinRoom (
    @NotNull
    public var userData: SmolUser,
    public var roomPassword: String,
    public var userid: String,
)

public data class AddMusicToRoom (
    @NotNull
    public var songId: String
)


