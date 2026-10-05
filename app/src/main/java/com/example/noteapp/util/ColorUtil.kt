package com.example.noteapp.util
import androidx.compose.ui.graphics.Color
object ColorUtil {
    private val colors = listOf(

        0xFFEFB8C8, // Dusty Pink
        0xFFEBD4C6, // Soft peach
        0xFFD0BCFF, // Lavender
        0xFFB8D8D8, // Soft Teal
        0xFFB8E0D2, // Mint Green
        0xFFBDE0FE, // Baby Blue
        0xFFFFE5A5, // Soft Butter
        0xFFC6DABF, // Sage Green
        0xFFC5CAE9, // Periwinkle
        0xFFFFCDB2  // Soft Coral

    )
    fun getRandomColor() : Long {
        return colors.random()
    }
}