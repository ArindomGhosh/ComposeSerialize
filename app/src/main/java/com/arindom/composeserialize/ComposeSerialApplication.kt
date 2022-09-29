package com.arindom.composeserialize

import android.app.Application
import com.arindom.composeserialize.parsers.ImageViewWidgetObj
import com.arindom.composeserialize.parsers.*

class ComposeSerialApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        registerWidgetObjManager(
            "Column" to ColumnWidgetObj.serializer(),
            "Row" to RowWidgetObj.serializer(),
            "TextView" to TextWidgetObj.serializer(),
            "ImageView" to ImageViewWidgetObj.serializer(),
            "CustomView" to CustomWidgetObj.serializer(),
            "Card" to CardWidgetObj.serializer(),
            "CircleShape" to CircleShapeWidgetObj.serializer(),
        )
    }
}