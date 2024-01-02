package com.example.bookshelf.data

import com.example.bookshelf.network.BookApiService
import retrofit2.Retrofit
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType


interface AppContainer {
    val bookPhotosRepository: BookPhotosRepository
}


class DefaultAppContainer : AppContainer {
    private val baseUrl = "https://www.googleapis.com/books/v1/"


    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()


    private val retrofitService: BookApiService by lazy {
        retrofit.create(BookApiService::class.java)
    }

    override val bookPhotosRepository: BookPhotosRepository by lazy {
        NetworkMarsPhotosRepository(retrofitService)
    }
}
