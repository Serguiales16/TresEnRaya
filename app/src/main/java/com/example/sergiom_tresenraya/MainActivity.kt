package com.example.sergiom_tresenraya

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.sergiom_tresenraya.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding   // Para acceder a los elementos de la vista

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityMainBinding.inflate(layoutInflater) // Carga el layout

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root) // Muestra el layout

        // Ajusta los márgenes de la vista del tablero para evitar que se tape con la barra superior
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.tablero)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Cogemos la vista del tablero
        val tableroVista = findViewById<Tablero>(R.id.tablero)

        // Botón para reiniciar el juego
        binding.reset.setOnClickListener {
            tableroVista.restablecer()     // Limpia el tablero
            binding.estado.text = "Turno: X" // Texto de turno inicial
        }

        // Escuchamos los eventos del tablero (ganar y cambio de turno)
        tableroVista.listener = object : Tablero.GameListener {

            // Este método se llama cuando la partida termina
            override fun juegoTermina(ganador: Int) {

                // Ponemos el mensaje según quién ganó
                val mensajeFinal = when (ganador) {
                    1 -> "GANA X"
                    2 -> "GANA O!"
                    3 -> "EMPATE"
                    else -> ""
                }

                // Enseña mensaje por pantalla
                Toast.makeText(this@MainActivity, mensajeFinal, Toast.LENGTH_SHORT).show()

                // Lo mostramos en el TextView
                binding.estado.text = mensajeFinal

                // Reinicia el tablero después de ganar
                tableroVista.restablecer()
                binding.estado.text = "Turno: X"
            }

            // Se llama cada vez que cambia el turno
            override fun cambioTurno(player: Int) {
                val txt = if (player == 1) "Turno: X" else "Turno: O"
                binding.estado.text = txt   // Actualizamos el texto
            }
        }
    }
}
