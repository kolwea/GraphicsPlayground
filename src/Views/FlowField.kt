package GraphicsPlayground.Views

import javafx.scene.layout.Pane

class FlowField : GraphicsView{

    override var root: Pane = Pane()

    init {
        root.styleClass.add("flowField")
    }

    override fun getView(): Pane? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}