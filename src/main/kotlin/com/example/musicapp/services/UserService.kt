package com.example.musicapp.services

import com.example.musicapp.models.User
import com.example.musicapp.models.UserStatus
import com.example.musicapp.models.requests.UserRequestModel
import com.example.musicapp.repository.UserRepository
import java.util.*

class UserService(private val userRepository: UserRepository) {
    private fun getUid(): UUID {
        return UUID.randomUUID();
    }

    public fun initializeToRepository(userRequest: UserRequestModel): User {
        val currentUserStatus = UserStatus(isOnline = true, currentSongPlaying = "")
        val id = getUid().toString();
        val name = userRequest.name
        val password = userRequest.password
        val phoneNumber = userRequest.phoneNumber;
        val likedSongs = arrayOf<String>();
        val userStatus = currentUserStatus;
        val savedSongs = arrayOf<String>();
        val roomsJoined = arrayOf<String>();
        val friends = arrayOf<String>();
        val user = User(id, name, phoneNumber, password, likedSongs, userStatus, savedSongs, roomsJoined, friends)
        if (!user) {
            val userResponse = userRepository.insert(user);
            return userResponse as User;
        }
        return { } as User;
    }
    public fun <T> userPatchHandler(id: String, op: String, path: String, value: T): User {
        when (op) {
            "REPLACE" -> {
                return userUpdatePathHandler(id, path, value)
            }
            else -> {
                throw Exception("Invalid Operation")
            }
        }
    }
    private fun <T> userUpdatePathHandler(id: String, path: String, value: T): User {
        val user = userRepository.findById(id).get();
        when (path) {
            "name" -> {
                user.name = value as String;
            }
            "phoneNumber" -> {
                user.phoneNumber = value as String;
            }
        }
        val response: Unit = userRepository.save(user);
        println(response);
        return user;
    }
}