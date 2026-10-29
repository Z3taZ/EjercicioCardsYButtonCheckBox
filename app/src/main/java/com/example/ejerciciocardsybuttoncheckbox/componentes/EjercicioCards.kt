package com.example.ejerciciocardsybuttoncheckbox.componentes

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp


class Tarea(
    val titulo: String,
    var completada: Boolean,
    var prioridad: String // "Alta", "Media" o "Baja"
)

@Composable
fun EjercicioCardsScreen(modifier: Modifier = Modifier) {

    val tarea1 = remember { mutableStateOf(Tarea("Estudiar Kotlin", false, "Alta")) }
    val tarea2 = remember { mutableStateOf(Tarea("Hacer interfaces", false, "Media")) }
    val tarea3 = remember { mutableStateOf(Tarea("Comprar pan", true, "Baja")) }
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            text = "Lista de Tareas",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "Tareas Pendientes",
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        if (!tarea1.value.completada) {
            TarjetaDeTarea(
                tarea = tarea1.value,
                onCambiarEstado = { tarea1.value = Tarea(tarea1.value.titulo, !tarea1.value.completada, tarea1.value.prioridad) },
                onCambiarPrioridad = { nuevaPrio -> tarea1.value = Tarea(tarea1.value.titulo, tarea1.value.completada, nuevaPrio) }
            )
        }
        if (!tarea2.value.completada) {
            TarjetaDeTarea(
                tarea = tarea2.value,
                onCambiarEstado = { tarea2.value = Tarea(tarea2.value.titulo, !tarea2.value.completada, tarea2.value.prioridad) },
                onCambiarPrioridad = { nuevaPrio -> tarea2.value = Tarea(tarea2.value.titulo, tarea2.value.completada, nuevaPrio) }
            )
        }
        if (!tarea3.value.completada) {
            TarjetaDeTarea(
                tarea = tarea3.value,
                onCambiarEstado = { tarea3.value = Tarea(tarea3.value.titulo, !tarea3.value.completada, tarea3.value.prioridad) },
                onCambiarPrioridad = { nuevaPrio -> tarea3.value = Tarea(tarea3.value.titulo, tarea3.value.completada, nuevaPrio) }
            )
        }


        Spacer(modifier = Modifier.height(10.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Tareas Completadas",
            fontWeight = FontWeight.Bold,
            color = Color.Gray
        )

        if (tarea1.value.completada) {
            TarjetaDeTarea(
                tarea = tarea1.value,
                onCambiarEstado = { tarea1.value = Tarea(tarea1.value.titulo, !tarea1.value.completada, tarea1.value.prioridad) },
                onCambiarPrioridad = { nuevaPrio -> tarea1.value = Tarea(tarea1.value.titulo, tarea1.value.completada, nuevaPrio) }
            )
        }
        if (tarea2.value.completada) {
            TarjetaDeTarea(
                tarea = tarea2.value,
                onCambiarEstado = { tarea2.value = Tarea(tarea2.value.titulo, !tarea2.value.completada, tarea2.value.prioridad) },
                onCambiarPrioridad = { nuevaPrio -> tarea2.value = Tarea(tarea2.value.titulo, tarea2.value.completada, nuevaPrio) }
            )
        }
        if (tarea3.value.completada) {
            TarjetaDeTarea(
                tarea = tarea3.value,
                onCambiarEstado = { tarea3.value = Tarea(tarea3.value.titulo, !tarea3.value.completada, tarea3.value.prioridad) },
                onCambiarPrioridad = { nuevaPrio -> tarea3.value = Tarea(tarea3.value.titulo, tarea3.value.completada, nuevaPrio) }
            )
        }
    }
}


@Composable
fun TarjetaDeTarea(
    tarea: Tarea,
    onCambiarEstado: () -> Unit,
    onCambiarPrioridad: (String) -> Unit
) {
    var mostrarMenuEstado by remember { mutableStateOf(false) }
    var mostrarMenuPrioridad by remember { mutableStateOf(false) }

    val colorAlta = Color(0xFFE57373)   // Rojo suave
    val colorMedia = Color(0xFFFFB74D)  // Naranja suave
    val colorBaja = Color(0xFF66BB6A)   // Verde suave (menos chillón)

    val colorPrioridad = when (tarea.prioridad) {
        "Alta" -> colorAlta
        "Media" -> colorMedia
        else -> colorBaja
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = tarea.completada,
                onCheckedChange = { onCambiarEstado() }
            )

            Spacer(modifier = Modifier.width(8.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = tarea.titulo,
                        textDecoration = if (tarea.completada) TextDecoration.LineThrough else TextDecoration.None,
                        color = if (tarea.completada) Color.Gray else Color.Black
                    )

                    BadgedBox(
                        badge = {
                            Badge(containerColor = colorPrioridad)
                        }
                    ) {
                        Surface(
                            shape = RoundedCornerShape(4.dp),
                            color = colorPrioridad.copy(alpha = 0.2f)
                        ) {
                            Text(
                                text = tarea.prioridad,
                                modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp),
                                color = colorPrioridad,
                                style = MaterialTheme.typography.labelSmall
                            )
                        }
                    }
                }
            }

            Box {
                IconButton(onClick = { mostrarMenuEstado = true }) {
                    Icon(Icons.Default.MoreVert, contentDescription = "Menú Estado")
                }

                DropdownMenu(
                    expanded = mostrarMenuEstado,
                    onDismissRequest = { mostrarMenuEstado = false }
                ) {
                    DropdownMenuItem(
                        text = { Text(if (tarea.completada) "Marcar como pendiente" else "Marcar como completada") },
                        onClick = {
                            mostrarMenuEstado = false
                            onCambiarEstado()
                        }
                    )
                }
            }

            Box {
                IconButton(onClick = { mostrarMenuPrioridad = true }) {
                    Icon(Icons.Default.Menu, contentDescription = "Menú Prioridad")
                }

                DropdownMenu(
                    expanded = mostrarMenuPrioridad,
                    onDismissRequest = { mostrarMenuPrioridad = false }
                ) {
                    DropdownMenuItem(
                        text = { Text("Prioridad Alta", color = colorAlta, fontWeight = FontWeight.Bold) },
                        onClick = {
                            mostrarMenuPrioridad = false
                            onCambiarPrioridad("Alta")
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Prioridad Media", color = colorMedia, fontWeight = FontWeight.Bold) },
                        onClick = {
                            mostrarMenuPrioridad = false
                            onCambiarPrioridad("Media")
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Prioridad Baja", color = colorBaja, fontWeight = FontWeight.Bold) },
                        onClick = {
                            mostrarMenuPrioridad = false
                            onCambiarPrioridad("Baja")
                        }
                    )
                }
            }
        }
    }
}
