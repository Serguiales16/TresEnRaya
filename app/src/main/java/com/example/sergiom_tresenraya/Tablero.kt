package com.example.sergiom_tresenraya

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class Tablero @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : View(context, attrs) {

    // Interfaz para avisar al Activity cuando pasa algo
    interface GameListener {
        fun juegoTermina(ganador: Int)   // Cuando alguien gana o hay empate
        fun cambioTurno(player: Int)     // Cuando cambia el turno
    }

    var listener: GameListener? = null   // Listener que el Activity va a usar


    // Pintura para las líneas del tablero
    private val paintGrid = Paint().apply {
        color = Color.MAGENTA
        strokeWidth = 12f
        style = Paint.Style.STROKE
        isAntiAlias = true
    }

    // Pintura para dibujar la X
    private val paintX = Paint().apply {
        color = Color.RED
        strokeWidth = 16f
        style = Paint.Style.STROKE
        isAntiAlias = true
    }

    // Pintura para dibujar la O
    private val paintO = Paint().apply {
        color = Color.BLUE
        strokeWidth = 16f
        style = Paint.Style.STROKE
        isAntiAlias = true
    }

    // Matriz del juego (3x3)
    private val board = Array(3) { IntArray(3) }

    // Jugador actual (1 = X, 2 = O)
    private var jugadorActual = 1


    // Dibuja todo en pantalla
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val w = width.toFloat()
        val h = height.toFloat()
        val cw = w / 3         // Ancho de una casilla
        val ch = h / 3         // Alto de una casilla

        // Dibujar líneas del tablero
        for (i in 1..2) {
            canvas.drawLine(cw * i, 12f, cw * i, h, paintGrid)
            canvas.drawLine(12f, ch * i, w, ch * i, paintGrid)
        }

        // Marco exterior
        canvas.drawRect(12f, 12f, w, h, paintGrid)

        // Dibujar X y O en cada casilla
        for (row in 0..2) {
            for (col in 0..2) {
                val left = col * cw
                val top = row * ch
                val right = left + cw
                val bottom = top + ch

                when (board[row][col]) {
                    1 -> drawX(canvas, left, top, right, bottom) // Dibuja X
                    2 -> drawO(canvas, left, top, right, bottom) // Dibuja O
                }
            }
        }
    }


    // Dibuja una X dentro de una casilla
    private fun drawX(canvas: Canvas, left: Float, top: Float, right: Float, bottom: Float) {
        val margin = 40f
        canvas.drawLine(left + margin, top + margin, right - margin, bottom - margin, paintX)
        canvas.drawLine(left + margin, bottom - margin, right - margin, top + margin, paintX)
    }

    // Dibuja un círculo (O)
    private fun drawO(canvas: Canvas, left: Float, top: Float, right: Float, bottom: Float) {
        val radius = (right - left) / 2.5f
        canvas.drawCircle(
            (left + right) / 2,
            (top + bottom) / 2,
            radius,
            paintO
        )
    }

    // Detecta toques de pantalla
    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {

            val cw = width / 3
            val ch = height / 3

            val col = (event.x / cw).toInt() // Columna tocada
            val row = (event.y / ch).toInt() // Fila tocada

            if (row in 0..2 && col in 0..2) {

                // Solo podemos jugar si la casilla está vacía
                if (board[row][col] == 0) {

                    // Pone X o O
                    board[row][col] = jugadorActual

                    // Comprueba si alguien gana
                    val result = quienGana()

                    if (result != 0) {
                        // Hay ganador o empate
                        listener?.juegoTermina(result)
                    } else {
                        // Cambiar de jugador
                        jugadorActual = if (jugadorActual == 1) 2 else 1

                        // Avisamos al Activity
                        listener?.cambioTurno(jugadorActual)
                    }

                    invalidate() // Vuelve a dibujar
                }
            }
        }
        return true
    }


    // Revisa si alguien ha ganado
    fun quienGana(): Int {

        // Revisar filas
        for (r in 0..2) {
            if (board[r][0] != 0 &&
                board[r][0] == board[r][1] &&
                board[r][1] == board[r][2]) {
                return board[r][0]   // Devuelve el jugador ganador
            }
        }

        // Revisar columnas
        for (c in 0..2) {
            if (board[0][c] != 0 &&
                board[0][c] == board[1][c] &&
                board[1][c] == board[2][c]) {
                return board[0][c]
            }
        }

        // Diagonal principal
        if (board[0][0] != 0 &&
            board[0][0] == board[1][1] &&
            board[1][1] == board[2][2]) {
            return board[0][0]
        }

        // Diagonal secundaria
        if (board[0][2] != 0 &&
            board[0][2] == board[1][1] &&
            board[1][1] == board[2][0]) {
            return board[0][2]
        }

        // Mirar si queda alguna casilla vacía
        var emptyFound = false
        for (r in 0..2)
            for (c in 0..2)
                if (board[r][c] == 0) emptyFound = true

        // No hay huecos y nadie ganó → empate
        if (!emptyFound) return 3

        return 0 // Aún no gana nadie
    }


    // Reinicia el tablero
    fun restablecer() {
        for (r in 0..2) for (c in 0..2) board[r][c] = 0
        jugadorActual = 1 // Vuelve a jugar X
        invalidate()      // Redibuja el tablero
    }
}
