package com.example.sergiom_tresenraya

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class TicTacToeView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : View(context, attrs) {



    interface GameListener {
        fun juegoTermina(ganador: Int)
        fun cambioTurno(player: Int)
    }

    var listener: GameListener? = null



    private val paintGrid = Paint().apply {
        color = Color.MAGENTA
        strokeWidth = 12f
        style = Paint.Style.STROKE
        isAntiAlias = true
    }


    private val paintX = Paint().apply {
        color = Color.RED
        strokeWidth = 16f
        style = Paint.Style.STROKE
        isAntiAlias = true
    }


    private val paintO = Paint().apply {
        color = Color.BLUE
        strokeWidth = 16f
        style = Paint.Style.STROKE
        isAntiAlias = true
    }


    private val board = Array(3) { IntArray(3) }


    private var jugadorActual = 1

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val w = width.toFloat()
        val h = height.toFloat()
        val cw = w / 3
        val ch = h / 3


        for (i in 1..2) {
            canvas.drawLine(cw * i, 12f, cw * i, h, paintGrid)
            canvas.drawLine(12f, ch * i, w, ch * i, paintGrid)
        }
        canvas.drawRect(12f, 12f, w, h, paintGrid)


        for (row in 0..2) {
            for (col in 0..2) {
                val left = col * cw
                val top = row * ch
                val right = left + cw
                val bottom = top + ch

                when (board[row][col]) {
                    1 -> drawX(canvas, left, top, right, bottom)
                    2 -> drawO(canvas, left, top, right, bottom)
                }
            }
        }
    }


    private fun drawX(canvas: Canvas, left: Float, top: Float, right: Float, bottom: Float) {
        val margin = 40f
        canvas.drawLine(left + margin, top + margin, right - margin, bottom - margin, paintX)
        canvas.drawLine(left + margin, bottom - margin, right - margin, top + margin, paintX)
    }


    private fun drawO(canvas: Canvas, left: Float, top: Float, right: Float, bottom: Float) {
        val radius = (right - left) / 2.5f
        canvas.drawCircle(
            (left + right) / 2,
            (top + bottom) / 2,
            radius,
            paintO
        )
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {

            val cw = width / 3
            val ch = height / 3

            val col = (event.x / cw).toInt()
            val row = (event.y / ch).toInt()

            if (row in 0..2 && col in 0..2) {

                if (board[row][col] == 0) {

                    board[row][col] = jugadorActual

                    val result = quienGana()

                    if (result != 0) {
                        // ↑ Hemos detectado ganador o empate
                        listener?.juegoTermina(result)
                    } else {
                        jugadorActual = if (jugadorActual == 1) 2 else 1

                        listener?.cambioTurno(jugadorActual)
                    }

                    invalidate()
                }
            }
        }
        return true
    }



    fun quienGana(): Int {

        // Filas
        for (r in 0..2) {
            if (board[r][0] != 0 &&
                board[r][0] == board[r][1] &&
                board[r][1] == board[r][2]) {
                return board[r][0]
            }
        }

        // Columnas
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

        // Diagonal inversa
        if (board[0][2] != 0 &&
            board[0][2] == board[1][1] &&
            board[1][1] == board[2][0]) {
            return board[0][2]
        }

        // ¿Empate?
        var emptyFound = false
        for (r in 0..2)
            for (c in 0..2)
                if (board[r][c] == 0) emptyFound = true

        if (!emptyFound) return 3

        return 0
    }


    fun restablecer() {
        for (r in 0..2) for (c in 0..2) board[r][c] = 0
        jugadorActual = 1
        invalidate()
    }
}
