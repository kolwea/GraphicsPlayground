package GraphicsPlayground.Views

import javafx.scene.layout.Pane
import javafx.scene.shape.Line

class FlowField : GraphicsView {
    override val label: String = "FlowField"
    override var root: Pane = Pane()

    val hDiv = 10
    val vDiv = 15

    init {
        root.styleClass.add("flowField")
    }

    override fun willOpen() {

    }

    override fun onOpen() {
//        setup()

    }

    override fun onClose() {

    }

    private fun setup() {
        val width = root.width
        val height = root.height

        val hSpacing = width/hDiv
        val vSpacing = height/vDiv

        for(i in 0 until hDiv){
            val pad = (i * hSpacing) - hSpacing/2
            val bar = Line(0.0,pad,width,pad)
            root.children.add(bar)
        }

        for(i in 0 until vDiv){
            val pad = (i * vSpacing) - vSpacing/2
            val bar = Line(pad,0.0,pad,height)
            root.children.add(bar)
        }
    }

}