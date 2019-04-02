package Views

import GraphicsPlayground.Views.FlowField
import GraphicsPlayground.Views.GraphicsView

class ViewController(){

    val views = arrayOf("FlowField", "NoiseField", "Rasterizer Engine", "Ray Tracing Engine", "TigerHacks View")

    lateinit var currentView : GraphicsView

    init{

    }

    fun goto(viewName : String):GraphicsView?{
        when(viewName){
            "FlowField" -> return FlowField()
            else -> return null
        }
    }
}