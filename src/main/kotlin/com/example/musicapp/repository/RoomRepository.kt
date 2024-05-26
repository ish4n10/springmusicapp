package com.example.musicapp.repository

import com.example.musicapp.models.RoomModel
import org.springframework.data.mongodb.repository.MongoRepository
import com.example.musicapp.models.User;
import java.util.*

public interface RoomRepository : MongoRepository<RoomModel,String> {
    override fun findById(id: String): Optional<RoomModel>;
    fun existsUserById(id: String): Boolean;
    fun save(user: User);
}
