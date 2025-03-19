package com.dicoding.dicodingeventapp.core.utils

import com.dicoding.dicodingeventapp.core.data.source.local.entity.EventEntity
import com.dicoding.dicodingeventapp.core.data.source.remote.response.EventResponse
import com.dicoding.dicodingeventapp.core.domain.model.Event

object DataMapper {
    fun mapResponsesToEntities(input: List<EventResponse>): List<EventEntity> {
        val eventList = ArrayList<EventEntity>()
        input.map {
            val event = EventEntity(
                id = it.id,
                description = it.description,
                name = it.name,
                summary = it.summary,
                mediaCover = it.mediaCover,
                imageLogo = it.imageLogo,
                registrants = it.registrants,
                category = it.category,
                beginTime = it.beginTime,
                cityName = it.cityName,
                endTime = it.endTime,
                quota = it.quota,
                ownerName = it.ownerName,
                link = it.link,
                isFavorite = false,
            )
            eventList.add(event)
        }
        return eventList
    }

    fun mapEntitiesToDomain(input: List<EventEntity>): List<Event> =
        input.map {
            Event(
                id = it.id,
                description = it.description,
                name = it.name,
                summary = it.summary,
                mediaCover = it.mediaCover,
                imageLogo = it.imageLogo,
                registrants = it.registrants,
                category = it.category,
                beginTime = it.beginTime,
                cityName = it.cityName,
                endTime = it.endTime,
                quota = it.quota,
                ownerName = it.ownerName,
                link = it.link,
                isFavorite = it.isFavorite,
            )
        }

    fun mapDomainToEntity(input: Event) = EventEntity(
        id = input.id,
        description = input.description,
        name = input.name,
        summary = input.summary,
        mediaCover = input.mediaCover,
        imageLogo = input.imageLogo,
        registrants = input.registrants,
        category = input.category,
        beginTime = input.beginTime,
        cityName = input.cityName,
        endTime = input.endTime,
        quota = input.quota,
        ownerName = input.ownerName,
        link = input.link,
        isFavorite = input.isFavorite,
    )
}