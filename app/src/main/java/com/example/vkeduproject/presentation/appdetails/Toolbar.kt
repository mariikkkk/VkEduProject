package com.example.vkeduproject.presentation.appdetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.vkeduproject.ui.theme.VkEduProjectTheme

@Composable
internal fun Toolbar(
    onBackClick: () -> Unit,
    onShareClick: () -> Unit,
    isInWishList: Boolean,
    onWishListClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
    ) {
        IconButton(onClick = onBackClick) {
            Icon(
                imageVector = Icons.AutoMirrored.Default.ArrowBack,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
            )
        }
        Spacer(Modifier.weight(1f))
        IconButton(
            onClick = onWishListClick
        ) {
            Icon(
                if(isInWishList)Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                contentDescription = "Добавление в вишлист",
                tint = MaterialTheme.colorScheme.primary
            )
        }
        IconButton(onClick = onShareClick) {
            Icon(
                imageVector = Icons.Default.Share,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    VkEduProjectTheme {
        Toolbar(
            onBackClick = {},
            onShareClick = {},
            false,
            {}
        )
    }
}