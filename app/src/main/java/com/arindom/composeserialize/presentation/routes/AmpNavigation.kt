package com.arindom.composeserialize.presentation.routes

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.core.net.toUri
import androidx.navigation.NavController
import androidx.navigation.NavDeepLinkRequest

interface AmpNavigation {
    fun navigateToUri(uri:String)
}

val LocalAmpNavigation =  staticCompositionLocalOf <AmpNavigation>{ error("AmpNavigation not implemented")  }

class AmpNavigationImpl(private val navController: NavController):AmpNavigation{
    override fun navigateToUri(uri: String) {
        val request = NavDeepLinkRequest.Builder
            .fromUri(uri.toUri())
            .build()
        navController.navigate(request)
    }
}