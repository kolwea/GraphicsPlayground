package GraphicsPlayground.Views

import javafx.scene.layout.Pane

interface GraphicsView {
    val label : String
    var root:Pane

    fun willOpen()
    fun onOpen()
    fun onClose()
}