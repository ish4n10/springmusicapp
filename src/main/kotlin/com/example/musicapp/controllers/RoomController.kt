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
    fun index(@RequestParam("id") id: String): Optional<RoomModel> {
        println(id);
        val roomDetails = roomRepository.findById(id);
        return roomDetails;
    };
    @PostMapping("/room")
    fun initializeUser(@RequestBody roomRequestModel: RoomRequestModel): ResponseEntity<RoomModel> {
      val something=roomServiceClass.initializeToRepository(roomRequestModel)
        return ResponseEntity.ok(something);
    }

    @PostMapping("/room/{id}/session")
    fun updateRoomUser(@PathVariable id : String,@RequestBody roomRequest:RoomUpdateJoinRoom): RoomModel {
        val response=roomServiceClass.addUserToRoom(id,roomRequest)
        return response
    }

    @PatchMapping("/room/{id}")
    fun updateUserDetails(@PathVariable id: String, @RequestBody roomPatchRequest: RoomPatchRequestModel<String>) : ResponseEntity<RoomModel> {
        val response = roomServiceClass.roomPatchHandler(
            id = id,
            op = roomPatchRequest.op,
            path = roomPatchRequest.path,
            value = roomPatchRequest.value
        );
        return ResponseEntity.ok(response)
    }
    @DeleteMapping("/room/{roomId}/session/{userId}")
    fun deleteUserFromRoom( @PathVariable roomId: String,@PathVariable userId: String) : ResponseEntity<RoomModel> {
        val userData = roomServiceClass.getUserFromRoom(roomId)
        val user = userData.listOfUsers.find { it.id == userId }
        if(user!=null){
            userData.listOfUsers=userData.listOfUsers.filter { it.id!=userId }.toTypedArray()
            roomRepository.save(userData)
            return ResponseEntity.noContent().build()
        }
        return ResponseEntity.notFound().build()

    }

    @DeleteMapping("/room/{id}")
    fun deleteRoom(@PathVariable id: String): ResponseEntity<RoomModel> {
        roomRepository.deleteById(id)
        return ResponseEntity.noContent().build()
    }

}