package com.example.drawerapp.repositories

import android.app.Application
import com.example.drawerapp.models.Album
import com.example.drawerapp.network.NetworkServiceAdapter

class AlbumRepository (val application: Application) {
    suspend fun refreshAlbumList(): List<Album> {
        return NetworkServiceAdapter.getInstance(application).getAlbums()
    }

    suspend fun refreshAlbumDetails(albumId: String): Album {
        return NetworkServiceAdapter.getInstance(application).getAlbumById(albumId)
    }
}