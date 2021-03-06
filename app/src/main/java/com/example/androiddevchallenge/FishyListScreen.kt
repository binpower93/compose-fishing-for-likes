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

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import com.example.androiddevchallenge.data.fishies
import com.example.androiddevchallenge.ui.FishCard

@Composable
fun FishyListScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Fishing for Likes",
                        style = MaterialTheme.typography.h6,
                    )
                },
                backgroundColor = MaterialTheme.colors.primary,
            )
        }
    ) {
        LazyColumn(contentPadding = PaddingValues(vertical = 8.dp, horizontal = 0.dp)) {
            items(fishies) { fish ->
                FishCard(
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                    fish = fish
                ) { navController.navigate("fish/${fish.id}") }
            }
        }
    }
}
