package com.example.firebase_130.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firebase_130.model.Mahasiswa
import com.example.firebase_130.repository.RepositoryMhs
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repoMhs: RepositoryMhs
) : ViewModel() {
    var mhsUiState: HomeUiState by mutableStateOf(HomeUiState.Loading)
        private set

    init {
        getMhs()
    }

    fun getMhs() {
        viewModelScope.launch {
            Log.d("Homeviewmodel","1")
            repoMhs.getAllMahasiswa().onStart {
                mhsUiState = HomeUiState.Loading
                Log.d("Homeviewmodel","2")
            }
                .catch {
                    mhsUiState = HomeUiState.Error(e = it)
                    Log.d("Homeviewmodel","3")
                }
                .collect{
                    mhsUiState = if (it.isEmpty()) {
                        Log.d("Homeviewmodel","4")
                        HomeUiState.Error(Exception("belum ada data mahasiswa"))
                    } else {
                        Log.d("Homeviewmodel","5")
                        HomeUiState.Success(it)
                    }
                }
        }
    }
}

sealed class HomeUiState {
    object Loading : HomeUiState()
    data class Success(val data: List<Mahasiswa>) : HomeUiState()
    data class Error(val e: Throwable) : HomeUiState()
}