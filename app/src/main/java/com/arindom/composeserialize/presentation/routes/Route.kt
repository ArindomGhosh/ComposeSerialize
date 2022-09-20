package com.arindom.composeserialize.presentation.routes

sealed class Route(val name: String) {
    object ScreenOneRoute : Route("Screen1")
    object ScreeTwoRoute : Route("Screen2")
}
