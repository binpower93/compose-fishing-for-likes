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

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.androiddevchallenge.data.Fish
import com.example.androiddevchallenge.data.fishies
import com.example.androiddevchallenge.ui.secondaryButtonColors
import dev.chrisbanes.accompanist.glide.GlideImage

@Composable
fun FishDetailsScreen(
    navController: NavHostController,
    fishId: String?,
) {
    if (fishId == null) return
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
                        modifier = Modifier
                            .size(40.dp)
                            .padding(8.dp)
                            .clickable { navController.popBackStack() },
                        painter = painterResource(id = R.drawable.arrow_back_24px),
                        contentDescription = "Back to List",
                        colorFilter = ColorFilter.tint(MaterialTheme.colors.onPrimary),
                    )
                },
                backgroundColor = MaterialTheme.colors.primary,
            )
        }
    ) {
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {
            FishHeaderCard(fish)

            FishDimensionsCard(fish)

            Text(
                modifier = Modifier
                    .padding(horizontal = 40.dp)
                    .padding(top = 16.dp)
                    .fillMaxWidth(),
                text = "Description",
                style = MaterialTheme.typography.h5,
            )

            Text(
                modifier = Modifier
                    .padding(horizontal = 40.dp)
                    .padding(
                        top = 8.dp,
                        bottom = 32.dp,
                    )
                    .fillMaxWidth(),
                text = fish.description,
                style = MaterialTheme.typography.subtitle2,
            )
        }
    }
}

@Composable
private fun FishHeaderCard(fish: Fish) {
    Card(
        modifier = Modifier.padding(32.dp),
        shape = MaterialTheme.shapes.medium.copy(all = CornerSize(16.dp)),
    ) {
        Column {
            Box(
                modifier = Modifier
                    .background(MaterialTheme.colors.surface.copy(alpha = 0.1f))
//                    .clip(RoundedCornerShape(16.dp))
                    .shadow(
                        elevation = 2.dp,
                        shape = RoundedCornerShape(size = 16.dp),
                        clip = true,
                    ),
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
}

@Composable
private fun FishDimensionsCard(fish: Fish) {
    Card(
        modifier = Modifier
            .padding(horizontal = 32.dp)
            .fillMaxWidth(),
        shape = MaterialTheme.shapes.medium.copy(CornerSize(16.dp)),
    ) {
        Column(Modifier.padding(16.dp)) {
            Row {
                Text(
                    text = "Species:",
                    style = MaterialTheme.typography.body1,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    modifier = Modifier.padding(start = 8.dp),
                    text = fish.species,
                    style = MaterialTheme.typography.body1,
                )
            }
            Row(modifier = Modifier.padding(top = 8.dp)) {
                Text(
                    text = "Height:",
                    style = MaterialTheme.typography.body1,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    modifier = Modifier.padding(start = 8.dp),
                    text = "${fish.height} cm",
                    style = MaterialTheme.typography.body1,
                )
            }
            Row(modifier = Modifier.padding(vertical = 8.dp)) {
                Text(
                    text = "Weight:",
                    style = MaterialTheme.typography.body1,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    modifier = Modifier.padding(start = 8.dp),
                    text = "${fish.weight}g",
                    style = MaterialTheme.typography.body1,
                )
            }

            Row(modifier = Modifier.padding(top = 8.dp)) {
                Text(
                    text = "Price:",
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.secondaryVariant,
                )
                Text(
                    modifier = Modifier.padding(start = 8.dp),
                    text = fish.price,
                    style = MaterialTheme.typography.h6,
                    color = MaterialTheme.colors.secondaryVariant,
                )
            }
            Button(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth(),
                onClick = {},
                colors = secondaryButtonColors(),
                shape = RoundedCornerShape(percent = 50)
            ) {
                Text(
                    text = "Adopt",
                    style = MaterialTheme.typography.h6
                    ,
                )
            }
        }
    }
}
