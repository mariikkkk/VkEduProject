package com.example.vkeduproject.presentation.appdetails

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.Image
import coil3.compose.AsyncImage
import com.example.vkeduproject.domain.appdetails.AppDetails
import com.example.vkeduproject.domain.appdetails.Category
import com.example.vkeduproject.ui.theme.VkEduProjectTheme
import com.example.vkeduproject.R

@Composable
fun AppListItem(
    app: AppDetails,
    onClick: (String) -> Unit
){
    Row(
        Modifier
            .fillMaxWidth()
            .clickable { onClick(app.id) }
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ){
        AsyncImage(
            model = app.iconUrl,
            contentDescription = "Иконка приложения",
            contentScale = ContentScale.Crop,
            placeholder = painterResource(id = R.drawable.logo_sber),
            modifier = Modifier
                .size(56.dp)
                .clip(RoundedCornerShape(12.dp))
        )
        Spacer(Modifier.width(16.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = app.name,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = app.description,
                fontSize = 12.sp,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = app.category.name,
                fontSize = 12.sp,
                color = Color.Gray
            )
        }
    }

}

@Preview
@Composable
fun previewAppListItem(){
    VkEduProjectTheme() {
        AppListItem(
            AppDetails(
                "1",
                "СберБанк Онлайн - с Салютом",
                "sberDevelop",
                Category.FINANCE,
                3,
                150f,
                "https://www.big-lab.com/images/logo-sber.jpg",
                emptyList(),
                "Больше чем банк"
            ),
            onClick = {}
        )
    }
}