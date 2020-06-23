package com.ankitdubey021.traveller

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.ui.animation.Crossfade
import androidx.ui.core.setContent
import androidx.ui.material.MaterialTheme
import androidx.ui.material.Surface
import com.ankitdubey021.traveller.ui.MovieComposerTheme
import com.ankitdubey021.traveller.ui.Screen
import com.ankitdubey021.traveller.ui.detail.DetailScreen
import com.ankitdubey021.traveller.ui.home.HomeScreen
import com.ankitdubey021.traveller.ui.mutableState

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieComposerTheme {
                Crossfade(mutableState) { screen ->
                        Surface(color = MaterialTheme.colors.background) {
                            when (screen.value) {
                                is Screen.Home -> HomeScreen()
                                is Screen.Detail -> DetailScreen((screen.value as Screen.Detail).postId)
                            }
                        }
                }
            }
        }
    }
}

