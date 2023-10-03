package me.tabraiz.room.ui.room

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.tabraiz.room.data.api.ApiHelper
import me.tabraiz.room.data.local.DatabaseHelper
import me.tabraiz.room.data.local.entity.User
import me.tabraiz.room.ui.base.UiState

class RoomDBViewModel(private val apiHelper: ApiHelper, private val dbHelper: DatabaseHelper) :
    ViewModel() {

    private val uiState = MutableLiveData<UiState<List<User>>>()

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            uiState.postValue(UiState.Loading)
            try {
                val usersFromDb = dbHelper.getUsers()
                if (usersFromDb.isEmpty()) {
                    val usersFromApi = apiHelper.getUsers()
                    val usersToInsertInDB = mutableListOf<User>()

                    for (apiUser in usersFromApi) {
                        val user = User(
                            apiUser.id,
                            apiUser.name,
                            apiUser.email,
                            apiUser.avatar
                        )
                        usersToInsertInDB.add(user)
                    }

                    dbHelper.insertAll(usersToInsertInDB)

                    uiState.postValue(UiState.Success(usersToInsertInDB))

                } else {
                    uiState.postValue(UiState.Success(usersFromDb))
                }


            } catch (e: Exception) {
                uiState.postValue(UiState.Error("Something Went Wrong"))
            }
        }
    }

    fun getUiState(): LiveData<UiState<List<User>>> {
        return uiState
    }

}