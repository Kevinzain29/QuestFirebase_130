package com.example.firebase_130.repository

import com.example.firebase_130.model.Mahasiswa
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class NetworkRepository(
    private val firestore: FirebaseFirestore
): RepositoryMhs {
    override suspend fun insertMhs(mahasiswa: Mahasiswa) {
        TODO("Not yet implemented")
    }

    override fun getAllMahasiswa(): Flow<List<Mahasiswa>> = callbackFlow {
        //membuka collection dari firestore
        val mhsCollection = firestore.collection("Mahasiswa")
            .orderBy("nim", Query.Direction.ASCENDING)
            .addSnapshotListener { value, error ->
                if (value != null) {
                    val mhsList = value.documents.mapNotNull {
                        //convert dari document firestore ke data class
                        it.toObject(Mahasiswa::class.java)!!
                    }
                    trySend(mhsList)
                }
            }
        awaitClose {
            //menutup collection dari firebase
            mhsCollection.remove()
        }
    }

    override fun getMahasiswa(nim: String): Flow<Mahasiswa> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteMahasiswa(mahasiswa: Mahasiswa) {
        TODO("Not yet implemented")
    }

    override suspend fun updateMahasiswa(mahasiswa: Mahasiswa) {
        TODO("Not yet implemented")
    }
}