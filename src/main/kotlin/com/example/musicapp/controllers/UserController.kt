package com.example.musicapp.controllers

import com.example.musicapp.models.User
import com.example.musicapp.models.requests.UserRequestModel
import com.example.musicapp.repository.UserRepository
import com.example.musicapp.services.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
class UserController(private val userRepository: UserRepository) {

    @GetMapping("/")
    fun index(@RequestParam("id") id: String): Optional<User> {
        println(id);
        val userDetails = userRepository.findById(id);
        return userDetails;
    };
    @PostMapping("/user")
    fun initializeUser(@RequestBody userRequestModel: UserRequestModel): ResponseEntity<String> {
        var userBuffer = UserService(userRequestModel, userRepository);
        userBuffer.initializeToRepository();
        return ResponseEntity.ok(userBuffer.toString())
    }
}
