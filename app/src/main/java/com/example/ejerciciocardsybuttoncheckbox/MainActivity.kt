package com.example.ejerciciocardsybuttoncheckbox

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.ejerciciocardsybuttoncheckbox.componentes.EjercicioButtonCheckBoxScreen
import com.example.ejerciciocardsybuttoncheckbox.componentes.EjercicioCardsScreen
import com.example.ejerciciocardsybuttoncheckbox.ui.theme.EjercicioCardsYButtonCheckBoxTheme
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            EjercicioCardsYButtonCheckBoxTheme {
                Greeting()
            }
        }
    }
}

@Composable
fun Greeting(modifier: Modifier = Modifier) {
    //EjercicioButtonCheckBoxScreen(modifier)
    EjercicioCardsScreen(modifier)
}
