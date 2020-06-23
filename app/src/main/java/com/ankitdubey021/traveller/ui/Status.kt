package com.ankitdubey021.traveller.ui

import androidx.compose.mutableStateOf
import com.ankitdubey021.traveller.model.Place

sealed class Screen {
    object Home : Screen()
    data class Detail(val place: Place) : Screen()
}

val mutableState = mutableStateOf<Screen>(Screen.Home)

fun navigateTo(destination: Screen) {
    mutableState.value=destination
}
