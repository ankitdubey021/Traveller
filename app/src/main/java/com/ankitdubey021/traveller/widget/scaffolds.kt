package com.ankitdubey021.mycompose01.ui.widget

import androidx.compose.Composable
import androidx.compose.remember
import androidx.ui.core.Alignment
import androidx.ui.core.ContentScale
import androidx.ui.core.Modifier
import androidx.ui.core.clip
import androidx.ui.foundation.Icon
import androidx.ui.foundation.Image
import androidx.ui.foundation.Text
import androidx.ui.foundation.shape.corner.CutCornerShape
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.layout.*
import androidx.ui.material.*
import androidx.ui.material.icons.Icons
import androidx.ui.material.icons.filled.Close
import androidx.ui.material.icons.filled.Menu
import androidx.ui.res.imageResource
import androidx.ui.unit.dp
import com.ankitdubey021.traveller.R
import com.ankitdubey021.traveller.ui.colGray
import androidx.compose.state

@Composable
fun ScaffoldWithDrawer(bodyContent: @Composable() () -> Unit) {
    val scaffoldState = remember { ScaffoldState() }
    val isDark = state { false }

    Scaffold(
            scaffoldState = scaffoldState,
            topAppBar = {
                TopAppBar(
                        title = {
                            Text(text = "Jetpack Compose")
                        },
                        navigationIcon = {
                            IconButton(onClick = {
                                scaffoldState.drawerState = DrawerState.Opened
                            }) {
                                Icon(Icons.Filled.Menu)
                            }
                        }
                )
            }, drawerContent = {
        Column(modifier = Modifier.fillMaxSize(),
                horizontalGravity = Alignment.CenterHorizontally) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ){
                IconButton(onClick = {
                    scaffoldState.drawerState = DrawerState.Closed
                }, icon = {
                    Icon(asset = Icons.Default.Close)
                })
            }


            val imageModifier = Modifier
                    .preferredHeight(100.dp)
                    .preferredWidth(100.dp)
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(topRight = 20.dp,bottomLeft = 20.dp))
                    .clip(shape = CutCornerShape(bottomRight = 20.dp,topLeft = 20.dp))

            val avatarImage =  imageResource(R.drawable.avatar)

            Image(avatarImage,  modifier = imageModifier,
                    contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.preferredHeight(30.dp))

            Divider(
                    thickness = 1.dp,
                    color = colGray
            )

            Spacer(modifier = Modifier.preferredHeight(40.dp))

            Button(onClick = {
                scaffoldState.drawerState = DrawerState.Closed
            }) {
                Text(text = "Close Drawer")
            }
        }
    }, bodyContent = {
        bodyContent()
    })
}