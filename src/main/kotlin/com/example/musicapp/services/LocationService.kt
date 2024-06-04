package com.example.musicapp.services

import com.example.musicapp.BadRequestException
import com.example.musicapp.models.*
import com.example.musicapp.models.requests.LocationRequestModel
import com.example.musicapp.models.requests.RoomRequestModel
import com.example.musicapp.repository.LocationRepository
import com.example.musicapp.repository.RoomRepository
class LocationService(private val locationRepository: LocationRepository) {
    public fun initializeToRepository(roomId:String,locationRequest: LocationRequestModel,roomRepository: RoomRepository):  LocationModel{
        var roomService=RoomService(
            roomRepository
        )

        var misc=Misc(
             nearestGoodLocation = "",
             lastKnownLocation="",
            connectionState="online"
        )
        var userInfo= UserInfo(
             id=locationRequest.userId,
            misc=misc

            )
        var userList= arrayOf(userInfo)
        var LocationModelConstructor=LocationModel(roomId,userList)
        locationRepository.save(LocationModelConstructor)
        return LocationModelConstructor;
    }

}