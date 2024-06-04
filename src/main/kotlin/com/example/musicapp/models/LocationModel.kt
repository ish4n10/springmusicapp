package com.example.musicapp.models

import org.jetbrains.annotations.NotNull


public data class Misc(
    public var nearestGoodLocation : String,
    public var lastKnownLocation:String,
    public var connectionState:String,

)
public data class UserInfo(
    public var id: String,

    public var misc: Misc,
)
public data class LocationModel(
    @NotNull
    public var roomId : String,
    public var userList:Array<UserInfo>,

)