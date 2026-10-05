package com.example.noteapp.util
import androidx.compose.ui.graphics.Color
object ColorUtil {
    private val colors = listOf(

        0xFFEFB8C8,
        0xFFEBD4C6,
        0xFFD0BCFF

    )
    fun getRandomColor() : Long {
        return colors.random()
    }
}