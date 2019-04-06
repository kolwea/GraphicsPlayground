package Playground

import Views.ViewController
import javafx.application.Application
import javafx.geometry.Pos
import javafx.scene.Scene
import javafx.scene.SceneAntialiasing
import javafx.scene.control.Button
import javafx.scene.layout.BorderPane
import javafx.scene.layout.HBox
import javafx.scene.layout.Pane
import javafx.scene.shape.Rectangle
import javafx.stage.Stage
import sun.tools.jstat.Alignment
import javax.lang.model.element.NestingKind

class GraphicsPlayground : Application() {

    val root = BorderPane()
    var scene = Scene(root, width, height, true, SceneAntialiasing.DISABLED)
    var viewController = ViewController()
    lateinit var buttonBar: HBox
    lateinit var stage : Stage

    override fun start(primaryStage: Stage?) {
        scene.stylesheets.addAll(stylesheet)
        root.styleClass.add("main")
        setupButtons()
        stage = primaryStage!!
        stage.isFullScreen = fullscreen
        stage.scene = scene
        stage.show()
    }

    private fun setupButtons(){
        buttonBar = HBox()
        buttonBar.spacing = 10.0
        buttonBar.alignment = Pos.BASELINE_CENTER
        root.bottom = buttonBar
        for (view in viewController.getViewLabels()){
            val currButton = ViewButton(view)
            buttonBar.children.add(currButton)
        }
    }

    private fun goto(target : String){
        val targetView = viewController.goto(target)
        if (targetView!= null){
            root.center = targetView
            viewController.currentView.onOpen()
        }
        buttonBar.toFront()
    }

    private fun setupFunctionality(){

    }

    inner class ViewButton(text: String?): Button(text) {
        init {
            this.text = text
            this.styleClass.add("main-button")
            this.setOnAction {
                goto(this.text)
            }
        }
    }


}

