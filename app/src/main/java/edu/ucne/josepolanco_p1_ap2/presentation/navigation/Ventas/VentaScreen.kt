package edu.ucne.josepolanco_p1_ap2.presentation.navigation.Ventas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun VentaScreen(viewModel: VentaViewModel = hiltViewModel(), goBack: () -> Unit) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    VentaBodyScreen(
        uiState = uiState.value,
        onEmpresaChange = viewModel::onEmpresaChange,
        onGalonesChange = viewModel::onGalonesChange,
        onDescuentoGalonChange = viewModel::onDescuentoGalonChange,
        onTotalDescontadoChange = viewModel::onTotalDescontadoChange,
        onTotalChange = viewModel::onTotalChange,
        onPrecioChange = viewModel::onPrecioChange,

        save = { viewModel.save(goBack) },
        nuevo = viewModel::nuevo,
        goBack = goBack
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VentaBodyScreen(
    uiState: VentaViewModel.UiState,
    onEmpresaChange: (String) -> Unit,
    onGalonesChange: (Double) -> Unit,
    onDescuentoGalonChange: (Double) -> Unit,
    onPrecioChange: (Double) -> Unit,
    onTotalDescontadoChange: (Double) -> Unit,
    onTotalChange: (Double) -> Unit,
    save: () -> Unit,
    nuevo: () -> Unit,
    goBack: () -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Registro de Ventas",
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    )
                },
                navigationIcon = {
                    IconButton(onClick = goBack) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Regresar")
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0xFF6200EE)
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = "Nombre de la Empresa") },
                value = uiState.nEmpresa,
                onValueChange = onEmpresaChange
            )

            if (uiState.MostrarError && uiState.nEmpresa.isBlank()) {
                Text(
                    text = "El nombre de la empresa no puede estar vacío",
                    color = Color.Red,
                    style = TextStyle(fontSize = 14.sp)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = "Galones") },
                value = if (uiState.galones == 0.0) "" else uiState.galones.toString(),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                ),
                onValueChange = { newValue ->
                    val galones = newValue.toDoubleOrNull() ?: 0.0
                    onGalonesChange(galones)
                }
            )

            if (uiState.MostrarError && uiState.galones <= 0) {
                Text(
                    text = "Los galones deben ser mayores que 0",
                    color = Color.Red,
                    style = TextStyle(fontSize = 14.sp)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = "Descuento por Galón") },
                value = if (uiState.descuento == 0.0) "" else uiState.descuento.toString(),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                ),
                onValueChange = { newValue ->
                    val descuento = newValue.toDoubleOrNull() ?: 0.0
                    onDescuentoGalonChange(descuento)
                }
            )

            if (uiState.MostrarError && uiState.descuento <= 0) {
                Text(
                    text = "El descuento debe ser mayor o igual a 0",
                    color = Color.Red,
                    style = TextStyle(fontSize = 14.sp)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = "Precio") },
                value = if (uiState.precio == 0.0) "" else uiState.precio.toString(),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                ),
                onValueChange = { newValue ->
                    val precio = newValue.toDoubleOrNull() ?: 0.0
                    onPrecioChange(precio)
                }
            )

            if (uiState.MostrarError && uiState.precio <= 0) {
                Text(
                    text = "El precio debe ser mayor que 0",
                    color = Color.Red,
                    style = TextStyle(fontSize = 14.sp)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = "Total Descontado") },
                value = if (uiState.totalDescontado == 0.0) "" else uiState.totalDescontado.toString(),
                readOnly = true,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                ),
                onValueChange = { newValue ->
                    val totald = newValue.toDoubleOrNull() ?: 0.0
                    onTotalDescontadoChange(totald)
                }
            )
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = "Total") },
                readOnly = true,
                value = if (uiState.total == 0.0) "" else uiState.total.toString(),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                ),
                onValueChange = { newValue ->
                    val total = newValue.toDoubleOrNull() ?: 0.0
                    onTotalChange(total)
                }
            )
            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedButton(
                    modifier = Modifier.weight(1f),
                    onClick = { save() }
                ) {
                    Text(text = "Guardar")
                    Icon(Icons.Filled.Add, contentDescription = "Guardar")
                }

                OutlinedButton(
                    modifier = Modifier.weight(1f),
                    onClick = { nuevo() }
                ) {
                    Text(text = "Nuevo")
                    Icon(Icons.Filled.Refresh, contentDescription = "Nuevo")
                }
            }



        }
    }
}

