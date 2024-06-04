package com.example.musicapp.controllers.serviceControllers

import com.example.musicapp.models.LocationModel
import com.example.musicapp.models.RoomModel
import com.example.musicapp.models.requests.LocationRequestModel
import com.example.musicapp.models.requests.RoomRequestModel
import com.example.musicapp.repository.LocationRepository
import com.example.musicapp.repository.RoomRepository
import com.example.musicapp.services.LocationService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class LocationController (private val locationRepository: LocationRepository,private val roomRepository: RoomRepository) {
    var locationServiceClass = LocationService(locationRepository)
    @PostMapping("/location/{roomId}/session/Location")
    fun initializeUser(@PathVariable roomId:String,@RequestBody locationRequestModel: LocationRequestModel): ResponseEntity<LocationModel> {
        val something=locationServiceClass.initializeToRepository(roomId,locationRequestModel, roomRepository)
        return ResponseEntity.ok(something);
    }
}