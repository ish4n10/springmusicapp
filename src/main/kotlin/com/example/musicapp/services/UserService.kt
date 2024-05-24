package com.example.musicapp.services

import com.example.musicapp.models.User
import com.example.musicapp.models.UserStatus
import com.example.musicapp.models.requests.UserRequestModel
import com.example.musicapp.repository.UserRepository
import java.util.*

class UserService(private val userRequest: UserRequestModel, private val userRepository: UserRepository) {
    private var user: User;
    private fun getUid(): UUID {
        return UUID.randomUUID();
    }
    init {
        val currentUserStatus = UserStatus(isOnline = true, currentSongPlaying = "")
        var id = getUid().toString();
        var name = userRequest.name
        var password = userRequest.password
        var phoneNumber = userRequest.phoneNumber;
        var likedSongs = arrayOf<String>();
        var userStatus = currentUserStatus;
        var savedSongs = arrayOf<String>();
        var roomsJoined = arrayOf<String>();
        var friends = arrayOf<String>();
        user = User(id, name, phoneNumber, password, likedSongs, userStatus, savedSongs, roomsJoined, friends)
    };

    public fun initializeToRepository() {
        if (!user) {
            val userResponse = userRepository.save(user);
            return userResponse;
        }
    }
}