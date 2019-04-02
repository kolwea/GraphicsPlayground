package GraphicsPlayground.Views

import javafx.scene.layout.Pane

interface GraphicsView {
    var root : Pane

    fun getView():Pane?
}