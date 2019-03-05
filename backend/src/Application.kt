package com.example

import io.ktor.application.*
import io.ktor.routing.*
import io.ktor.features.*
import io.ktor.websocket.*
import io.ktor.http.cio.websocket.*
import kotlinx.coroutines.channels.ClosedReceiveChannelException

fun main(args: Array<String>) = io.ktor.server.netty.EngineMain.main(args)

fun Application.module() {

    install(CallLogging)
    install(WebSockets)
    routing {
        webSocket("ws") {
            send(Frame.Text("Hi from server"))
            try {
                while (true) {
                    val frame = incoming.receive()
                    require(frame is Frame.Text) { "Non Text Frame Received" }

                    println(frame.readText())

                }
            } catch (e: ClosedReceiveChannelException) {
                println("Closed Receive Channel")
            }
        }
    }
}

