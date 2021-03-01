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
package com.example.androiddevchallenge.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.data.Fish
import com.example.androiddevchallenge.data.Gender
import dev.chrisbanes.accompanist.glide.GlideImage
import java.util.UUID

@Composable
fun FishCard(
    modifier: Modifier = Modifier,
    fish: Fish,
    isPreview: Boolean = false,
    onClick: () -> Unit,
) {
    Box(modifier = modifier) {
        Card(
            elevation = 2.dp,
            modifier = Modifier
                .padding(
                    start = 16.dp,
                    top = 16.dp,
                    bottom = 16.dp,
                )
                .height(96.dp)
                .fillMaxWidth()
                .clickable {
                    onClick()
                }
        ) {
            Column(
                modifier = Modifier.padding(
                    start = 128.dp,
                    top = 16.dp,
                    bottom = 16.dp,
                    end = 16.dp,
                )
            ) {
                Text(
                    text = fish.name,
                    style = MaterialTheme.typography.h6,
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
        Card(
            elevation = 5.dp,
            shape = MaterialTheme.shapes.medium.copy(all = CornerSize(size = 16.dp)),
            modifier = Modifier.clickable {
                onClick()
            },
        ) {
            if (isPreview) {
                Image(
                    modifier = Modifier.size(128.dp),
                    painter = painterResource(id = R.drawable.sample_image),
                    contentDescription = "Sample Image",
                    contentScale = ContentScale.Crop,
                )
            } else {
                GlideImage(
                    data = fish.image,
                    contentDescription = fish.name,
                    modifier = Modifier.size(128.dp),
                    fadeIn = true,
                    contentScale = ContentScale.Crop,
                )
            }
        }
    }
}

@Preview
@Composable
fun FishCardPreview() {
    FishCard(
        fish = Fish(
            id = UUID.randomUUID().toString(),
            name = "Nicola Sturgeon",
            species = "Shovelnose Sturgeon",
            age = "1",
            price = "$45",
            height = 10,
            weight = 10,
            gender = Gender.Female,
            description = "A strong female fish, who won't stop until she gets independence",
            image = "https://upload.wikimedia.org/wikipedia/commons/1/12/Sturgeon.jpg",
            location = "Scotland",
        ),
        isPreview = true,
    ) {  }
}
