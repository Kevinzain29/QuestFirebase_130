package com.example.firebase_130.di

import android.content.Context
import com.example.firebase_130.repository.NetworkRepository
import com.example.firebase_130.repository.RepositoryMhs
import com.google.firebase.firestore.FirebaseFirestore

interface InterfaceContainerApp {
    val repositoryMhs: RepositoryMhs
}

class MahasiswaContainer (private val context: Context): InterfaceContainerApp {
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    override val repositoryMhs: RepositoryMhs by lazy {
        NetworkRepository(firestore)
    }
}

