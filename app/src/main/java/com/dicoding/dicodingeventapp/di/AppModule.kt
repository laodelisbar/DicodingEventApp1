package com.dicoding.dicodingeventapp.di

import com.dicoding.dicodingeventapp.core.domain.usecase.EventInteractor
import com.dicoding.dicodingeventapp.core.domain.usecase.EventUseCase
import com.dicoding.dicodingeventapp.detail.DetailEventViewModel
import com.dicoding.dicodingeventapp.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<EventUseCase> { EventInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailEventViewModel(get()) }
}