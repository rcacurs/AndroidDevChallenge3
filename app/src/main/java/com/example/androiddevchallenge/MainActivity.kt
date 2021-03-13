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

import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import android.os.Bundle
import android.view.View
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.popUpTo
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.data.FavoriteCollectionsItem
import com.example.androiddevchallenge.data.listOfBody
import com.example.androiddevchallenge.data.listOfFavoriteCollections
import com.example.androiddevchallenge.data.listOfMind
import com.example.androiddevchallenge.ui.theme.MyTheme

class MainActivity : AppCompatActivity() {

    val favoriteCollectionsImageId = mutableListOf(
        R.drawable.short_mantras,
        R.drawable.nature_mediations,
        R.drawable.stress_and_anxiety,
        R.drawable.self_masage,
        R.drawable.overwhelmed,
        R.drawable.nightly_wind
    )
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (VERSION.SDK_INT >= VERSION_CODES.KITKAT) {
//            window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN, WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN)
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
            window.statusBarColor = Color(0x000000).toArgb()
        }
        setContent {
            MyTheme {
                MyApp(favoriteCollectionsImageId)
            }
        }
    }
}

// Start building your app here!
@ExperimentalFoundationApi
@Composable
fun MyApp(list: List<Int>) {
    val navController = rememberNavController()
    val isDark = isSystemInDarkTheme()
    NavHost(navController = navController, startDestination = "Welcome") {
        composable("Welcome") { Welcome(navController, isDark) }
        composable("Log in") { LogIn(navController, isDark) }
        composable("Home") {
            Home(
                navController,
                isDark,
                listOfFavoriteCollections,
                listOfBody,
                listOfMind
            )
        }
    }
}

@Composable
fun Welcome(navController: NavController?, isDark: Boolean) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(
                if (isDark) R.drawable.dark_welcome else R.drawable.light_welcome
            ),
            contentDescription = "",
//            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(
                    if (isDark) R.drawable.dark_logo else R.drawable.light_logo
                ),
                contentDescription = "My Soothe Logo"
            )
            Divider(
                modifier = Modifier
                    .alpha(0f)
                    .height(32.dp)
            )
            Button(
                onClick = {},
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier
                    .padding(top = 32.dp, bottom = 8.dp)
                    .fillMaxWidth()
                    .height(72.dp)
            ) {
                Text(
                    text = "SIGN UP",
                    style = MaterialTheme.typography.button,
                )
            }
            Button(
                onClick = { navController?.navigate("Log in") },
                shape = MaterialTheme.shapes.medium,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colors.secondary
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(72.dp),
            ) {
                Text(text = "LOG IN")
            }
        }
    }
}

@Composable fun LogIn(navController: NavController?, isDark: Boolean) {
    var emailAddress by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(
                if (isDark) R.drawable.dark_login else R.drawable.light_login
            ),
            contentDescription = "",
//            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 16.dp, start = 16.dp, end = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "LOG IN",
                style = MaterialTheme.typography.h1,
                modifier = Modifier.paddingFromBaseline(top = 184.dp, bottom = 32.dp)
            )
            TextField(
                value = emailAddress,
                textStyle = MaterialTheme.typography.body1,
                onValueChange = { emailAddress = it },
                label = {
                    Text(
                        text = "Email address",
                    )
                },
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .fillMaxWidth()
            )

            TextField(
                value = password,
                textStyle = MaterialTheme.typography.body1,
                onValueChange = { password = it },
                label = {
                    Text(
                        text = "Password",
                    )
                },
//                style = MaterialTheme.typography.body1,
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .fillMaxWidth()
            )

            Button(
                onClick = {
                    navController?.navigate("Home") {
                        popUpTo("Home") { inclusive = true }
                    }
                },
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(72.dp),
            ) {
                Text(text = "LOG IN")
            }
            Row {
                Text(
                    text = "Don't have an account? ",
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.paddingFromBaseline(top = 32.dp)
                )
                Text(
                    text = "Sign up",
                    style = MaterialTheme.typography.body1.copy(textDecoration = TextDecoration.Underline),
                    modifier = Modifier
                        .paddingFromBaseline(top = 32.dp)
                        .clickable(onClick = {})
                )
            }
        }
    }
}

