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

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.compose.Composable
import androidx.compose.getValue
import androidx.compose.setValue
import androidx.compose.state
import androidx.ui.core.ContentScale
import androidx.ui.core.ContextAmbient
import androidx.ui.core.Modifier
import androidx.ui.foundation.*
import androidx.ui.graphics.asImageAsset
import androidx.ui.layout.*
import androidx.ui.material.Card
import androidx.ui.material.EmphasisAmbient
import androidx.ui.material.MaterialTheme
import androidx.ui.material.ProvideEmphasis
import androidx.ui.material.icons.Icons
import androidx.ui.material.icons.filled.Refresh
import androidx.ui.material.ripple.ripple
import androidx.ui.text.style.TextOverflow
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.ankitdubey021.imagedemo.utils.UiState
import com.ankitdubey021.traveller.model.Movie
import com.ankitdubey021.traveller.ui.Screen
import com.ankitdubey021.traveller.ui.navigateTo

@Composable
fun PostCardPopular(post: Movie, modifier: Modifier = Modifier) {

    val loadPictureState = loadPicture("https://source.unsplash.com/${post.imageUrl}/640x426")

    Card(
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        Box(Modifier.ripple().clickable(onClick = {
            navigateTo(Screen.Detail(postId = post.id))
        }
                //onClick = { navigateTo(Screen.Article(post.id)) }
        ), children = {
            Column {
                if (loadPictureState is UiState.Success<Bitmap>)
                    Image(
                        asset = loadPictureState.data.asImageAsset(),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .preferredHeight(200.dp)
                            .fillMaxSize()
                    )
                else
                    Box (
                            paddingTop = 60.dp,
                            paddingBottom = 60.dp,
                            paddingStart = 150.dp,
                            paddingEnd = 150.dp
                    ){
                        Icon(Icons.Default.Refresh)
                    }

                Column(modifier = Modifier.padding(16.dp)) {
                    val emphasisLevels = EmphasisAmbient.current
                    ProvideEmphasis(emphasisLevels.high) {
                        Text(
                            text = post.title,
                            style = MaterialTheme.typography.h6,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                    ProvideEmphasis(emphasisLevels.high) {
                        Text(
                            text = post.place,
                            style = MaterialTheme.typography.body2
                        )
                    }
                }
            }
        })
    }
}

@Preview()
@Composable
fun PreviewPostCardPopular() {
    PostCardPopular(post = Movie(1, "sdf", "dsf", "df",""))
}

@Composable
fun loadPicture(url: String): UiState<Bitmap> {
    var bitmapState:
            UiState<Bitmap> by state<UiState<Bitmap>> { UiState.Loading }
    Glide.with(ContextAmbient.current)
        .asBitmap()
        .load(url)
        .into(object : CustomTarget<Bitmap>() {
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                bitmapState = UiState.Success(resource)
            }

            override fun onLoadCleared(placeholder: Drawable?) { }
        })

    return bitmapState
}

