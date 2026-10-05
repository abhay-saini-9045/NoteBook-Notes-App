package com.example.noteapp.ui.screens

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import com.example.noteapp.MainActivity
import com.example.noteapp.R
import com.example.noteapp.ui.theme.Grey
import com.example.noteapp.ui.theme.NoteAppTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            delay(2000)
            startActivity(
                Intent(
                    this@SplashScreen,
                    MainActivity::class.java
                )
            )
            finish()
        }

        enableEdgeToEdge()
        setContent {
            NoteAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(bottom = 16.dp)
                                .fillMaxSize()
                                .align(Alignment.Center)
                                .offset(y = (-30).dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Image(
                                painter = painterResource(R.drawable.splash_screen_icon),
                                contentDescription = "Logo",
                                modifier = Modifier.size(230.dp)

                            )

                            Text(
                                text = "NoteBook",
                                fontSize = 35.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color(0xFF6C63B5)
                            )

                            Spacer(modifier = Modifier.height(1.dp))

                            Text(
                                text = "All your notes, one place.",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color.Gray
                            )
                        }


                        Text(
                            text = "Developed by Abhay",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Grey,
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                                .padding(bottom = 30.dp)
                        )
                    }
                }

            }
        }
    }

}