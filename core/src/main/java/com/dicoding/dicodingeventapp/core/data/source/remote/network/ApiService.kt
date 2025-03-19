package com.dicoding.dicodingeventapp.core.data.source.remote.network

import com.dicoding.dicodingeventapp.core.data.source.remote.response.ListEventResponse
import retrofit2.http.GET

interface ApiService {

    @GET("events")
    suspend fun getEvents(
    ): ListEventResponse
}
