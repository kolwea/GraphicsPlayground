package Views.Resources.Interfaces

import javafx.scene.layout.Pane
import javafx.scene.media.AudioSpectrumListener

interface Visualizer:AudioSpectrumListener {
    val name: String
    var root : Pane
    fun start(numBands: Int)
    fun end()
}