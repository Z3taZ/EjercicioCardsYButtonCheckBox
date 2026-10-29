package com.example.ejerciciocardsybuttoncheckbox.componentes

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun EjercicioButtonCheckBoxScreen(modifier: Modifier = Modifier) {

    var mensaje by remember { mutableStateOf("Pulsa el botón para empezar") }
    var mostrarCargando by remember { mutableStateOf(false) }
    var checkboxActivar by remember { mutableStateOf(false) }
    var switchMostrarRadio by remember { mutableStateOf(false) }
    var opcionSeleccionada by remember { mutableStateOf("Opción A") }

    var indiceImagen by remember { mutableStateOf(0) }
    val listaImagenes = listOf(
        "https://images.unsplash.com/photo-1552728089-57bdde30beb3?w=400", // Loro
        "https://images.unsplash.com/photo-1514888286974-6c03e2ca1dba?w=400", // Gato
        "https://images.unsplash.com/photo-1543466835-00a7907e9de1?w=400"  // Perro
    )

    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Práctica Button CheckBox",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = "Icono decorativo de estrella",
                tint = MaterialTheme.colorScheme.primary
            )
            Text(text = "Icono decorativo siempre visible")
        }

        HorizontalDivider()

        Button(
            onClick = {
                mensaje = "Botón presionado"
                indiceImagen = (indiceImagen + 1) % listaImagenes.size
                mostrarCargando = true

                coroutineScope.launch {
                    delay(5000L)
                    mostrarCargando = false
                }
            }
        ) {
            Text("Presionar")
        }

        if (mostrarCargando) {
            CircularProgressIndicator()
            Text(text = "Cargando durante 5 segundos...", style = MaterialTheme.typography.bodySmall)
        }

        Card(
            modifier = Modifier.size(160.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    model = listaImagenes[indiceImagen],
                    contentDescription = "Imagen de animal de internet",
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(12.dp)),
                    contentScale = ContentScale.Crop
                )
            }
        }

        HorizontalDivider()

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = checkboxActivar,

                onCheckedChange = { checkboxActivar = it }
            )
            Text(text = "Activar")
        }

        if (checkboxActivar) {
            Card(
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Mensaje: $mensaje",
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Medium
                )
            }
        }

        HorizontalDivider()

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Mostrar grupo de opciones")
            Switch(
                checked = switchMostrarRadio,
                onCheckedChange = { switchMostrarRadio = it }
            )
        }

        if (switchMostrarRadio) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(text = "Selecciona una opción:", fontWeight = FontWeight.Bold)

                val opciones = listOf("Opción A", "Opción B", "Opción C")

                opciones.forEach { opcion ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        RadioButton(
                            selected = (opcionSeleccionada == opcion),
                            onClick = {
                                opcionSeleccionada = opcion
                                mensaje = "Seleccionado: $opcion"
                            }
                        )
                        Text(
                            text = opcion,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                }
            }
        }
    }
}
