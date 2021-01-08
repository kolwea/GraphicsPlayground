package Views

import Views.Resources.Interfaces.GraphicsView
import javafx.animation.Animation
import javafx.animation.KeyFrame
import javafx.animation.Timeline
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.layout.Pane
import javafx.scene.paint.Color
import javafx.scene.shape.Circle
import javafx.util.Duration
import java.awt.Toolkit
import kotlin.random.Random

class BingeViz : GraphicsView {
    override val label: String = "Binge"
    override var root: Pane = Pane()
    override val styleClass: String = "BingeViz"

    lateinit var timeline: Timeline
    val frameRate = 0.5

    val bubbleGen = BubbleGen()
    var bubbleGenDelay = 100
    var bubbleGenDelayCount = 0
    val bubbles = ArrayList<Bubble>()

    init {
        root.styleClass.add(styleClass)
        setupTimeline()
    }

    override fun willOpen() {
        root.children.addAll(bubbles)
        bubbles.iterator().forEach {
            it.toFront()
        }
    }

    override fun onOpen() {
        timeline.play()
    }

    override fun onClose() {
        timeline.stop()
    }

    private fun setupTimeline() {
        val event = EventHandler<ActionEvent> { update() }
        val keyframe = KeyFrame(Duration.millis(frameRate), event)
        timeline = Timeline(keyframe)
        timeline.cycleCount = Animation.INDEFINITE
    }

    private fun update() {
        if(bubbleGenDelayCount == bubbleGenDelay){
            val bub = bubbleGen.generateBubble()
            bubbles.add(bub)
            root.children.add(bub)
            bubbleGenDelayCount = 0
        }

        val remove = ArrayList<Bubble>()
        for (bubble in bubbles) {
            if (bubble.ticksTillDeath >= 0) {
                bubble.applyGravity()
                bubble.update()
            } else {
                remove.add(bubble)
            }
        }

        bubbleGenDelayCount += 1
        root.children.removeAll(remove)
        bubbles.removeAll(remove)
        bubbles.trimToSize()
    }

    inner class Bubble(radius: Double) : Circle() {
        val maxVelocity = 0.7
        val gravity = -0.000001
        var ticksTillDeath = 20000
        var speedX = 0.0
        var speedY = 0.0

        init {
            this.radius = radius
            styleClass.add("BingeBubble")
        }

        fun applyGravity() {
            if (Math.abs(speedY) <= maxVelocity)
                speedY += gravity * radius
        }

        fun update() {
            centerX += speedX
            centerY += speedY
            ticksTillDeath -= 1
        }

    }

    inner class BubbleGen {
        fun generateBubble(): Bubble {
            val bubble = Bubble(Random.nextDouble(10.0, 25.0)).apply {
                centerX = Random.nextDouble(0.0, Toolkit.getDefaultToolkit().screenSize.getWidth())
                centerY = Toolkit.getDefaultToolkit().screenSize.getHeight() + radius + Math.random() * 20.0
                fill = Color.GOLD.apply {
                    setOpacity(0.5)
                }
            }
            return bubble
        }
    }


}