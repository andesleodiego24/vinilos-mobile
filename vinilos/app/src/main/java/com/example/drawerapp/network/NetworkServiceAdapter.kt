package com.example.drawerapp.network

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.drawerapp.models.Album
import com.example.drawerapp.models.Artist
import com.example.drawerapp.models.Collector
import org.json.JSONArray
import org.json.JSONObject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class NetworkServiceAdapter constructor(context: Context) {
    companion object{
//        const val BASE_URL= "https://web-andesleodiego24.cloud.okteto.net/"
        const val BASE_URL= "https://vynils-back-heroku.herokuapp.com/"
        private var instance: NetworkServiceAdapter? = null
        fun getInstance(context: Context) =
            instance ?: synchronized(this) {
                instance ?: NetworkServiceAdapter(context).also {
                    instance = it
                }
            }
    }

    private val requestQueue: RequestQueue by lazy {
        Volley.newRequestQueue(context.applicationContext)
    }

    suspend fun getAlbums() = suspendCoroutine<List<Album>> {cont ->
        requestQueue.add(getRequest("albums",
            { response ->
                val resp = JSONArray(response)
                val list = mutableListOf<Album>()
                for (i in 0 until resp.length()) {
                    val item = resp.getJSONObject(i)
                    val artistList = item.getJSONArray("performers")
                    val performersList = mutableListOf<String>()
                    for(j in 0 until artistList.length()) {
                        val artist = artistList.getJSONObject(j)
                        performersList.add(artist.getString("name"))
                    }

                    val album = Album(id = item.getString("id"),name = item.getString("name"),
                        cover = item.getString("cover"), performers = performersList.joinToString(", ") ,
                        recordLabel = item.getString("recordLabel"),
                        releaseDate = item.getString("releaseDate"), genre = item.getString("genre"),
                        description = item.getString("description"))
                    list.add(i, album)
                }
                cont.resume(list)
            },
            {
                cont.resumeWithException(it)
            }
        ))
    }

    suspend fun getAlbumById(albumId: String) = suspendCoroutine<Album> { cont ->
        requestQueue.add(getRequest("albums/$albumId",
            {response ->
                val resp = JSONObject(response)
                val album  = Album(id = resp.getString("id"), name = resp.getString("name"),
                    cover = resp.getString("cover"), performers = resp.getJSONArray("performers").join(", ") ,
                    recordLabel = resp.getString("recordLabel"), releaseDate = resp.getString("releaseDate"),
                    genre = resp.getString("genre"), description = resp.getString("description"))

                cont.resume(album)
            },{
                cont.resumeWithException(it)
            }))
    }

    suspend fun getArtists() = suspendCoroutine<List<Artist>> { cont ->
        requestQueue.add(getRequest("bands",
            { response ->
                val resp = JSONArray(response)
                val list = mutableListOf<Artist>()
                for (i in 0 until resp.length()) {
                    val item = resp.getJSONObject(i)
                    val artist = Artist(id =  item.getString("id"), name = item.getString("name"),
                        image = item.getString("image"), description = item.getString("description"))
                    list.add(i, artist)
                }
                cont.resume(list)
            },
            {
                cont.resumeWithException(it)
            }
        ))
    }

    suspend fun getCollectors() = suspendCoroutine<List<Collector>> { cont ->
        requestQueue.add(getRequest("collectors",
            { response ->
                val resp = JSONArray(response)
                val list = mutableListOf<Collector>()
                for (i in 0 until resp.length()) {
                    val item = resp.getJSONObject(i)
                    val albumsCount = item.getJSONArray("collectorAlbums").length()
                    val collector = Collector(id =  item.getString("id"), name = item.getString("name"),
                        telephone = item.getString("telephone"), email = item.getString("email"),
                        albumsCount = "$albumsCount Albumes")
                    list.add(i, collector)
                }
                cont.resume(list)
            },
            {
                cont.resumeWithException(it)
            }
        ))
    }

    private fun getRequest(path:String, responseListener: Response.Listener<String>, errorListener: Response.ErrorListener): StringRequest {
        return StringRequest(Request.Method.GET, BASE_URL+path, responseListener,errorListener)
    }
}