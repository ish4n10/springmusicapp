package com.example.musicapp.repository

import org.springframework.data.mongodb.repository.MongoRepository
import com.example.musicapp.models.User;
import java.util.*

public interface UserRepository : MongoRepository<User,String> {
    override fun findById(id: String): Optional<User>;
    override fun deleteById(id: String);
    fun existsUserById(id: String): Boolean;
    fun save(user: User);
}


