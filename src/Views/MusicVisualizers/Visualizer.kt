package Views.MusicVisualizers

import javafx.scene.layout.Pane

interface Visualizer {
    val name: String
    fun start(numBands: Int?, vizPane: Pane)
    fun end()
    fun update(timestamp: Double, duration: Double, magnitudes: FloatArray, phases: FloatArray)
}