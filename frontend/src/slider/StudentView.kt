package slider

import kotlinx.html.InputType
import kotlinx.html.js.onChangeFunction
import network.WsConnection
import org.w3c.dom.HTMLInputElement
import react.*
import react.dom.h1
import react.dom.input
import react.dom.p

class StudentView : RComponent<RProps, StudentView.State>() {

    private val ws = WsConnection()

    init { state = State("50") }

    override fun RBuilder.render() {
        display()
        slider()
    }

    private fun RBuilder.display() {
        h1 { +"StudentView" }
        p  { +"Slider Value: ${state.sliderValue}" }
    }

    private fun RBuilder.slider() {
        input(InputType.range) {
            attrs.value = state.sliderValue
            attrs.onChangeFunction = { event ->
                when (val slider = event.target) {
                    is HTMLInputElement -> setState { sliderValue = slider.value }
                    else -> throw IllegalArgumentException("Unhandled event target: ${event.target}")
                }
            }
        }
    }

    override fun componentDidUpdate(prevProps: RProps, prevState: State, snapshot: Any) {
        println("Sending: ${state.sliderValue}")
        ws.send(state.sliderValue)
    }

    class State(var sliderValue: String) : RState

}

fun RBuilder.student() = child(StudentView::class) {}
