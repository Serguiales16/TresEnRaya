package com.example.sergiom_tresenraya

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class TicTacToeView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : View(context, attrs) {

    private val paint = Paint().apply {
        color = Color.MAGENTA
        strokeWidth = 12f
        style = Paint.Style.STROKE
        isAntiAlias = true
    }



    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val width = width.toFloat()
        val height = height.toFloat()
        val cellWidth = width / 3
        val cellHeight = height / 3

        // Dibujar líneas verticales
        for (i in 1..2) {
            canvas.drawLine(cellWidth * i, 12f, cellWidth * i, height, paint)
        }

        // Dibujar líneas horizontales
        for (i in 1..2) {
            canvas.drawLine(12f, cellHeight * i, width, cellHeight * i, paint)
        }

        canvas.drawRect(12f, 12f, width, height, paint)
    }
}