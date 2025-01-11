package com.example.firebase_130.repository

import com.example.firebase_130.model.Mahasiswa
import kotlinx.coroutines.flow.Flow

interface RepositoryMhs {
    suspend fun insertMhs(mahasiswa: Mahasiswa)
    fun getAllMahasiswa(): Flow<List<Mahasiswa>>
    fun getMahasiswa(nim: String): Flow<Mahasiswa>
    suspend fun deleteMahasiswa(mahasiswa: Mahasiswa)
    suspend fun updateMahasiswa(mahasiswa: Mahasiswa)
}