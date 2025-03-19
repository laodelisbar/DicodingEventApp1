package com.dicoding.dicodingeventapp.core.data.source.remote

import android.util.Log
import com.dicoding.dicodingeventapp.core.data.source.remote.network.ApiResponse
import com.dicoding.dicodingeventapp.core.data.source.remote.network.ApiService
import com.dicoding.dicodingeventapp.core.data.source.remote.response.EventResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getAllEvent(): Flow<ApiResponse<List<EventResponse>>> {
        return flow {
            try {
                val response = apiService.getEvents()
                val dataArray = response.listEvents
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.listEvents))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}

