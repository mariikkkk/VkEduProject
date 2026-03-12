package com.example.vkeduproject.presentation.appdetails

import com.example.vkeduproject.domain.appdetails.AppDetails
import com.example.vkeduproject.domain.appdetails.Category

val mockAppsList = listOf(
    AppDetails(
        "1",
        "СберБанк Онлайн - с Салютом",
        "sberDevelop",
        Category.FINANCE,
        3,
        150f,
        "",
        emptyList(),
        "Больше чем банк"
    ),
    AppDetails(
        "2",
        "Яндекс.Браузер - с Алисой",
        "yandexDevelop",
        Category.UTILITIES,
        3,
        200f,
        "",
        emptyList(),
        "Быстрый и безопасный браузер"
    ),
    AppDetails(
        "3",
        "Почта Mail.ru",
        "mailDevelop",
        Category.UTILITIES,
        3,
        80f,
        "https://static.rustore.ru/imgproxy/APsbtHxkVa4MZ0DXjnIkSwFQ_KVIcqHK9o3gHY6pvOQ/preset:web_app_icon_62/plain/https://static.rustore.ru/apk/393868735/content/ICON/3f605e3e-f5b3-434c-af4d-77bc5f38820e.png@webp",
        emptyList(),
        "Почтовый клиент для любых ящиков"
    )
)