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

import com.example.androiddevchallenge.R

class FavoriteCollectionsItem(val imageResource: Int, val label: String)

val listOfFavoriteCollections = listOf(
    FavoriteCollectionsItem(R.drawable.short_mantras, "Short mantras"),
    FavoriteCollectionsItem(R.drawable.nature_mediations, "Nature meditations"),
    FavoriteCollectionsItem(R.drawable.stress_and_anxiety, "Stress and anxiety"),
    FavoriteCollectionsItem(R.drawable.self_masage, "Self-massage"),
    FavoriteCollectionsItem(R.drawable.overwhelmed, "Overwhelmed"),
    FavoriteCollectionsItem(R.drawable.nightly_wind, "Nightly wind down")
)

val listOfBody = listOf(
    FavoriteCollectionsItem(R.drawable.inversions, "Inversions"),
    FavoriteCollectionsItem(R.drawable.quick_yoga, "Quick Yoga"),
    FavoriteCollectionsItem(R.drawable.stretching, "Stretching"),
    FavoriteCollectionsItem(R.drawable.tabata, "tabata"),
    FavoriteCollectionsItem(R.drawable.hiit, "HIIT"),
    FavoriteCollectionsItem(R.drawable.pre_natal_yoga, "Pre-natal yoga")
)

val listOfMind = listOf(
    FavoriteCollectionsItem(R.drawable.meditate, "Meditate"),
    FavoriteCollectionsItem(R.drawable.with_kids, "With kids"),
    FavoriteCollectionsItem(R.drawable.aromatherapy, "Aromatherapy"),
    FavoriteCollectionsItem(R.drawable.on_the_go, "On the go"),
    FavoriteCollectionsItem(R.drawable.with_pets, "With pets"),
    FavoriteCollectionsItem(R.drawable.pre_natal_yoga, "High stress")
)
