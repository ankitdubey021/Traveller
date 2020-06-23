package com.ankitdubey021.traveller.ui.detail

import androidx.compose.Composable
import androidx.ui.foundation.Icon
import androidx.ui.foundation.Text
import androidx.ui.material.IconButton
import androidx.ui.material.Scaffold
import androidx.ui.material.TopAppBar
import androidx.ui.material.icons.Icons
import androidx.ui.material.icons.filled.ArrowBack
import com.ankitdubey021.traveller.ui.Screen
import com.ankitdubey021.traveller.ui.navigateTo

@Composable
fun DetailScreen(id:Int){
    Scaffold(
            topAppBar = {
                TopAppBar(
                        title = { Text(text = "NewYork") },
                        navigationIcon = {
                            IconButton(onClick = { navigateTo(Screen.Home)}) {
                                Icon(Icons.Default.ArrowBack)
                            }
                        }
                )
            },
            bodyContent = { _ ->

            }
    )
}