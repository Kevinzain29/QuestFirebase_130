package com.example.firebase_130.ui.viewmodel

import com.example.firebase_130.model.Mahasiswa

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


