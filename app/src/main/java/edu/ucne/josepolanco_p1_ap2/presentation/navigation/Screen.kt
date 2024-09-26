package edu.ucne.josepolanco_p1_ap2.presentation.navigation

import kotlinx.serialization.Serializable

sealed class Screen {
    @Serializable
    data object ListScreen : Screen()
    @Serializable
    data class RegistroScreen(val VentaId: Int) : Screen()
    @Serializable
    data class EditScreen(val VentaId: Int) : Screen()
    @Serializable
    data class DeleteScreen(val VentaId: Int) : Screen()


}