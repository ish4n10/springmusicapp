package com.example.musicapp.models

import org.jetbrains.annotations.NotNull
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.MongoId
import java.time.LocalDateTime
import java.util.Date

data class SmolUser (
    @NotNull
    public var id: String,
    @NotNull
    public var name: String,
    @NotNull
    public var phoneNumber: String
)

data class InitializeData(
    var creator: String,
    var roomPassword: String,
);

public data class RoomModel(
    @MongoId
    @NotNull
    var id: String,

    @NotNull
    var hostId: String,

    var initializeData: InitializeData,

    var listOfUsers: Array<SmolUser>,
    var status: String,
    var initTs: LocalDateTime,
    var currentSongPlaying: String,
    var queueList: Array<String>,

) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as RoomModel
        if (!listOfUsers.contentEquals(other.listOfUsers)) return false
        if (!queueList.contentEquals(other.queueList)) return false
        return true
    }

    operator fun not(): Boolean {
            return !(id.isEmpty() || hostId.isEmpty());
    }
};

