package com.example.musicapp.models

import org.jetbrains.annotations.NotNull
import org.springframework.data.mongodb.core.mapping.MongoId


public data class UserStatus(
    private var isOnline: Boolean,
    private var currentSongPlaying: String,
)
public data class User(
    @MongoId
    public var id: String,
    @NotNull
    public var name: String,
    @NotNull
    public var phoneNumber: String,
    @NotNull
    public var password: String,

    public var userStatus: Array<String>,

    public var roomsJoined: UserStatus,
    public var likedSongs: Array<String>,
    public var savedSongs: Array<String>,
    public var friends: Array<String>,
) {

    operator fun not(): Boolean {
        return !(id.isEmpty() || name.isEmpty() ||  password.isEmpty());
    }
}



