package com.example.musicapp.controllers

import com.example.musicapp.models.RoomModel
import com.example.musicapp.models.User
import com.example.musicapp.models.requests.UserPatchRequestModel
import com.example.musicapp.models.requests.UserRequestModel
import com.example.musicapp.repository.UserRepository
import com.example.musicapp.services.UserService
import jakarta.websocket.server.PathParam
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
class UserController(private val userRepository: UserRepository) {
    var userServiceClass = UserService(userRepository);
    @GetMapping("/")
    fun index(@RequestParam("id") id: String): Optional<User> {
        println(id);
        val userDetails = userRepository.findById(id);
        return userDetails;
    };

    @PostMapping("/user")
    fun initializeUser(@RequestBody userRequestModel: UserRequestModel): ResponseEntity<User> {
        var something = userServiceClass.initializeToRepository(userRequestModel);
        return ResponseEntity.ok(something)
    }

    @PatchMapping("/user/{id}")
    fun updateUserDetails(@PathVariable id: String, @RequestBody userPatchRequest: UserPatchRequestModel<String>) : ResponseEntity<User> {
        val response = userServiceClass.userPatchHandler(
            id = id,
            op = userPatchRequest.op,
            path = userPatchRequest.path,
            value = userPatchRequest.value
        );
        return ResponseEntity.ok(response)
    }
    @DeleteMapping("/user/{id}")
    fun deleteUser(@PathVariable id: String): ResponseEntity<User> {
        userRepository.deleteById(id)
        return ResponseEntity.noContent().build()
    }
}
