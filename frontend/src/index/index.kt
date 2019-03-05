package index

import react.dom.*
import slider.app
import kotlin.browser.*

fun main() {

    val root = document.getElementById("root")

    render(root) {
        app()
    }

}