@ExperimentalFoundationApi
@Composable fun Home(
    navController: NavController?,
    isDark: Boolean,
    listOfFavoriteCollections: List<FavoriteCollectionsItem>,
    listOfBody: List<FavoriteCollectionsItem>,
    listOfMind: List<FavoriteCollectionsItem>
) {
    var searchString by remember { mutableStateOf("") }

    Surface {
        Scaffold(
            bottomBar = {
                BottomNavigation() {
                    BottomNavigationItem(
                        icon = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_baseline_spa_24),
                                contentDescription = "",
                                tint = MaterialTheme.colors.onBackground,
                                modifier = Modifier.size(18.dp)

                            )
                        },
                        label = {
                            Text(
                                text = "HOME",
                                style = MaterialTheme.typography.caption,
                                color = MaterialTheme.colors.onBackground
                            )
                        },
                        onClick = {},
                        selected = true,
                        modifier = Modifier.background(MaterialTheme.colors.background)
                    )

                    BottomNavigationItem(
                        icon = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_baseline_account_circle_24),
                                contentDescription = "",
                                tint = MaterialTheme.colors.onBackground,
                                modifier = Modifier.size(18.dp)

                            )
                        },
                        label = {
                            Text(
                                text = "PROFILE",
                                style = MaterialTheme.typography.caption,
                                color = MaterialTheme.colors.onBackground
                            )
                        },
                        onClick = {},
                        selected = false,
                        modifier = Modifier.background(MaterialTheme.colors.background)
                    )
                }
            },
        ) {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .fillMaxHeight()
                    .padding(top = 16.dp)
            ) {
                TextField(
                    value = searchString,
                    textStyle = MaterialTheme.typography.body1,
                    onValueChange = { searchString = it },
                    label = {
                        Text(
                            text = "Search",
                            style = MaterialTheme.typography.body1
                        )
                    },
                    leadingIcon = {
                        Icon(
                            painterResource(
                                id = R.drawable.ic_baseline_search_24
                            ),
                            contentDescription = "",
                            modifier = Modifier.size(18.dp)
                        )
                    },
                    modifier = Modifier
                        .padding(top = 40.dp, start = 16.dp, end = 16.dp)
                        .fillMaxWidth()
                )
                Text(
                    text = "FAVORITE COLLECTIONS",
                    style = MaterialTheme.typography.h2,
                    modifier = Modifier.paddingFromBaseline(top = 40.dp).padding(start = 16.dp)
                )

                LazyRow(
                    contentPadding = PaddingValues(start = 16.dp, end = 16.dp)

                ) {

                    items(listOfFavoriteCollections.size / 2) { index ->
                        Column() {
                            CardFavoriteCollecion(listOfFavoriteCollections[index * 2])
                            CardFavoriteCollecion(listOfFavoriteCollections[index * 2 + 1])
                        }
                    }
                }

                Text(
                    text = "ALIGN YOUR BODY",
                    style = MaterialTheme.typography.h2,
                    modifier = Modifier.paddingFromBaseline(top = 48.dp).padding(start = 16.dp)
                )

                LazyRow(
                    contentPadding = PaddingValues(start = 16.dp, end = 16.dp)
                ) {
                    items(listOfBody) { item ->
                        ImageCard(item)
                    }
                }

                Text(
                    text = "ALIGN YOUR MIND",
                    style = MaterialTheme.typography.h2,
                    modifier = Modifier.paddingFromBaseline(top = 48.dp).padding(start = 16.dp)
                )

                LazyRow(
                    contentPadding = PaddingValues(start = 16.dp, end = 16.dp)
                ) {
                    items(listOfMind) { item ->
                        ImageCard(item)
                    }
                }
                Divider(modifier = Modifier.height(100.dp).alpha(0.0f))
            }
        }

        MyFab()
    }
}
@Composable
fun CardFavoriteCollecion(collectionItem: FavoriteCollectionsItem) {
    Card(
        shape = MaterialTheme.shapes.small,
        elevation = 0.dp,
        modifier = Modifier
            .height(56.dp)
            .width(192.dp)
            .padding(end = 8.dp, top = 8.dp)
            .clickable(onClick = {})
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(collectionItem.imageResource),
                contentDescription = "",
                modifier = Modifier.width(56.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = collectionItem.label,
                style = MaterialTheme.typography.h3,
                modifier = Modifier.padding(start = 16.dp, end = 8.dp)
            )
        }
    }
}

@Composable
fun BottomButton(iconResource: Int, label: String, selected: Boolean) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        val color = if (selected) MaterialTheme.colors.onBackground else MaterialTheme.colors.onBackground.copy(alpha = 0.5f)
        Icon(
            painterResource(
                id = iconResource
            ),
            contentDescription = "",
            modifier = Modifier.size(18.dp),
            tint = color

        )
        Text(
            text = label,
            style = MaterialTheme.typography.caption,
            color = color
        )
    }
}
@Composable
fun MyFab() {
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {

        val (button) = createRefs()
        FloatingActionButton(
            onClick = {},
            backgroundColor = MaterialTheme.colors.primary,
            modifier = Modifier.constrainAs(button) {
                bottom.linkTo(parent.bottom, margin = 32.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        ) {
            Icon(painter = painterResource(id = R.drawable.ic_baseline_play_arrow_24), "")
        }
    }
}
@Composable
fun ImageCard(collectionItem: FavoriteCollectionsItem) {
    Column(
        modifier = Modifier
            .padding(top = 8.dp, end = 8.dp)
            .clickable(onClick = {}),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(collectionItem.imageResource),
            contentDescription = "",
            modifier = Modifier
                .height(88.dp)
                .width(88.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Text(
            text = collectionItem.label,
            style = MaterialTheme.typography.h3,
            modifier = Modifier.paddingFromBaseline(top = 24.dp)
        )
    }
}

// @Preview("Light Welcome", widthDp = 360, heightDp = 640)
// @Composable
// fun LightWelcomePreview() {
//    MyTheme(darkTheme = false) {
//        Welcome(null, false)
//    }
// }

// @Preview("Dark Welcome", widthDp = 360, heightDp = 640)
// @Composable
// fun DarkWelcomePreview() {
//    MyTheme(darkTheme = true) {
//        Welcome(null, true)
//    }
// }

// @Preview("Light Login", widthDp = 360, heightDp = 640)
// @Composable
// fun LightLoginPreview() {
//    MyTheme(darkTheme = false) {
//        LogIn(null, false)
//    }
// }
//
// @Preview("Dark Login", widthDp = 360, heightDp = 640)
// @Composable
// fun DarkLoginPreview() {
//    MyTheme(darkTheme = true) {
//        LogIn(null, true)
//    }
// }

@ExperimentalFoundationApi
@Preview("Light Home", widthDp = 360, heightDp = 640)
@Composable
fun LightHomePreview() {
    MyTheme(darkTheme = false) {
        Home(null, false, listOfFavoriteCollections, listOfBody, listOfMind)
    }
}

@ExperimentalFoundationApi
@Preview("Dark Home", widthDp = 360, heightDp = 640)
@Composable
fun DarkHomePreview() {
    MyTheme(darkTheme = true) {
        Home(null, true, listOfFavoriteCollections, listOfBody, listOfMind)
    }
}
