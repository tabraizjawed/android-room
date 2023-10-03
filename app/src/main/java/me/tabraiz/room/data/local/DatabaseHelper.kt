package me.tabraiz.room.data.local

import me.tabraiz.room.data.local.entity.User

interface DatabaseHelper {

    suspend fun getUsers(): List<User>

    suspend fun insertAll(users: List<User>)

}