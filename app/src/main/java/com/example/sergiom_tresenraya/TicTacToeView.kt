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

    /**
     *
     */
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


    private var currentPlayer = 1

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
                    board[row][col] = currentPlayer


                    currentPlayer = if (currentPlayer == 1) 2 else 1

                    invalidate()
                }
            }
        }
        return true
    }


    fun resetBoard() {
        for (r in 0..2) for (c in 0..2) board[r][c] = 0
        currentPlayer = 1
        invalidate()
    }
}
