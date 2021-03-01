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
package com.example.androiddevchallenge.data

import java.util.UUID

data class Fish(
    val id: String,
    val name: String,
    val species: String,
    val age: String,
    val gender: Gender,
    val description: String,
    val image: String,
    val price: String,
    val height: Int,
    val weight: Int,
    val location: String,
)

val fishies by lazy {
    listOf(
        Fish(
            id = UUID.randomUUID().toString(),
            name = "P Sherman",
            species = "Clown Fish",
            age = "1",
            price = "$5",
            height = 10,
            weight = 10,
            gender = Gender.Male,
            description = "When life gets you down do you wanna know what you've gotta do? Just keep swimming. Just keep swimming. Just keep swimming, swimming..",
            image = "https://static.wikia.nocookie.net/pixar/images/a/aa/Nemo-FN.png/revision/latest/scale-to-width-down/1000?cb=20160710221104",
            location = "42 Wallaby Way, Sydney",
        ),
        Fish(
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
        Fish(
            id = UUID.randomUUID().toString(),
            name = "Loan Shark",
            species = "Shark",
            age = "1",
            price = "$45",
            height = 10,
            weight = 10,
            gender = Gender.Male,
            description = "They are happy to give you money, in return for your knee caps",
            image = "https://cpcu.co.uk/wp-content/webp-express/webp-images/doc-root/wp-content/uploads/2018/06/loan-shark.jpg.webp",
            location = "Coming for you",
        ),
        Fish(
            id = UUID.randomUUID().toString(),
            name = "Rip Doff",
            species = "Sardine",
            age = "1",
            price = "$1450",
            height = 10,
            weight = 10,
            gender = Gender.Female,
            description = "Most likely to be found during the Peak hours on the London Underground, paying thousands for the privilege",
            image = "https://upload.wikimedia.org/wikipedia/commons/6/6a/Sardina_pilchardus_2011.jpg",
            location = "London Underground",
        ),
    )
}
