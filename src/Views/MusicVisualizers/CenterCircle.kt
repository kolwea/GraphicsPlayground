package Views.MusicVisualizers

import javafx.scene.layout.Pane
import javafx.scene.paint.Color
import javafx.scene.shape.Circle
import kotlin.math.sin

class CenterCircle:Visualizer{
    override val name: String = "Center Circle"
    var root : Pane? = null
    var numBands : Int? = null
    lateinit var circles : Array<Circle>
    val circleGrowFactor = 500.0

    val hueMaxAmount = 239.0

    override fun start(numBands: Int?, vizPane: Pane) {
        this.numBands = numBands
        this.root = vizPane

        val div = recursiveAdd(numBands!!)

        circles = Array(numBands!!){
            AudioCircle().apply {
                originalRadius = (it+1) * 20.0
                radius = originalRadius
                fill = Color.hsb( 255.0/numBands * it,1.0,1.0,0.03)
                strokeWidth = 2.0
                stroke = Color.BLACK
                this.toBack()
            }
        }

        for(i in 0 until circles.size){
            if(i+1 < circles.size-1){
                (circles[i] as AudioCircle).nextRadius = (circles[i+1] as AudioCircle).originalRadius
            }
        }

        vizPane.children.addAll(circles)
    }

    override fun end() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun update(timestamp: Double, duration: Double, magnitudes: FloatArray, phases: FloatArray) {
        for (i in 0 until  phases.size){
            (circles[i] as AudioCircle).apply {
                radius = map(phases[i].toDouble(),-1.0,1.0, this.originalRadius, this.nextRadius) * Math.sin(map(phases[i].toDouble(),-1.0,1.0,0.0,Math.PI))
            }
        }
    }

    fun map(value: Double, inMin: Double, inMax: Double, outMin: Double, outMax: Double): Double {
        return outMin + (outMax - outMin) / (inMax - inMin) * (value - inMin)
    }

    fun recursiveAdd(num: Int):Int{
        if (num >= 1){
            return num + recursiveAdd(num-1)
        }
        else{
            return num
        }
    }

    inner class AudioCircle:Circle(){
        var originalRadius = 0.0
        var nextRadius = circleGrowFactor

    }

}