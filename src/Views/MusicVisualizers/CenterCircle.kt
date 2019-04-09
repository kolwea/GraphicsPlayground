package Views.MusicVisualizers

import javafx.scene.layout.AnchorPane
import javafx.scene.layout.Pane
import javafx.scene.paint.Color
import javafx.scene.shape.Circle
import java.awt.Toolkit

class CenterCircle : Visualizer {

    override val name: String = "Center Circle"
    override var root: Pane = Pane()
    var numBands: Int? = null
    lateinit var circles: Array<AudioCircle>
    val circleGrowFactor = 50.0
    var k = 0

    init {
        root.styleClass.add("CenterCircle")
        root.setPrefSize(400.0, 400.0)
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

        for (i in 0 until phases!!.size) {
            circles[i].apply {
                radius = this.originalRadius + (magnitudes!![i].toDouble() + 60.0)
//                moveCircle(this)
//                if (k == 300) {
//                    println("Mag:")
//                    magnitudes!!.iterator().forEach { println("$it ") }
//                    println()
//
//                    println("Phases:")
//                    phases.iterator().forEach { println("$it") }
//                    k = 0
//                    println()
//                }
            }
        }
        k+=1
    }

    override fun end() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun map(value: Double, inMin: Double, inMax: Double, outMin: Double, outMax: Double): Double {
        return outMin + (outMax - outMin) / (inMax - inMin) * (value - inMin)
    }

    private fun setupCircles() {
        circles = Array(numBands!!) {
            AudioCircle().apply {
                originalRadius = 30.0
                radius = originalRadius
                fill = Color.hsb(255.0 / numBands!! * it, 1.0, 1.0, 0.03)
                strokeWidth = 2.0
                stroke = Color.BLACK
                centerX = Math.random() * Toolkit.getDefaultToolkit().screenSize.width
                centerY = Math.random() * Toolkit.getDefaultToolkit().screenSize.height
                println(it)
            }
        }

        for (i in 0 until circles.size) {
            if (i + 1 < circles.size - 1) {
                circles[i].nextRadius = circles[i + 1].originalRadius
            }
        }
    }

    private fun recursiveAdd(num: Int): Int {
        if (num >= 1) {
            return num + recursiveAdd(num - 1)
        } else {
            return num
        }
    }

    private fun moveCircle(circ: AudioCircle) {
        var screenSize = Toolkit.getDefaultToolkit().screenSize
        if ((circ.radius + circ.centerX >= screenSize.width) || (circ.centerX - circ.radius <= 0))
            circ.dirX *= -1.0
        if ((circ.radius + circ.centerY >= screenSize.height) || (circ.centerY - circ.radius <= 0))
            circ.dirY *= -1.0

        circ.centerX += circ.dirX
        circ.centerY += circ.dirY

    }


    inner class AudioCircle : Circle() {
        var originalRadius = 0.0
        var nextRadius = circleGrowFactor
        var dirX = Math.random() * 1.0 + 1.0
        var dirY = Math.random() * 1.0 + 1.0

    }


}