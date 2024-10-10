package edu.ucne.josepolanco_p1_ap2.presentation.navigation.Ventas

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.josepolanco_p1_ap2.data.local.entity.VentaEntity
import edu.ucne.josepolanco_p1_ap2.data.repository.VentaRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VentaViewModel @Inject constructor(
    private val ventaRepository: VentaRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    val uiState get() = _uiState.asStateFlow()

    init {
        getVentas()
    }
    private fun limpiarMensaje(){
        viewModelScope.launch {
            delay(5000)
            _uiState.update {
                it.copy(errorMessage = null)
            }
        }
    }


    fun save(goBack: () -> Unit) {
        viewModelScope.launch {
            val state = _uiState.value
            _uiState.update { it.copy(MostrarError = true) }

            when {
                state.nEmpresa.isBlank() -> {
                    _uiState.update {
                        it.copy(errorMessage = "El nombre de la empresa no puede estar vacío")
                    }
                    limpiarMensaje()
                }

                state.galones == null || state.galones <= 0 -> {
                    _uiState.update {
                        it.copy(errorMessage = "Los galones deben ser mayores que 0")
                    }
                    limpiarMensaje()
                }
                state.descuento == null || state.descuento <= 0 ->{
                    _uiState.update {
                        it.copy(errorMessage = "El Descuento por galon debe ser mayor que cero")
                    }
                    limpiarMensaje()
                }
                state.precio == null || state.precio <= 0 -> {
                    _uiState.update {
                        it.copy(errorMessage = "El precio debe ser mayor que 0")
                    }
                    limpiarMensaje()
                }
                else -> {
                    ventaRepository.save(state.toEntity())
                    _uiState.update {
                        it.copy(errorMessage = null, MostrarError = false)
                    }
                    goBack()
                }
            }
        }
    }

    fun nuevo() {
        _uiState.update {
            it.copy(
                ventaId = null,
                nEmpresa = "",
                galones = 0.0,
                descuento = 0.0,
                precio = 0.0,
                totalDescontado = 0.0,
                total = 0.0,
                errorMessage = null,
                MostrarError = false
            )
        }
    }


    fun select(ventaId: Int) {
        viewModelScope.launch {
            if (ventaId > 0) {
                val venta = ventaRepository.find(ventaId)
                _uiState.update {
                    it.copy(
                        ventaId = venta?.ventaId,
                        nEmpresa = venta?.nEmpresa ?: "",
                        galones = venta?.galones ?: 0.0,
                        descuento = venta?.descuento ?: 0.0,
                        precio = venta?.precio ?: 0.0,
                        totalDescontado = venta?.totalDescontado ?: 0.0,
                        total = venta?.total ?: 0.0,
                        errorMessage = null
                    )
                }
            }
        }
    }

    fun delete(goBack: () -> Unit) {
        viewModelScope.launch {
            try {
                ventaRepository.delete(_uiState.value.toEntity())
                _uiState.update {
                    it.copy(errorMessage = null)
                }
                goBack()
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(errorMessage = "Error al eliminar la venta")
                }
            }
        }
    }

    fun getVentas() {
        viewModelScope.launch {
            ventaRepository.getAll().collect { ventas ->
                _uiState.update {
                    it.copy(ventas = ventas)
                }
            }
        }
    }

    fun onEmpresaChange(nEmpresa: String) {
        _uiState.update {
            it.copy(nEmpresa = nEmpresa)
        }
    }

    fun onGalonesChange(galones: Double) {
        _uiState.update {
            it.copy(galones = galones)
        }
        calcularTotal()
    }

    fun onDescuentoGalonChange(descuento: Double) {
        _uiState.update {
            it.copy(descuento = descuento)
        }
        calcularTotal()
    }

    fun onPrecioChange(precio: Double) {
        _uiState.update {
            it.copy(precio = precio)
        }
        calcularTotal()
    }

    fun onTotalDescontadoChange(totaldescontado: Double){
        _uiState.update {
            it.copy(totalDescontado = totaldescontado)
        }
    }

    fun onTotalChange(total: Double){
        _uiState.update {
            it.copy(total = total)
        }
    }

    fun calcularTotal() {
        val galones = _uiState.value.galones
        val descuento = _uiState.value.descuento
        val precio = _uiState.value.precio

        val totalDescontado = galones * descuento
        val total = (galones * precio) - totalDescontado

        _uiState.update {
            it.copy(
                totalDescontado = totalDescontado,
                total = total
            )
        }
    }

    data class UiState(
        val ventaId: Int? = null,
        val nEmpresa: String = "",
        val galones: Double = 0.0,
        val descuento: Double = 0.0,
        val precio: Double = 0.0,
        val totalDescontado: Double = 0.0,
        val total: Double = 0.0,
        val errorMessage: String? = null,
        val ventas: List<VentaEntity> = emptyList(),
        val MostrarError: Boolean = false
    )

    fun UiState.toEntity() = VentaEntity(
        ventaId = ventaId,
        nEmpresa = nEmpresa,
        galones = galones,
        descuento = descuento,
        precio = precio,
        totalDescontado = totalDescontado,
        total = total
    )
}
