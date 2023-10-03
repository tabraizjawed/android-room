package me.tabraiz.room.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import me.tabraiz.room.data.api.ApiHelper
import me.tabraiz.room.data.local.DatabaseHelper
import me.tabraiz.room.ui.room.RoomDBViewModel

class ViewModelFactory(private val apiHelper: ApiHelper, private val dbHelper: DatabaseHelper) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RoomDBViewModel::class.java)) {
            return RoomDBViewModel(apiHelper, dbHelper) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}