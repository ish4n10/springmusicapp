package com.example.musicapp.repository

import com.example.musicapp.models.LocationModel
import org.springframework.data.mongodb.repository.MongoRepository
import com.example.musicapp.models.User;
import java.util.*

public interface LocationRepository : MongoRepository<LocationModel,String> {
    override fun findById(id: String): Optional<LocationModel>;
    override fun deleteById(id: String);
}