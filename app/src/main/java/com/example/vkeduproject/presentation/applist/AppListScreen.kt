package com.example.vkeduproject.presentation.applist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.vkeduproject.ui.theme.VkEduProjectTheme
import com.example.vkeduproject.R

@Composable
fun AppListScreen(
    viewModel: AppListViewModel,      // Мне все же привычней создавать VM здесь
    onAppClick: (String) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(viewModel.events){
        viewModel.events.collect{ event ->
            when (event){
                is ScreenEvent.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(event.message)
                }
            }
        }
    }
    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
    ){ paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFF005BFF))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = { viewModel.onLogoClick() }
                ){
                    Icon(
                        painter = painterResource(R.drawable.rustore_logo),
                        contentDescription = "Иконка RuStore",
                        tint = Color.Unspecified,
                        modifier = Modifier
                            .size(32.dp)
                    )
                }
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "RuStore",
                    color = Color.White,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    painter = painterResource(R.drawable.headergridicon),
                    contentDescription = "Если честно я не знаю, что она делает",
                    tint = Color.Unspecified,
                    modifier = Modifier
                        .size(32.dp)
                )
            }

            // Список
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                    .background(Color.White)
                    .padding(top = 8.dp)
            ) {
                items(state.items) { app ->
                    AppListItem(
                        app = app,
                        onClick = onAppClick
                    )
                }
            }
        }
    }

}

@Preview
@Composable
fun previewAppListScreen(){
    VkEduProjectTheme() {
        AppListScreen(
            viewModel = viewModel(),
            onAppClick = {}
        )

    }
}