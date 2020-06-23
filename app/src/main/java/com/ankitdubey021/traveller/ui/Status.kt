package com.ankitdubey021.traveller.ui

import androidx.compose.mutableStateOf

sealed class Screen {
    object Home : Screen()
    data class Detail(val postId: Int) : Screen()
}

val mutableState = mutableStateOf<Screen>(Screen.Home)

fun navigateTo(destination: Screen) {
    mutableState.value=destination
}
