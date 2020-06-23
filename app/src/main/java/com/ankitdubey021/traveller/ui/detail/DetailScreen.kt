package com.ankitdubey021.traveller.ui.detail

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import androidx.compose.Composable
import androidx.compose.getValue
import androidx.compose.setValue
import androidx.compose.state
import androidx.ui.core.Alignment
import androidx.ui.core.ContentScale
import androidx.ui.core.ContextAmbient
import androidx.ui.core.Modifier
import androidx.ui.foundation.*
import androidx.ui.foundation.R
import androidx.ui.graphics.asImageAsset
import androidx.ui.layout.*
import androidx.ui.layout.RowScope.weight
import androidx.ui.material.*
import androidx.ui.material.icons.Icons
import androidx.ui.material.icons.filled.ArrowBack
import androidx.ui.material.icons.filled.BlurCircular
import androidx.ui.material.icons.filled.FavoriteBorder
import androidx.ui.material.icons.filled.Share
import androidx.ui.res.vectorResource
import androidx.ui.unit.dp
import com.ankitdubey021.imagedemo.utils.UiState
import com.ankitdubey021.traveller.model.Place
import com.ankitdubey021.traveller.ui.Screen
import com.ankitdubey021.traveller.ui.home.loadPicture
import com.ankitdubey021.traveller.ui.navigateTo

@Composable
fun DetailScreen(place: Place){
    val loadPictureState = loadPicture("https://source.unsplash.com/${place.imageUrl}/640x426")
    var showDialog by state { false }
    if (showDialog) {
        FunctionalityNotAvailablePopup { showDialog = false }
    }
    Scaffold(
            topAppBar = {
                TopAppBar(
                        title = { Text(text = place.title) },
                        navigationIcon = {
                            IconButton(onClick = { navigateTo(Screen.Home)}) {
                                Icon(Icons.Default.ArrowBack)
                            }
                        }
                )
            },
            bodyContent = { _ ->
                VerticalScroller() {
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
                                Icon(Icons.Default.BlurCircular)
                            }

                    }
                }

            },
        bottomAppBar = {
            BottomBar(place) { showDialog = true }
        }
    )
}
@Composable
private fun BottomBar(post: Place, onUnimplementedAction: () -> Unit) {
    Surface(elevation = 2.dp) {
        Row(
            verticalGravity = Alignment.CenterVertically,
            modifier = Modifier
                .preferredHeight(56.dp)
                .fillMaxWidth()
        ) {
            IconButton(onClick = onUnimplementedAction) {
                Icon(Icons.Filled.FavoriteBorder)
            }

            val context = ContextAmbient.current
            IconButton(onClick = { sharePost(post, context) }) {
                Icon(Icons.Filled.Share)
            }
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Composable
private fun FunctionalityNotAvailablePopup(onDismiss: () -> Unit) {
    AlertDialog(
        onCloseRequest = onDismiss,
        text = {
            Text(
                text = "Functionality not available \uD83D\uDE48",
                style = MaterialTheme.typography.body2
            )
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text(text = "CLOSE")
            }
        }
    )
}

private fun sharePost(post: Place, context: Context) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TITLE, post.title)
        putExtra(Intent.EXTRA_TEXT, post.body)
    }
    context.startActivity(Intent.createChooser(intent, "Share post"))
}

