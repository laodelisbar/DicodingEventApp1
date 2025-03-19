package com.dicoding.dicodingeventapp.core.di

import androidx.room.Room
import com.dicoding.dicodingeventapp.core.data.EventRepository
import com.dicoding.dicodingeventapp.core.data.source.local.LocalDataSource
import com.dicoding.dicodingeventapp.core.data.source.local.room.EventDatabase
import com.dicoding.dicodingeventapp.core.data.source.remote.RemoteDataSource
import com.dicoding.dicodingeventapp.core.data.source.remote.network.ApiService
import com.dicoding.dicodingeventapp.core.domain.repository.IEventRepository
import com.dicoding.dicodingeventapp.core.utils.AppExecutors
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<EventDatabase>().eventDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            EventDatabase::class.java, "Event.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://event-api.dicoding.dev/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IEventRepository> {
        EventRepository(
            get(),
            get(),
            get()
        )
    }
}