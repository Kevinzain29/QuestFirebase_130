package com.example.firebase_130.ui.viewmodel

import com.example.firebase_130.model.Mahasiswa

data class InsertUiState(
    val insertUiEvent: MahasiswaEvent = MahasiswaEvent(),
    val isEntryValid: FormErrorState = FormErrorState()
)

data class FormErrorState(
    val nim: String? = null,
    val nama: String? = null,
    val alamat: String? = null,
    val gender: String? = null,
    val kelas: String? = null,
    val angkatan: String? = null
) {
    fun isValid(): Boolean {
        return nim == null && nama == null && alamat == null &&
                gender == null && kelas == null && angkatan == null
    }
}

//data class variabel yg menyimpan data input form
data class MahasiswaEvent(
    val nim: String = "",
    val nama: String= "",
    val alamat: String= "",
    val gender: String= "",
    val kelas: String= "",
    val angkatan: String= ""
)
//menyimpan input form ke dalam entity
fun MahasiswaEvent.toMhsModel(): Mahasiswa = Mahasiswa(
    nim = nim,
    nama = nama,
    alamat = alamat,
    gender = gender,
    kelas = kelas,
    angkatan = angkatan
)


