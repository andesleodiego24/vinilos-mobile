package com.example.drawerapp.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.drawerapp.models.Album
import com.example.drawerapp.repositories.AlbumRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AlbumDetailsViewModel(application: Application, albumId: String): AndroidViewModel(application) {
    private val albumRepository = AlbumRepository(application)

    private val _album = MutableLiveData<Album>()
    val album: LiveData<Album>
        get() = _album

    private var _eventNetworkError = MutableLiveData<Boolean>(false)
    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)
    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    init {
        refreshAlbumDetails(albumId)
    }

    private fun refreshAlbumDetails(albumId: String) {
        try {
            viewModelScope.launch(Dispatchers.Default) {
                withContext(Dispatchers.IO) {
                    val data = albumRepository.refreshAlbumDetails(albumId)
                    _album.postValue(data)
                }
            }
            _eventNetworkError.value = false
            _isNetworkErrorShown.value = false
        } catch (e: Exception){
            _eventNetworkError.value = true
        }
    }

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }

    class Factory(val app: Application, private val albumId: String): ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(AlbumDetailsViewModel::class.java)) {
               @Suppress("UNCHECKED CAST")
               return AlbumDetailsViewModel(app, albumId) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}