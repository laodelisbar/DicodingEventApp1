package com.dicoding.dicodingeventapp.core.domain.repository

import com.dicoding.dicodingeventapp.core.data.Resource
import com.dicoding.dicodingeventapp.core.domain.model.Event
import kotlinx.coroutines.flow.Flow

interface IEventRepository {

    fun getAllEvent(): Flow<Resource<List<Event>>>

    fun getFavoriteEvent(): Flow<List<Event>>

    fun setFavoriteEvent(event: Event, state: Boolean)

}