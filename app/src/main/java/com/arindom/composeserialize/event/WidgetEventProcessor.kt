package com.arindom.composeserialize.event

enum class EventType(){
    MIME,// "image/*, video/*"
    URL,
    NAV,
}

data class AmpEvents(
    val eventType: EventType,
    val message:String
)


