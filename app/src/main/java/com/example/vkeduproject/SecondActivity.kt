package com.example.vkeduproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.vkeduproject.ui.theme.VkEduProjectTheme

class SecondActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val text = intent.getStringExtra("USER_INPUT") ?: "Ничего не передали"
        setContent{
            SecondScreen(text = text)
        }
    }

}

@Composable
fun SecondScreen(text: String){
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Text(text = text)
    }
}

@Preview(showBackground = true)
@Composable
fun SecondPreview() {
    VkEduProjectTheme {
        SecondScreen("Привет")
    }
}