package Views

import GraphicsPlayground.Views.FlowFieldViz
import Views.Resources.Interfaces.GraphicsView
import contentViewPortion
import controlViewPortion
import javafx.scene.layout.AnchorPane
import javafx.scene.layout.Pane
import javafx.scene.layout.StackPane
import javafx.scene.layout.VBox
import viewPadding

class ViewController(rootPane:Pane) {
    //Active List of all views

    var root = rootPane
    var controlView : Pane = VBox()
    var contentView : Pane = StackPane()

    private val views = arrayOf(
        FlowFieldViz(),
        MusicViz(),
        BarCodeViz(),
        BingeViz()
    )

    var currentView: GraphicsView = initView()

    init {
        AnchorPane.setTopAnchor(controlView,viewPadding)
        AnchorPane.setLeftAnchor(controlView,viewPadding)
        AnchorPane.setBottomAnchor(controlView,viewPadding)

        AnchorPane.setTopAnchor(contentView,viewPadding)
        AnchorPane.setRightAnchor(contentView,viewPadding)
        AnchorPane.setBottomAnchor(contentView,viewPadding)


        controlView.prefWidthProperty().bind(root.widthProperty().multiply(controlViewPortion))
        controlView.prefHeightProperty().bind(root.heightProperty())

        contentView.prefWidthProperty().bind(root.widthProperty().multiply(contentViewPortion))
        contentView.prefHeightProperty().bind(root.heightProperty())


        controlView.styleClass.add("ControlView")
        contentView.styleClass.add("ContentView")
    }

    fun goto(viewName: String): Pane? {
        var target = currentView
        var changed = false
        for (view in views) {
            if (view.label == viewName) {
                if (view != currentView) {
                    target = view
                    changed = true
                }
            }
        }
        if (changed) {
            currentView.onClose()
            currentView = target
            currentView.willOpen()
            return currentView.root
        }
        return null
    }

    fun getViewLabels(): List<String> {
        return List(views.size) {
            views[it].label
        }
    }

    fun setMaximumSize(width:Double,height:Double){
        currentView.root.setMaxSize(width,height)
    }

    inner class initView : GraphicsView {

        override fun willOpen() {

        }

        override val styleClass: String = "InitView"
        override val label: String = "Nunca"
        override var root: Pane = Pane()

        override fun onOpen() {
        }

        override fun onClose() {
        }

    }

}