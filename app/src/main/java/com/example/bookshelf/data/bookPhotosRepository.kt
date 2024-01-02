package com.example.bookshelf.data

import com.example.bookshelf.model.BookPhoto
import com.example.bookshelf.network.BookApiService


interface BookPhotosRepository {
    suspend fun getBookPhotos(): List<BookPhoto>
}


class NetworkMarsPhotosRepository(
    private val bookApiService: BookApiService
) : BookPhotosRepository {
    override suspend fun getBookPhotos(): List<BookPhoto> {
        val bookResponse = bookApiService.getBooksWithImages("android")
        return bookResponse.items.map { bookItem ->
            BookPhoto(
                bookItem.volumeInfo.imageLinks
            )
        }
    }
}
