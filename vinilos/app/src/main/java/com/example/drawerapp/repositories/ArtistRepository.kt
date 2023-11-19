package com.example.drawerapp.repositories

import android.app.Application
import com.example.drawerapp.models.Artist
import com.example.drawerapp.network.NetworkServiceAdapter

class ArtistRepository(val application: Application) {
    suspend fun refreshData(): List<Artist> {
        return NetworkServiceAdapter.getInstance(application).getArtists()
    }
}