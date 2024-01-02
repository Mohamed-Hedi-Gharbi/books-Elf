package com.example.bookshelf.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.bookshelf.BookPhotosApplication
import com.example.bookshelf.data.BookPhotosRepository
import com.example.bookshelf.model.BookPhoto
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException


sealed interface BookUiState {
    data class Success(val photos: List<BookPhoto>) : BookUiState
    object Error : BookUiState
    object Loading : BookUiState
}

class BookViewModel(private val bookPhotosRepository: BookPhotosRepository) : ViewModel() {
    var bookUiState: BookUiState by mutableStateOf(BookUiState.Loading)
        private set

    init {
        getBookPhotos()    }


    fun getBookPhotos() {
        viewModelScope.launch {
            bookUiState = BookUiState.Loading
            bookUiState = try {
                BookUiState.Success(bookPhotosRepository.getBookPhotos())
            } catch (e: IOException) {
                BookUiState.Error
            } catch (e: HttpException) {
                BookUiState.Error
            }
        }
    }


    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as BookPhotosApplication)
                val bookPhotosRepository = application.container.bookPhotosRepository
                BookViewModel(bookPhotosRepository = bookPhotosRepository)
            }
        }
    }
}
