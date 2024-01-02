
package com.example.bookshelf.network

import com.example.bookshelf.model.BookPhoto
import com.example.bookshelf.model.ImageLinks
import kotlinx.serialization.Serializable
import retrofit2.http.GET
import retrofit2.http.Query

interface BookApiService {

    @GET("volumes")
    suspend fun getBooksWithImages(@Query("q") query: String): BookResponse
}

@Serializable
data class BookResponse(
    val items: List<BookItem>
)

@Serializable
data class BookItem(
    val id: String,
    val volumeInfo: VolumeInfo
)

@Serializable
data class VolumeInfo(
    val title: String,
    val authors: List<String>,
    val imageLinks: ImageLinks
)