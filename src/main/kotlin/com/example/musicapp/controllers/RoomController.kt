package com.example.musicapp.controllers
import com.example.musicapp.models.RoomModel
import com.example.musicapp.models.requests.RoomRequestModel
import com.example.musicapp.repository.RoomRepository
import com.example.musicapp.services.RoomService
import com.example.musicapp.services.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
@RestController
class RoomController(private val roomRepository: RoomRepository) {
    @GetMapping("/room")
    fun index(@RequestParam("id") id: String): Optional<RoomModel> {
        println(id);
        val roomDetails = roomRepository.findById(id);
        return roomDetails;
    };
    @PostMapping("/room")
    fun initializeUser(@RequestBody roomRequestModel: RoomRequestModel): ResponseEntity<RoomModel> {
        val roomBuffer = RoomService(roomRequestModel, roomRepository);
        var response= roomBuffer.initializeToRepository();
        return ResponseEntity.ok(response);
    }
}