package Views.MusicVisualizers

import javafx.scene.layout.Pane
import javafx.scene.paint.Color
import javafx.scene.shape.Circle
import java.awt.Toolkit

class CenterCircle : Visualizer {

    override val name: String = "Center Circle"
    override var root: Pane = Pane()
    var numBands: Int? = null
    lateinit var circles: Array<AudioCircle>

    private val minMagnitudeThreshold = 5.0
    private val circleRadiusSpacing = 1.0

    init {
        val window = Toolkit.getDefaultToolkit().screenSize
        root.styleClass.add("CenterCircle")
        root.setPrefSize(300.0,300.0)
    }


    override fun start(numBands: Int) {
        this.numBands = numBands
        setupCircles()
        root.children.addAll(circles)
    }

    override fun spectrumDataUpdate(timestamp: Double, duration: Double, magnitudes: FloatArray?, phases: FloatArray?) {
        var a = true
        if (a) {
            root.children.clear()
            root.children.addAll(circles)
            a = false
        }

        for (i in 0 until magnitudes!!.size) {
            if (-magnitudes[i] >= minMagnitudeThreshold)
                circles[i].update(magnitudes[i].toDouble())
//            moveCircle(circles[i])
        }
    }

    override fun end() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun setupCircles() {
        circles = Array(numBands!!) {
            AudioCircle().apply {
                fill = Color.hsb(255.0 / numBands!! * it, 1.0, 1.0, 0.03)
                strokeWidth = 2.0
                stroke = Color.BLACK
                minSize = circleRadiusSpacing
                centerX = root.width / numBands!! * it
                centerY = root.height / numBands!! * it
            }
        }

        for (i in circles.size - 1 downTo 0) {
            circles[i].toFront()
        }
    }

    private fun map(value: Double, inMin: Double, inMax: Double, outMin: Double, outMax: Double): Double {
        return outMin + (outMax - outMin) / (inMax - inMin) * (value - inMin)
    }

    inner class AudioCircle : Circle() {
        private val changeValue = -0.5
        var minSize = 20.0
        var maxSize = minSize + 80.0 // The largest difference in magnitude
        private val changeThreshold = 10.0

        var originX = 0.0
        var originY = 0.0

        init {
            radius = minSize
        }

        fun update(magnitude: Double) {
            val normalizedMagnitude = magnitude + 100.0
            val targetRadius = normalizedMagnitude + minSize

            when {
                targetRadius > maxSize -> {
                    maxSize = targetRadius
                    radius = targetRadius
                }
                targetRadius >= radius + changeThreshold ->
                    radius = targetRadius
                else -> {
                    val mapDistFromOriginalRadius = map(radius, minSize, maxSize, 0.0, Math.PI / 2)
                    val speed = Math.sin(mapDistFromOriginalRadius)
                    radius += changeValue * speed
                }
            }
        }


    }


}