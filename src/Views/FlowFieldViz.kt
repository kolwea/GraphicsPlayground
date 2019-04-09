package GraphicsPlayground.Views

import com.sun.org.apache.xml.internal.security.utils.JavaUtils
import javafx.scene.layout.Pane
import javafx.scene.shape.Line
import java.awt.Toolkit
import javax.tools.Tool

class FlowFieldViz : GraphicsView {
    override val label: String = "FlowFieldViz"
    override var root: Pane = Pane()
    override val styleClass = "FlowField"

    val troubleShooting = true

    val hDiv = 10
    val vDiv = 15

    init {
        root.styleClass.add(styleClass)
    }

    override fun willOpen() {

    }

    override fun onOpen() {
        setup()
        if (troubleShooting)
            troubleShooting()
    }

    override fun onClose() {
        root.children.clear()
    }

    private fun setup() {

    }

    private fun troubleShooting(){
        val width = Toolkit.getDefaultToolkit().screenSize.width.toDouble()
        val height = Toolkit.getDefaultToolkit().screenSize.height.toDouble()

        val hSpacing = height/hDiv
        val vSpacing = width/vDiv

        for(i in 0 until hDiv){
            val pad = (i * hSpacing) - hSpacing/2
            val bar = Line(0.0,pad,width,pad)
            bar.strokeWidth = 5.0
            root.children.add(bar)
        }

        for(i in 0 until vDiv){
            val pad = (i * vSpacing) - vSpacing/2
            val bar = Line(pad,0.0,pad,height)
            bar.strokeWidth = 5.0
            root.children.add(bar)
        }
    }

    private fun generateFlowGrid(verticalDivisions : Int, horizontaldivisions :Int){

    }

    private fun setupParticles(){

    }
}