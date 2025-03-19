package com.dicoding.dicodingeventapp.core.domain.usecase

import com.dicoding.dicodingeventapp.core.data.Resource
import com.dicoding.dicodingeventapp.core.domain.model.Event
import com.dicoding.dicodingeventapp.core.domain.repository.IEventRepository
import kotlinx.coroutines.flow.Flow

class EventInteractor(private val eventRepository: IEventRepository) : EventUseCase {
    override fun getAllEvent(): Flow<Resource<List<Event>>> {
        return eventRepository.getAllEvent()
    }

    override fun getFavoriteEvent(): Flow<List<Event>> {
        return eventRepository.getFavoriteEvent()

    }

    override fun setFavoriteEvent(event: Event, state: Boolean) {
        eventRepository.setFavoriteEvent(event, state)
    }
}