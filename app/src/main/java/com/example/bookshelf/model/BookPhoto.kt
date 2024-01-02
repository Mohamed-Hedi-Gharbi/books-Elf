package com.example.bookshelf.model

import kotlinx.serialization.Serializable


@Serializable
data class BookPhoto(
    val imageLinks: ImageLinks
)

@Serializable
data class ImageLinks(
    val smallThumbnail: String,
    val thumbnail: String,
    val small: String,
    val medium: String,
    val large: String,
    val extraLarge: String
)
