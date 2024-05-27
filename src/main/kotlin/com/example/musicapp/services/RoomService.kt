package com.example.musicapp.services

import com.example.musicapp.models.InitializeData
import com.example.musicapp.models.RoomModel
import com.example.musicapp.models.User
import com.example.musicapp.models.UserStatus

import com.example.musicapp.models.requests.RoomRequestModel

import com.example.musicapp.repository.RoomRepository
import java.time.LocalDateTime

import java.util.*

class RoomService(private val roomRequest: RoomRequestModel, private val roomRepository: RoomRepository) {
    private var room: RoomModel;
    private fun getUid(): UUID {
        return UUID.randomUUID();
    }

    init {
        var id = getUid().toString();
        var hostId = roomRequest.hostId;
        var initializeData = InitializeData(creator = roomRequest.hostId, roomPassword = roomRequest.roomPassword);
        var listOfUser = arrayOf<String>();
        var status = "online";
        var initTs = java.time.LocalDateTime.now();
        var currentSongPlaying = "";
        var queueList = arrayOf<String>();

        room = RoomModel(id, hostId, initializeData, listOfUser, status, initTs, currentSongPlaying, queueList);
    };
    public fun initializeToRepository(): RoomModel {
        if (!room) {
            val roomResponse = roomRepository.insert(room);
            return roomResponse;
        }
        return {} as RoomModel;
    }
}