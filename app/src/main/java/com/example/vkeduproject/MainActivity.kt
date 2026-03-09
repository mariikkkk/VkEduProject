package com.example.vkeduproject

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Patterns
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.vkeduproject.ui.theme.VkEduProjectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VkEduProjectTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen(){
    val context = LocalContext.current
    var inputText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,

    ){
        BasicTextField(
            value = inputText,
            onValueChange = {newText ->
                inputText = newText
            },
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 1.dp,
                    shape = RoundedCornerShape(12.dp),
                    color = Color.Gray
                )
                .height(32.dp),
            decorationBox = { innerTextField ->
                Box(
                    contentAlignment = Alignment.CenterStart,
                    modifier = Modifier.padding(horizontal = 16.dp)
                ){
                    if(inputText.isBlank()){
                        Text(text = "Введите текст или номер телефона",
                            color = Color.Gray,
                            style = MaterialTheme.typography.bodyMedium)
                    }
                    innerTextField()
                }

            }
        )
        Spacer(Modifier.height(8.dp))
        Button(
            onClick = {
                val intent = Intent(context, SecondActivity::class.java)
                intent.putExtra("USER_INPUT", inputText)
                context.startActivity(intent)
            },
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth(),
            enabled = inputText.isNotBlank()
        ){
            Text(text = "Открыть вторую Acrtivity")
        }
        Spacer(Modifier.height(8.dp))
        Button(
            onClick = {
                val intent = Intent(Intent.ACTION_DIAL)
                intent.data = Uri.parse("tel:$inputText")
                context.startActivity(intent)
            },
            enabled =
                !(inputText.isBlank() || !Patterns.PHONE.matcher(inputText).matches()),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth()
        ){
            Text(text = "Позвонить другу")
        }
        Spacer(Modifier.height(8.dp))
        Button(
            onClick = {
                val intent = Intent(Intent.ACTION_SEND)
                intent.type = "text/plain"
                intent.putExtra(Intent.EXTRA_TEXT, inputText)
                val chooser = Intent.createChooser(intent, "Поделиться через...")
                context.startActivity(chooser)
            },
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth(),
            enabled = inputText.isNotBlank()
        ){
            Text(text = "Поделиться текстом")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    VkEduProjectTheme {
        MainScreen()
    }
}