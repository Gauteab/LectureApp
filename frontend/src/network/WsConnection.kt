package network

import org.w3c.dom.WebSocket

const val URL = "ws://localhost:8080/ws"
const val READY: Short = 1

class WsConnection {

    private val socket = WebSocket(URL).apply {
        onopen  = { println("Connection Established: $readyState") }
        onerror = { println("An error Occurred!") }
    }

    fun send(data: String) {
        while (socket.readyState != READY);
        socket.send(data)
    }
}