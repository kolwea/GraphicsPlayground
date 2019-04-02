package GraphicsPlayground.Views

import javafx.scene.layout.Pane

class FlowField : GraphicsView{
    override val label: String = "Flowfield"
    override var root: Pane = Pane()


    override fun onOpen() {

    }

    override fun onClose() {

    }


    init {
        root.styleClass.add("flowField")
    }

}