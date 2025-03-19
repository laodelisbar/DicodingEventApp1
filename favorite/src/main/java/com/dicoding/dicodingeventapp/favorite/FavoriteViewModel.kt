package com.dicoding.dicodingeventapp.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.dicodingeventapp.core.domain.usecase.EventUseCase

class FavoriteViewModel(event: EventUseCase) : ViewModel() {
    val favoriteEvent = event.getFavoriteEvent().asLiveData()
}

