package com.dicoding.dicodingeventapp.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.dicodingeventapp.core.domain.usecase.EventUseCase

class HomeViewModel(eventUseCase: EventUseCase) : ViewModel() {
    val event = eventUseCase.getAllEvent().asLiveData()
}

