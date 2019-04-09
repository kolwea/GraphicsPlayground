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
import javafx.scene.layout.StackPane
import javafx.scene.shape.Rectangle
import javafx.stage.Screen
import javafx.stage.Stage
import sun.tools.jstat.Alignment
import java.awt.Toolkit
import java.util.*
import javax.lang.model.element.NestingKind
import javafx.stage.Screen.getPrimary



class GraphicsPlayground : Application() {

    private val root = BorderPane()
    var scene = Scene(root, width, height, true, SceneAntialiasing.DISABLED)
    var viewController = ViewController()
    lateinit var buttonBar: HBox
    lateinit var stage: Stage
    private val window = Toolkit.getDefaultToolkit().screenSize!!


    override fun start(primaryStage: Stage?) {
        scene.stylesheets.addAll(stylesheet)
        root.styleClass.add("main")
        root.prefHeightProperty().bind(scene.heightProperty())
        root.prefWidthProperty().bind(scene.widthProperty())
        setupButtons()
        stage = primaryStage!!
        stage.isFullScreen = fullscreen
        stage.fullScreenProperty().addListener { observable, oldValue, newValue ->
            println("Old: $oldValue, New: $newValue")
//            val screen = Screen.getPrimary()
//            val bounds = screen.getVisualBounds()
//            primaryStage.x = bounds.getMinX()
//            primaryStage.y = bounds.getMinY()
//            primaryStage.width = bounds.getWidth()
//            primaryStage.height = bounds.getHeight()
        }
        stage.scene = scene
        stage.show()
    }

    private fun setupButtons() {
        buttonBar = HBox()
        buttonBar.spacing = 10.0
        buttonBar.alignment = Pos.BASELINE_CENTER
        root.bottom = buttonBar
        for (view in viewController.getViewLabels()) {
            val currButton = ViewButton(view)
            buttonBar.children.add(currButton)
        }
    }

    private fun goto(target: String) {
        val targetView = viewController.goto(target)
        if (targetView != null) {
            root.center = targetView as StackPane
            viewController.currentView.onOpen()
        }
        root.bottom.autosize()
        buttonBar.toFront()
    }


    inner class ViewButton(text: String?) : Button(text) {
        init {
            this.text = text
            this.styleClass.add("main-button")
            this.setOnAction {
                goto(this.text)
            }
        }
    }


}

