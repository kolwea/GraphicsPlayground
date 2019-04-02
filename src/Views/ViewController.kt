package Views

import GraphicsPlayground.Views.FlowField
import GraphicsPlayground.Views.GraphicsView
import javafx.scene.layout.Pane

class ViewController() {
    //Active List of all views
    val views: List<GraphicsView> = List(2) {
        when (it) {
            0 -> FlowField()
            1 -> MusicVisualizer()
            else -> FlowField()
        }
    }

    var currentView: GraphicsView = views[0]

    fun goto(viewName: String): Pane? {
        var target = currentView
        for (view in views) {
            if(view.label == viewName)
                target = view
        }

        if (target != currentView) {
            currentView.onClose()
            currentView = target
            currentView.onOpen()
            return currentView.root
        } else
            return null
    }


    fun getViewLabels(): List<String> {
        return List(views.size) {
            views[it].label
        }
    }
}