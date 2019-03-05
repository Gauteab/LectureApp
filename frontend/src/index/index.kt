package index

import react.dom.*
import slider.App
import kotlin.browser.*

fun main() {

    val root = document.getElementById("root")

    render(root) {
        child(App::class) {}
    }

}

