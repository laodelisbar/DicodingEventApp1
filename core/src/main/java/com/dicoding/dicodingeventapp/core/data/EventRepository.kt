package com.dicoding.dicodingeventapp.core.data

import com.dicoding.dicodingeventapp.core.data.source.local.LocalDataSource
import com.dicoding.dicodingeventapp.core.data.source.remote.RemoteDataSource
import com.dicoding.dicodingeventapp.core.data.source.remote.network.ApiResponse
import com.dicoding.dicodingeventapp.core.data.source.remote.response.EventResponse
import com.dicoding.dicodingeventapp.core.domain.model.Event
import com.dicoding.dicodingeventapp.core.domain.repository.IEventRepository
import com.dicoding.dicodingeventapp.core.utils.AppExecutors
import com.dicoding.dicodingeventapp.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class EventRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IEventRepository {

    override fun getAllEvent(): Flow<Resource<List<Event>>> =
        object : NetworkBoundResource<List<Event>, List<EventResponse>>() {
            override fun loadFromDB(): Flow<List<Event>> {
                return localDataSource.getAllEvent().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }
            override fun shouldFetch(data: List<Event>?): Boolean =
                data.isNullOrEmpty() // mengambil data dari internet hanya jika data di database kosong
//                 true // ganti dengan true jika ingin selalu mengambil data dari internet

            override suspend fun createCall(): Flow<ApiResponse<List<EventResponse>>> =
                remoteDataSource.getAllEvent()

            override suspend fun saveCallResult(data: List<EventResponse>) {
                val eventList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertEvent(eventList)
            }
        }.asFlow()

    override fun getFavoriteEvent(): Flow<List<Event>> {
        return localDataSource.getFavoriteEvent().map {
           DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteEvent(event: Event, state: Boolean) {
        val tourismEntity = DataMapper.mapDomainToEntity(event)
        appExecutors.diskIO().execute { localDataSource.setFavoriteEvent(tourismEntity, state) }
    }
}

