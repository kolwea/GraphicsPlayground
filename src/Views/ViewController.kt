package Views

import GraphicsPlayground.Views.FlowFieldViz
import GraphicsPlayground.Views.GraphicsView
import javafx.scene.layout.Pane

class ViewController() {
    //Active List of all views
    val views = arrayOf(
        FlowFieldViz(),
        MusicViz(),
        BarCodeViz())

    var currentView: GraphicsView = initView()

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

    inner class initView : GraphicsView {
        override fun willOpen() {
        }

        override val label: String = "Nunca"
        override var root: Pane = Pane()

        override fun onOpen() {
        }

        override fun onClose() {
        }

    }

}