package com.example.musicapp.controllers
import com.example.musicapp.models.RoomModel
import com.example.musicapp.models.requests.RoomRequestModel
import com.example.musicapp.models.requests.RoomPatchRequestModel
import com.example.musicapp.models.requests.RoomUpdateJoinRoom
import com.example.musicapp.repository.RoomRepository
import com.example.musicapp.services.RoomService

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
@RestController
class RoomController(private val roomRepository: RoomRepository) {
    var roomServiceClass = RoomService(roomRepository);

    @GetMapping("/room")
    fun getAllRooms(): MutableList<RoomModel> {
        return roomRepository.findAll();
    }
    @GetMapping("/room/{id}")
    fun getRoomById(@PathVariable id: String): Optional<RoomModel> {
        println(id);
        val roomDetails = roomRepository.findById(id);
        return roomDetails;
    };
    @PostMapping("/room")
    fun initializeRoom(@RequestBody roomRequestModel: RoomRequestModel): ResponseEntity<RoomModel> {
        val userData = roomServiceClass.initializeToRepository(roomRequestModel)
        return ResponseEntity.ok(userData);
    }
    @PatchMapping("/room/{id}")
    fun updateRoomDetails(@PathVariable id: String, @RequestBody roomPatchRequest: RoomPatchRequestModel<String>) : ResponseEntity<RoomModel> {
        val response = roomServiceClass.roomPatchHandler(
            id = id,
            op = roomPatchRequest.op,
            path = roomPatchRequest.path,
            value = roomPatchRequest.value
        );
        return ResponseEntity.ok(response)
    }

    @DeleteMapping("/room/{id}")
    fun deleteRoom(@PathVariable id: String): ResponseEntity<String> {
        val response = roomRepository.deleteById(id)
        return ResponseEntity.ok({"Deleted user of id $id"}.toString());
    }
    //POST api/room/{room_id}/session
    @PostMapping("/room/{id}/session")
    fun updateRoomUsers(@PathVariable id: String, @RequestBody roomModel: RoomUpdateJoinRoom) : ResponseEntity<RoomModel> {
        val response = roomServiceClass.addUserToRoom(id, roomModel);
        return ResponseEntity.ok(response);
    }

}