package com.example.musicapp.repository

import com.example.musicapp.models.RoomModel
import org.springframework.data.mongodb.repository.MongoRepository
import com.example.musicapp.models.User;
import org.springframework.data.domain.Example
import java.util.*

public interface RoomRepository : MongoRepository<RoomModel,String> {
    override fun findById(id: String): Optional<RoomModel>;
    override fun deleteById(id: String);
    override fun findAll(): MutableList<RoomModel>;
    fun existsUserById(id: String): Boolean;
    fun save(user: User);
}
