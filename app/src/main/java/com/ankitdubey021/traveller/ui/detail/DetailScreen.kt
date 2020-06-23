package com.ankitdubey021.traveller.ui.detail

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.nfc.Tag
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
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.graphics.asImageAsset
import androidx.ui.layout.*
import androidx.ui.layout.RowScope.weight
import androidx.ui.material.*
import androidx.ui.material.icons.Icons
import androidx.ui.material.icons.filled.ArrowBack
import androidx.ui.material.icons.filled.BlurCircular
import androidx.ui.material.icons.filled.FavoriteBorder
import androidx.ui.material.icons.filled.Share
import androidx.ui.material.ripple.ripple
import androidx.ui.res.vectorResource
import androidx.ui.text.style.TextOverflow
import androidx.ui.unit.dp
import com.ankitdubey021.imagedemo.utils.UiState
import com.ankitdubey021.traveller.model.Place
import com.ankitdubey021.traveller.ui.LightBlue
import com.ankitdubey021.traveller.ui.Pink
import com.ankitdubey021.traveller.ui.Screen
import com.ankitdubey021.traveller.ui.home.loadPicture
import com.ankitdubey021.traveller.ui.navigateTo

@Composable
fun DetailScreen(place: Place){
    val loadPictureState = loadPicture("https://source.unsplash.com/${place.imageUrl}/640x426")
    val context = ContextAmbient.current
    Scaffold(
            topAppBar = {
                TopAppBar(
                        title = { Text(text = place.title) },
                        navigationIcon = {
                            IconButton(onClick = { navigateTo(Screen.Home)}) {
                                Icon(Icons.Default.ArrowBack)
                            }
                        },
                        actions = {
                            IconButton(onClick = { sharePost(place, context) }) {
                                Icon(Icons.Filled.Share)
                            }
                        }
                )
            },
            bodyContent = { _ ->
                VerticalScroller(
                        modifier = Modifier.padding(bottom = 50.dp)
                ) {
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
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                                text = place.body,
                                style = MaterialTheme.typography.body1,
                                overflow = TextOverflow.Ellipsis,
                                modifier = Modifier.padding(8.dp)
                        )

                        if(place.tag.isNotEmpty())
                            HomeScreenPopularSection(tags = place.tag)

                    }
                }

            }
    )
}


@Composable
private fun HomeScreenPopularSection(tags: List<String>) {
    Column {
        ProvideEmphasis(EmphasisAmbient.current.high) {
            Text(
                    modifier = Modifier.padding(
                            top = 16.dp,bottom = 8.dp, start = 16.dp
                    ),
                    text = "See also",
                    style = MaterialTheme.typography.subtitle1
            )
        }
        HorizontalScroller {
            Row(modifier = Modifier.padding(end = 16.dp, bottom = 16.dp)) {
                tags.forEach { str ->
                    TagBox(str)
                }
            }
        }
        HomeScreenDivider()
    }
}


@Composable
fun TagBox(title : String) {
    Box(
            modifier = Modifier.padding(8.dp),
            shape = RoundedCornerShape(12.dp, 0.dp, 12.dp, 0.dp),
            backgroundColor = Pink
    ) {
        Text(
                text = title,
                modifier = Modifier.padding(
                        top = 8.dp,bottom = 8.dp,
                        start = 16.dp,end = 16.dp
                ),
                color = Color.White
        )
    }
}

@Composable
private fun HomeScreenDivider() {
    Divider(
            modifier = Modifier.padding(horizontal = 14.dp),
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.08f)
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

