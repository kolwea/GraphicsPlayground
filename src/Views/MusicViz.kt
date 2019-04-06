package Views

import GraphicsPlayground.Views.GraphicsView
import javafx.scene.layout.Pane
import javafx.scene.layout.StackPane
import javafx.scene.media.Media
import javafx.scene.media.MediaPlayer
import javafx.scene.media.MediaView
import net.beadsproject.beads.analysis.Analyzer
import net.beadsproject.beads.analysis.FeatureExtractor
import net.beadsproject.beads.analysis.FeatureManager
import net.beadsproject.beads.analysis.FeatureTrack
import net.beadsproject.beads.analysis.featureextractors.FFT
import net.beadsproject.beads.analysis.featureextractors.PowerSpectrum
import net.beadsproject.beads.core.*
import net.beadsproject.beads.data.Sample
import net.beadsproject.beads.data.SampleManager
import net.beadsproject.beads.ugens.Gain
import net.beadsproject.beads.ugens.SamplePlayer
import java.io.File
import java.util.*

class MusicViz : GraphicsView {


    override val label: String = "Music Visualizer"
    override var root: Pane = StackPane()

    lateinit var media: Media
    lateinit var player: MediaPlayer
    lateinit var mediaView: MediaView

    init {
        root.styleClass.add("MusicViz")
    }

    override fun onOpen() {

    }

    override fun onClose() {

    }

    override fun willOpen() {

    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    fun setup() {
        media = Media("Views/Copy of Liberty City House (Updatedd).wav")
        player = MediaPlayer(media)
        mediaView = MediaView(player)
        root.children.add(mediaView)
    }

}