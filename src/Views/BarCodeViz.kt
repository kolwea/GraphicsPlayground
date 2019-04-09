package Views

import GraphicsPlayground.Views.GraphicsView
import javafx.scene.layout.Pane
import javafx.scene.layout.StackPane
import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle
import kotlin.random.Random

class BarCodeViz : GraphicsView {
    override val label: String = "Barcode"
    override var root: Pane = StackPane()

    val divisions = 10
    val widthPercentage = 0.5
    val min = 1
    val max = 5

    override fun willOpen() {
        setupView(300.0, 300.0)
    }

    override fun onOpen() {

    }

    override fun onClose() {
        root.children.clear()
    }

    private fun setupView(width: Double, height: Double) {
        root.styleClass.add("barcode")
        var box = Pane()

        fun setupBoundingBox() {
            box.styleClass.add("boundingBox")
            box.setPrefSize(width, height)
            root.children.add(box)
        }

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
//                print("$it")
            }

            val widthAmount = width * widthPercentage

            println()
            println("Sum: $sum")
            println("Width amount: $widthAmount")

            //Use portions to find variable width of "bar"
            val barWidths = Array(randomPortions.size) {
                widthAmount * (randomPortions[it] / sum)
            }

            println("Bar Widths: ")
            print("[")
            barWidths.iterator().forEach {
                print("$it-")
            }

            print("]")
            println()

            var widthSum = 0.0
            barWidths.iterator().forEach { widthSum += it }

            println("BarWidth Percentage vs. Width Sums: ${width * widthPercentage} : $widthSum")
            return barWidths
        }

        setupBoundingBox()
        val barWidths = getBarWidths()

        val bars = Array(divisions) {
            Bar().apply {
                this.width = barWidths[it];
                this.height = height
            }
        }

        for (i in 0 until bars.size) {
            val curr = bars[i]
            if (i - 1 > 0) {
                curr.offset = bars[i - 1].width * 2
                curr.updateBar()
                println("${curr.width}")
            }
            box.children.add(curr.shape)
        }

    }

    inner class Bar {
        var width: Double = 50.0
        var height: Double = 0.0
        var offset: Double = 0.0
        var shape = Rectangle()

        fun updateBar() {
            shape.width = width
            shape.x = offset
            shape.y = height
            shape.fill = Color.ALICEBLUE
            shape.toFront()

            println("Width: $width, X: ${shape.x}, Y:${shape.y}")
        }
    }


}