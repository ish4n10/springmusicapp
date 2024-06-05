package com.example.musicapp.controllers
import com.example.musicapp.models.RoomModel
import com.example.musicapp.models.requests.AddMusicToRoom
import com.example.musicapp.models.requests.RoomRequestModel
import com.example.musicapp.models.requests.RoomPatchRequestModel
import com.example.musicapp.models.requests.RoomUpdateJoinRoom
import com.example.musicapp.repository.RoomRepository
import com.example.musicapp.repository.UserRepository
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
//DELETE api/room/{room_id}/session/{user_id}
    @DeleteMapping("/{roomId}/session/{userId}")
    fun deleteUser(@PathVariable roomId: String, @PathVariable userId: String  ): ResponseEntity<String> {
        val response = roomServiceClass.deleteUserFromRoom(roomId,userId);
        return ResponseEntity.ok({"Deleted user of id $userId"}.toString());


    }
//    GET api/room/{room_id}/session/queue
      @GetMapping("/{roomId}/queue")
      fun getsonglist(@PathVariable roomId: String ) : Array<String> {
      val response = roomRepository.findById(roomId).get()
      return response.queueList


    }
//    POST api/room/{room_id}/session/queue
    @PostMapping("/room/{roomId}/queue")
    fun addsong(@PathVariable roomId: String, @RequestBody body: AddMusicToRoom): RoomModel {
        val response = roomRepository.findById(roomId).get()
         response.queueList += body.songId
          roomRepository.save(response)
          return response
}

}