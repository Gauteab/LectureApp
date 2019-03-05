package com.example

import io.ktor.application.*
import io.ktor.routing.*
import io.ktor.features.*
import io.ktor.websocket.*
import io.ktor.http.cio.websocket.*

fun main(args: Array<String>) = io.ktor.server.netty.EngineMain.main(args)

fun Application.module() {

    install(CallLogging)
    install(WebSockets)
    routing {
        webSocket("ws") {
            send(Frame.Text("Hi from server"))
            receiveForever {
                val frame = incoming.receive()
                println(frame)
                if (frame is Frame.Text) {
                    println(frame.readText())
                }
            }
        }
    }
}

