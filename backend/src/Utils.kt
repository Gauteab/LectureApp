package com.example

import io.ktor.websocket.DefaultWebSocketServerSession
import kotlinx.coroutines.channels.ClosedReceiveChannelException

suspend fun DefaultWebSocketServerSession.receiveForever(f: suspend DefaultWebSocketServerSession.() -> Unit) {
    try { while (true) f() }
    catch (e: ClosedReceiveChannelException) {
        println("Closed Receive Channel")
    }
}

