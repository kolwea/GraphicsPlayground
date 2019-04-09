package Views

import GraphicsPlayground.Views.GraphicsView
import javafx.scene.layout.AnchorPane
import javafx.scene.layout.Pane
import javafx.scene.layout.StackPane
import javafx.scene.shape.Rectangle
import kotlin.random.Random

class BarCodeViz : GraphicsView {
    override val styleClass: String = "BarCodeViz"
    override val label: String = "BarCode"
    override var root: Pane = StackPane()

    private val barPane = AnchorPane()
    private val divisions = 20
    private val widthPercentage = 0.5
    private val min = 1
    private val max = 5

    init {
        root.styleClass.add(styleClass)
        barPane.styleClass.add("BarBox")
    }

    override fun willOpen() {
        setupView(300.0, 300.0)
    }

    override fun onOpen() {

    }

    override fun onClose() {
        root.children.clear()
    }

    private fun setupView(width: Double, height: Double) {

        fun getBarWidths(): Array<Double> {
            //Bars need to take up a certain portion of box, also need to be of variable widths
            //Create array of random portions

            val randomPortions = Array(divisions) {
                Random.nextInt(min, max)
            }

            //Add them to find mix portion count
            var sum = 0.0
            randomPortions.iterator().forEach {
                sum += it
            }

            val widthAmount = width * widthPercentage

            //Use portions to find variable width of "bar"
            val barWidths = Array(randomPortions.size) {
                widthAmount * (randomPortions[it] / sum)
            }

            return barWidths
        }

        barPane.setMaxSize(width, height)

        val barWidths = getBarWidths()

        val bars = Array(divisions) {
            var offset = 0.0
            for (i in 0..it) {
                if(i!= 0)
                    offset += barWidths[i] * 2
            }
            println("Offset: $offset")
            Bar(
                barWidths[it],
                if(it==0 || it == divisions-1)height else height - 10.0,
                if(it != divisions - 1) offset else width - barWidths[it]
                , 0.0)
        }

        bars.iterator().forEach {
            barPane.children.add(it.shape)
        }

        root.children.add(barPane)
    }

    inner class Bar(width: Double = 10.0, height: Double = 10.0, startX: Double = 0.0, startY: Double) {
        var shape: Rectangle = Rectangle()
        var changing = false
        var originalWidth = 0.0
        var targetWidth = 0.0

        val changeSpeed = 1.0

        init {
            shape.width = width
            shape.height = height
            shape.x = startX
            shape.y = startY
            originalWidth = shape.width
            println("Width: $width, X: ${shape.x}, Y:${shape.y}")
        }

        fun gotoWidth(target : Double){
            changing = true
            targetWidth = target
        }

        fun updateBar(){
            val difference = Math.abs(shape.width - targetWidth)
            if(difference >= 1.0){

            }
            else{

            }

        }
    }


}