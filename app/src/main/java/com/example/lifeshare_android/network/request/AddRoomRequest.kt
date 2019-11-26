package com.example.lifeshare_android.network.request

class AddRoomRequest(var houseId: Int,
                     var peopleCnt: Int,
                     var money: String,
                     var image: String) {
    constructor() : this(0, 0, "", "")
}