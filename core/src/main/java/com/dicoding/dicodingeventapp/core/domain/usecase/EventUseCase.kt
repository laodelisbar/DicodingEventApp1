package com.dicoding.dicodingeventapp.core.domain.usecase

import com.dicoding.dicodingeventapp.core.data.Resource
import com.dicoding.dicodingeventapp.core.domain.model.Event
import kotlinx.coroutines.flow.Flow

interface  EventUseCase {
    fun getAllEvent (): Flow<Resource<List<Event>>>
    fun getFavoriteEvent () : Flow<List<Event>>
    fun setFavoriteEvent (event: Event, state : Boolean)
}