package com.arindom.composeserialize

import android.app.Application
import com.arindom.composeserialize.models.ImageViewWidgetObj
import com.arindom.composeserialize.parser.*

class ComposeSerialApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        registerWidgetObjManager(
            "Column" to ColumnWidgetObj.serializer(),
            "Row" to RowWidgetObj.serializer(),
            "TextView" to TextWidgetObj.serializer(),
            "ImageView" to ImageViewWidgetObj.serializer(),
            "CustomView" to CustomWidgetObj.serializer()
        )
    }
}