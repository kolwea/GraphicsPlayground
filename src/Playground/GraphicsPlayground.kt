package Playground

import Views.ViewController
import javafx.application.Application
import javafx.geometry.Pos
import javafx.scene.Scene
import javafx.scene.SceneAntialiasing
import javafx.scene.control.Button
import javafx.scene.layout.BorderPane
import javafx.scene.layout.HBox
import javafx.scene.shape.Rectangle
import javafx.stage.Stage
import sun.tools.jstat.Alignment
import javax.lang.model.element.NestingKind

class GraphicsPlayground : Application() {

    val root = BorderPane()
    val scene = Scene(root, width, height, true, SceneAntialiasing.DISABLED)
    val viewController = ViewController()

    override fun start(primaryStage: Stage?) {
        scene.stylesheets.addAll(stylesheet)
        root.styleClass.add("main")
        setupButtons()
        primaryStage?.isFullScreen = fullscreen
        primaryStage?.scene = scene
        primaryStage?.show()
    }

    fun setupButtons(){
        val buttonBar = HBox()
        buttonBar.spacing = 10.0
        buttonBar.alignment = Pos.BASELINE_CENTER
        root.bottom = buttonBar
        for (view in viewController.views){
            val currButton = ViewButton(view)
            buttonBar.children.add(currButton)
        }
    }

    inner class ViewButton(text: String?): Button(text) {
        init {
            this.text = text
            this.styleClass.add("main-button")
            this.setOnAction {
                println("Pressed Me!")
            }
        }

        fun goto(){

        }
    }


}

