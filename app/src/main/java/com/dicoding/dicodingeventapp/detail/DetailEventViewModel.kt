package com.dicoding.dicodingeventapp.detail

import androidx.lifecycle.ViewModel
import com.dicoding.dicodingeventapp.core.domain.model.Event
import com.dicoding.dicodingeventapp.core.domain.usecase.EventUseCase

class DetailEventViewModel(private val eventUseCase: EventUseCase) : ViewModel() {
    fun setFavoriteEvent(event: Event, newStatus:Boolean) =
        eventUseCase.setFavoriteEvent(event, newStatus)
}

