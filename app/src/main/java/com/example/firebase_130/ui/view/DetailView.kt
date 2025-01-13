package com.example.firebase_130.ui.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.firebase_130.model.Mahasiswa
import com.example.firebase_130.ui.viewmodel.DetailMhsViewModel
import com.example.firebase_130.ui.viewmodel.DetailUiState
import com.example.firebase_130.ui.viewmodel.PenyediaViewModel
import com.example.firebase_130.ui.viewmodel.toMhsModel

@Composable
fun DetailView(
    modifier: Modifier = Modifier,
    viewModel: DetailMhsViewModel = viewModel(factory = PenyediaViewModel.Factory),
    onBack: () -> Unit = {},
    onEditClick: (String) -> Unit = {},
    onDeleteClick: () -> Unit = {}
){
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    onEditClick(viewModel.detailUiState.value.detailUiEvent.nim) },
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(16.dp)
            ){
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit Mahasiswa",
                )


            }
        }
    ) { innerpadding ->
        val detailUiState by viewModel.detailUiState.collectAsState()

        BodyDetailMhs(
            modifier = Modifier.padding(innerpadding),
            detailUiState = detailUiState,
            onDeleteClick = {
                viewModel.deleteMhs()
                onDeleteClick()
            }
        )
    }
}


@Composable
fun BodyDetailMhs(
    modifier: Modifier = Modifier,
    detailUiState: DetailUiState = DetailUiState(),
    onDeleteClick: () -> Unit = {}
){
    var deleteConfirmationRequired by rememberSaveable { mutableStateOf(false) }
    when {
        detailUiState.isLoading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                CircularProgressIndicator()
            }
        }
        detailUiState.isUiEventNotEmpty -> {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ){
                ItemDetailMhs(
                    mahasiswa = detailUiState.detailUiEvent.toMhsModel(),
                    modifier = Modifier
                )
                Spacer(modifier = Modifier.padding(8.dp))
                Button(
                    onClick = {
                        deleteConfirmationRequired = true
                    },
                    modifier = Modifier.fillMaxWidth()
                ){
                    Text(text = "Delete")
                }
            }
        }

        detailUiState.isUiEventEmpty -> {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ){
                Text(
                    text = "Data tidak ditemukan",
                    modifier = Modifier.padding(16.dp),
                )
            }
        }
    }
}

@Composable
fun ItemDetailMhs(
    modifier: Modifier = Modifier,
    mahasiswa: Mahasiswa
){
    Card(
        modifier = modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    ){
        Column(
            modifier = Modifier.padding(16.dp)
        ){
            ComponentDetailMhs(judul = "NIM", isinya = mahasiswa.nim)
            Spacer(modifier = Modifier.padding(4.dp))

            ComponentDetailMhs(judul = "Nama", isinya = mahasiswa.nama)
            Spacer(modifier = Modifier.padding(4.dp))

            ComponentDetailMhs(judul = "alamat", isinya = mahasiswa.alamat)
            Spacer(modifier = Modifier.padding(4.dp))

            ComponentDetailMhs(judul = "gender", isinya = mahasiswa.gender)
            Spacer(modifier = Modifier.padding(4.dp))

            ComponentDetailMhs(judul = "Kelas", isinya = mahasiswa.kelas)
            Spacer(modifier = Modifier.padding(4.dp))

            ComponentDetailMhs(judul = "angkatan", isinya = mahasiswa.angkatan)
            Spacer(modifier = Modifier.padding(4.dp))

            ComponentDetailMhs(judul = "judulskripsi", isinya = mahasiswa.judulskripsi)
            Spacer(modifier = Modifier.padding(4.dp))

            ComponentDetailMhs(judul = "dospem1", isinya = mahasiswa.dospem1)
            Spacer(modifier = Modifier.padding(4.dp))

            ComponentDetailMhs(judul = "dospem2", isinya = mahasiswa.dospem2)

        }
    }
}

@Composable
fun ComponentDetailMhs(
    modifier: Modifier = Modifier,
    judul: String,
    isinya: String,
){
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ){
        Text(
            text = " $judul :",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray


        )
        Text(
            text = isinya, fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
        )
    }
}