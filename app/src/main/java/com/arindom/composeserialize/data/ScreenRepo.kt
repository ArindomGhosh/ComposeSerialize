package com.arindom.composeserialize.data

import com.arindom.composeserialize.parser.WidgetObj
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class ScreenRepo {
    private val clazzLoader =
        object {}.javaClass.classLoader
    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)
    suspend fun getMainScreen(path: String): Flow<WidgetObj> {
        return flow<WidgetObj> {
            val widgetObj: WidgetObj
            withContext(Dispatchers.IO) {
                val input =
                    clazzLoader?.getResourceAsStream("res/raw/$path")?.bufferedReader()
                        ?.readText()!!
                widgetObj = Json.decodeFromString<WidgetObj>(input)
            }
            emit(widgetObj)
        }
    }
}