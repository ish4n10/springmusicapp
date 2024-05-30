package com.example.musicapp.services

import com.example.musicapp.models.InitializeData
import com.example.musicapp.models.RoomModel
import com.example.musicapp.models.SmolUser
import com.example.musicapp.models.requests.RoomRequestModel
import com.example.musicapp.models.requests.RoomUpdateJoinRoom

import com.example.musicapp.repository.RoomRepository

import java.util.*

class RoomService( private val roomRepository: RoomRepository) {
    private fun getUid(): UUID {
        return UUID.randomUUID();
    }

    public fun initializeToRepository(roomRequest: RoomRequestModel): RoomModel {
        var id = getUid().toString();
        var hostId = roomRequest.hostId;
        var initializeData = InitializeData(creator = roomRequest.hostId, roomPassword = roomRequest.roomPassword);
        var listOfUser = arrayOf<SmolUser>();
        var status = "online";
        var initTs = java.time.LocalDateTime.now();
        var currentSongPlaying = "";
        var queueList = arrayOf<String>();
        val room = RoomModel(id, hostId, initializeData, listOfUser, status, initTs, currentSongPlaying, queueList);
        if (!room) {
            val roomResponse = roomRepository.insert(room);
            return roomResponse;
        }
        return {} as RoomModel;
    }
    public fun <T> roomPatchHandler(id: String, op: String, path: String, value: T): RoomModel {
        when (op) {
            "REPLACE" -> {
                return roomUpdatePathHandler(id, path, value)
            }
            else -> {
                throw Exception("Invalid Operation")
            }
        }
    }
    private fun <T> roomUpdatePathHandler(id: String, path: String, value: T): RoomModel {
        val room = roomRepository.findById(id).get();
        when (path) {
            "hostId" -> {
                room.hostId = value as String;
            }
            "status" -> {
                room.status = value as String;
            }
        }
        val response: RoomModel = roomRepository.save(room);
        println(response);
        return room;
    }

    public fun addUserToRoom(id: String, roomAddUserRequest: RoomUpdateJoinRoom): RoomModel {
        var roomData = roomRepository.findById(id).get();
        roomData.listOfUsers += roomAddUserRequest.userData;
        roomRepository.save(roomData);
        return roomData;
    }
}