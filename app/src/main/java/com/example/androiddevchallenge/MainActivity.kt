/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.data.fishies
import com.example.androiddevchallenge.ui.theme.MyTheme
import dev.chrisbanes.accompanist.glide.GlideImage

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp() {
    Surface(color = MaterialTheme.colors.background) {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "fish") {
            composable("fish") {
                FishyListScreen(navController)
            }
            composable(
                "fish/{fishId}",
                arguments = listOf(
                    navArgument("fishId") { type = NavType.StringType }
                ),
            ) {
                FishDetailsScreen(
                    navController = navController,
                    fishId = it.arguments?.getString("fishId"),
                )
            }
        }
    }
}

@Composable
fun FishDetailsScreen(
    navController: NavHostController,
    fishId: String?,
) {
    if(fishId == null) return
    val fish = fishies.firstOrNull { it.id == fishId } ?: return

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = fish.name,
                        style = MaterialTheme.typography.h6,
                    )
                },
                navigationIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.arrow_back_24px),
                        contentDescription = "Back to List",
                    )
                },
                backgroundColor = MaterialTheme.colors.primary,
            )
        }
    ) {
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {
            Card(
                modifier = Modifier.padding(32.dp),
                shape = MaterialTheme.shapes.medium.copy(all = CornerSize(16.dp))
            ) {
                Column {
                    Box(
                        modifier = Modifier
                            .background(MaterialTheme.colors.surface.copy(alpha = 0.1f))
                            .clip(RoundedCornerShape(16.dp))
                    ) {
                        GlideImage(
                            data = fish.image,
                            contentDescription = fish.name,
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(1f),
                            fadeIn = true,
                            contentScale = ContentScale.Crop,
                        )
                    }

                    Column(Modifier.padding(16.dp)) {
                        Text(
                            text = fish.name,
                            style = MaterialTheme.typography.h4,
                        )
                        Row {
                            Text(
                                text = fish.gender.name,
                                style = MaterialTheme.typography.body1,
                            )
                            Text(
                                modifier = Modifier.padding(horizontal = 4.dp),
                                text = "|",
                                style = MaterialTheme.typography.body1,
                            )
                            Text(
                                text = stringResource(id = R.string.age_string, fish.age),
                                style = MaterialTheme.typography.body1,
                            )
                        }
                        Text(
                            text = fish.location,
                            style = MaterialTheme.typography.caption,
                        )
                    }
                }
            }

            Card(
                modifier = Modifier
                    .padding(horizontal = 32.dp)
                    .fillMaxWidth(),
                shape = MaterialTheme.shapes.medium.copy(CornerSize(16.dp)),
            ) {
                Column(Modifier.padding(16.dp)) {
                    Text(
                        text = "Height: ${fish.height}",
                        style = MaterialTheme.typography.body1,
                    )
                    Text(
                        text = "Weight: ${fish.weight}",
                        style = MaterialTheme.typography.body1,
                    )
                    Text(
                        text = "Price: ${fish.price}",
                        style = MaterialTheme.typography.body1,
                    )
                    Button(
                        modifier = Modifier
                            .padding(top = 16.dp)
                            .fillMaxWidth(),
                        onClick = {},
                    ) {
                        Text(
                            text = "Adopt",
                            style = MaterialTheme.typography.button,
                        )
                    }
                }
            }

            Text(
                modifier = Modifier.padding(horizontal = 40.dp).padding(top = 16.dp).fillMaxWidth(),
                text = "Description",
                style = MaterialTheme.typography.h5,
            )

            Text(
                modifier = Modifier.padding(horizontal = 40.dp).padding(top = 8.dp).fillMaxWidth(),
                text = fish.description,
                style = MaterialTheme.typography.subtitle2,
            )
        }
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        MyApp()
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        MyApp()
    }
}
