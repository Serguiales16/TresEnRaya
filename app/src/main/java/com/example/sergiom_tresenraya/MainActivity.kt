package com.example.sergiom_tresenraya

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.sergiom_tresenraya.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityMainBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.tablero)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val ticTacToeView = findViewById<Tablero>(R.id.tablero)



        binding.btnReset.setOnClickListener {
            ticTacToeView.restablecer()
            binding.estado.text = "Turno: X"
        }


        ticTacToeView.listener = object : Tablero.GameListener {

            override fun juegoTermina(ganador: Int) {
                val mensajeFinal = when (ganador) {
                    1 -> "GANA X"
                    2 -> "GANA 0!"
                    3 -> "EMPATE"
                    else -> ""
                }

                Toast.makeText(this@MainActivity, mensajeFinal, Toast.LENGTH_SHORT).show()
                binding.estado.text = mensajeFinal

                ticTacToeView.restablecer()
                binding.estado.text = "Turno: X"
            }

            override fun cambioTurno(player: Int) {
                val txt = if (player == 1) "Turno: X" else "Turno: O"
                binding.estado.text = txt
            }
        }






    }
}