package me.tabraiz.room.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import me.tabraiz.room.data.local.dao.UserDao
import me.tabraiz.room.data.local.entity.User

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

}