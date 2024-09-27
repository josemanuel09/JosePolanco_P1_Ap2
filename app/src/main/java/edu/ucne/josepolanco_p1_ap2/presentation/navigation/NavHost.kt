package edu.ucne.josepolanco_p1_ap2.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import edu.ucne.josepolanco_p1_ap2.presentation.navigation.Ventas.DeleteVentaScreen
import edu.ucne.josepolanco_p1_ap2.presentation.navigation.Ventas.EditVentaScreen
import edu.ucne.josepolanco_p1_ap2.presentation.navigation.Ventas.VentaListScreen
import edu.ucne.josepolanco_p1_ap2.presentation.navigation.Ventas.VentaScreen

@Composable
fun NavHostExam(
    navHostController: NavHostController
) {
    val scope = rememberCoroutineScope()
    NavHost(navController = navHostController, startDestination = Screen.ListScreen) {

        composable<Screen.ListScreen> {
            VentaListScreen(
                scope= scope ,
                onCreateVenta = {
                    navHostController.navigate(Screen.RegistroScreen(0))
                },
                onEditVenta = { venta ->
                    navHostController.navigate(Screen.EditScreen(venta))
                },
                onDeleteVenta = { venta ->
                    navHostController.navigate(Screen.DeleteScreen(venta))
                }
            )
        }
        composable<Screen.RegistroScreen> {
            val args = it.toRoute<Screen.RegistroScreen>()
            VentaScreen (
                goBack = {
                    navHostController.navigateUp()

                }
            )
        }

        composable<Screen.EditScreen> {
            val args = it.toRoute<Screen.EditScreen>()
            EditVentaScreen(
                VentaId = args.VentaId,
                goBack = {
                    navHostController.navigateUp()
                }
            )
        }


        composable<Screen.DeleteScreen> {
            val args = it.toRoute<Screen.DeleteScreen>()
            DeleteVentaScreen(
                VentaId = args.VentaId,
                goBack = {
                    navHostController.navigateUp()
                }
            )
        }
    }
}
