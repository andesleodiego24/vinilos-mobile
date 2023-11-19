package com.example.drawerapp.repositories

import android.app.Application
import com.example.drawerapp.models.Collector
import com.example.drawerapp.network.NetworkServiceAdapter

class CollectorRepository(val application: Application) {
    suspend fun refreshData(): List<Collector> {
        return NetworkServiceAdapter.getInstance(application).getCollectors()
    }
}