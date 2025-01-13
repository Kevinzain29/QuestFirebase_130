package com.example.firebase_130.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firebase_130.model.Mahasiswa
import com.example.firebase_130.repository.RepositoryMhs
import kotlinx.coroutines.launch

class InsertViewModel(
    private val mhs: RepositoryMhs
) : ViewModel() {
    var uiEvent: InsertUiState by mutableStateOf(InsertUiState())
        private set
    var uiState: FormState by mutableStateOf(FormState.Idle)
        private set
    //memperbarui state berdasarkan input pengguna
    fun updateState(mahasiswaEvent: MahasiswaEvent) {
        uiEvent = uiEvent.copy(
            insertUiEvent = mahasiswaEvent
        )
    }
    //validasi data input pengguna
    fun validateFields(): Boolean {
        val event = uiEvent.insertUiEvent
        val errorState = FormErrorState(
            nim = if (event.nim.isNotEmpty()) null else "NIM tidak boleh kosong",
            nama = if (event.nama.isNotEmpty()) null else "nama tidak boleh kosong",
            alamat = if (event.alamat.isNotEmpty()) null else "alamat tidak boleh kosong",
            gender = if (event.gender.isNotEmpty()) null else "gender tidak boleh kosong",
            kelas = if (event.kelas.isNotEmpty()) null else "kelas tidak boleh kosong",
            angkatan = if (event.angkatan.isNotEmpty()) null else "angkatan tidak boleh kosong",
            judulskripsi = if (event.judulskripsi.isNotEmpty()) null else "judulskripsi tidak boleh kosong",
            dospem1 = if (event.dospem1.isNotEmpty()) null else "dospem1 tidak boleh kosong",
            dospem2 = if (event.dospem2.isNotEmpty()) null else "dospem2 tidak boleh kosong"
        )
        uiEvent = uiEvent.copy(isEntryValid = errorState)
        return errorState.isValid()
    }
    fun insertMhs() {
        if (validateFields()) {
            viewModelScope.launch {
                uiState = FormState.Loading
                try {
                    mhs.insertMhs(uiEvent.insertUiEvent.toMhsModel())
                    uiState = FormState.Success("Data berhasil disimpan")
                } catch (e: Exception) {
                    uiState = FormState.Error("Data gagal disimpan")
                }
            }
        } else {
            uiState = FormState.Error("Data tidak valid")
        }
    }
    fun resetForm() {
        uiEvent = InsertUiState()
        uiState = FormState.Idle
    }
    fun resetSnackBarMessage() {
        uiState = FormState.Idle
    }
}

sealed class FormState {
    object Idle : FormState()
    object Loading : FormState()
    data class Success(val message: String) : FormState()
    data class Error(val message: String) : FormState()
}

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
    val angkatan: String? = null,
    val judulskripsi: String? = null,
    val dospem1: String? = null,
    val dospem2: String? = null
) {
    fun isValid(): Boolean {
        return nim == null && nama == null && alamat == null &&
                gender == null && kelas == null && angkatan == null &&
                judulskripsi == null && dospem1 == null && dospem2 == null
    }
}

//data class variabel yg menyimpan data input form
data class MahasiswaEvent(
    val nim: String = "",
    val nama: String= "",
    val alamat: String= "",
    val gender: String= "",
    val kelas: String= "",
    val angkatan: String= "",
    val judulskripsi: String= "",
    val dospem1: String= "",
    val dospem2: String= ""
)
//menyimpan input form ke dalam entity
fun MahasiswaEvent.toMhsModel(): Mahasiswa = Mahasiswa(
    nim = nim,
    nama = nama,
    alamat = alamat,
    gender = gender,
    kelas = kelas,
    angkatan = angkatan,
    judulskripsi = judulskripsi,
    dospem1 = dospem1,
    dospem2 = dospem2
)


