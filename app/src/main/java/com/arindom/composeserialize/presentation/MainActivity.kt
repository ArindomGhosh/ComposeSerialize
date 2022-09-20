package com.arindom.composeserialize.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.arindom.composeserialize.presentation.routes.AmpNavigationImpl
import com.arindom.composeserialize.presentation.routes.LocalAmpNavigation
import com.arindom.composeserialize.presentation.routes.Route
import com.arindom.composeserialize.presentation.screens.screenone.ScreenOne
import com.arindom.composeserialize.ui.theme.ComposeSerializeTheme

const val uriScreen = "android-app://screen.presentation.composeserialize.arindom.com"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colors.background
            ) {
                // A surface container using the 'background' color from the theme
                val navController = rememberNavController()
                ComposeSerializeTheme {
                    CompositionLocalProvider(
                        LocalAmpNavigation provides AmpNavigationImpl(navController)
                    ) {
                        NavGraph(
                            navController = navController,
                            startDestination = "${Route.ScreenOneRoute.name}?screen={screenName}"
                        )
                    }

                }
            }
        }
    }
}

@Composable
fun NavGraph(
    navController: NavHostController,
    startDestination: String
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(
            route = "${Route.ScreenOneRoute.name}?screen={screenName}",
            arguments = listOf(navArgument("screenName") {
                defaultValue = "screen_one.json"
            }),
            deepLinks = listOf(navDeepLink {
                uriPattern = "$uriScreen/{screenName}"
            })
        ) {
            println(it.arguments?.getString("screenName"))
            ScreenOne(
                it.arguments?.getString("screenName") ?: "screen_one.json"
            )
        }
    }
}