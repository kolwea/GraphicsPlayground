package Playground

import Views.ViewController
import javafx.application.Application
import javafx.geometry.Pos
import javafx.scene.Scene
import javafx.scene.SceneAntialiasing
import javafx.scene.control.Button
import javafx.scene.layout.*
import javafx.stage.Stage
import java.awt.Toolkit


class GraphicsPlayground : Application() {
    lateinit var root: AnchorPane
//    lateinit var root: BorderPane
    lateinit var scene: Scene
    lateinit var viewController: ViewController
    lateinit var buttonBar: HBox
    lateinit var stage: Stage
    private val window = Toolkit.getDefaultToolkit().screenSize!!


    override fun start(primaryStage: Stage?) {
        root = AnchorPane()
        viewController = ViewController(root)

        root.children.addAll(viewController.contentView,viewController.controlView)

        scene = Scene(root, width, height, true, SceneAntialiasing.DISABLED)
        scene.stylesheets.add(stylesheet)

        root.styleClass.add("GraphicsPlaygroundView")
        root.maxHeightProperty().bind(scene.heightProperty())
        root.maxWidthProperty().bind(scene.widthProperty())


        stage = primaryStage!!
        stage.isFullScreen = fullscreen
        stage.fullScreenProperty().addListener { _, oldValue, newValue ->
            println("Old: $oldValue, New: $newValue")
        }

        stage.scene = scene
        stage.show()
    }

    private fun setupScene() {

    }

    private fun setupButtons() {
//        buttonBar = HBox()
//        buttonBar.spacing = 10.0
//        buttonBar.alignment = Pos.BASELINE_CENTER
//        buttonBar.isFillHeight = true
//        root.bottom = buttonBar
//        for (view in viewController.getViewLabels()) {
//            val currButton = ViewButton(view)
//            buttonBar.children.add(currButton)
//        }
    }

    private fun setupStage(primaryStage:Stage){

    }

    private fun goto(target: String) {
        val targetView = viewController.goto(target)
        if (targetView != null) {
//            root.center = targetView
            viewController.currentView.onOpen()
            viewController.setMaximumSize(root.width,root.height - buttonBar.height)
//            root.bottom = null
//            root.bottom = buttonBar
//            root.bottom.autosize()
//            updateBoundaries(targetView)
//            buttonBar.toFront()
        }
    }

    private fun updateBoundaries(view: Pane) {
//        root.center.maxHeight(scene.height - buttonBar.height)
        view.setMaxSize(scene.width, scene.height - buttonBar.height)
    }

    private fun updateBoundaries() {
        viewController.currentView.root.setPrefSize(root.width, root.height - buttonBar.height)
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

