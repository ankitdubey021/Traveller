/*
 * Copyright 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ankitdubey021.traveller.ui.home

import androidx.compose.Composable
import androidx.compose.state
import androidx.ui.core.Modifier
import androidx.ui.foundation.Text
import androidx.ui.foundation.VerticalScroller
import androidx.ui.layout.Column
import androidx.ui.layout.padding
import androidx.ui.material.Scaffold
import androidx.ui.material.TopAppBar
import androidx.ui.unit.dp
import com.ankitdubey021.traveller.model.Place
import com.ankitdubey021.traveller.widget.LoadingScreen
import com.creativeitem.academy.latest.network.MovieNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Composable
fun HomeScreen() {
    Scaffold(
            topAppBar = {
                TopAppBar(
                        title = { Text(text = "Traveller") }
                )
            },
            bodyContent = { _ ->
                fetchMovies()
            }
    )

}

@Composable
fun fetchMovies() {

    val movies = state { listOf<Place>() }

    if (movies.value.isEmpty()) {
        GlobalScope.launch {
            val list = MovieNetwork.api.getPosts()
            if (list.isSuccessful && !list.body().isNullOrEmpty()) {
                GlobalScope.launch(Dispatchers.Main) {
                    movies.value = list.body()!!
                    println("::::::::::;${list.body()}")
                }
            }
        }
        LoadingScreen()
    } else {
        VerticalScroller {
            Column {
                movies.value.forEach {
                    PostCardPopular(it, Modifier.padding(16.dp))
                }
            }
        }
    }
}




//