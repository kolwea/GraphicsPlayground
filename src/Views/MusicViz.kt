package Views

import GraphicsPlayground.Views.GraphicsView
import Views.MusicVisualizers.CenterCircle
import javafx.scene.layout.AnchorPane
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
import sun.plugin.javascript.navig.Anchor
import java.io.File
import java.time.Duration
import java.util.*

class MusicViz : GraphicsView {
    override val styleClass: String = "MusicViz"
    override val label: String = "Music Visualizer"
    override var root: Pane = Pane()
    override val styleClass: String = "MusicViz"

    lateinit var media: Media
    lateinit var player: MediaPlayer
    private var mediaView: MediaView = MediaView()

    var visualizer = CenterCircle()

    val spectrumBands = 10
    val updateinterval = 0.01

    init {
        root.styleClass.add(styleClass)
    }

    override fun onOpen() {
    }

    override fun onClose() {

    }

    override fun willOpen() {
        openMedia()
        setupPlayer()
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    fun openMedia() {
        val file =
            File("/Users/kolbe/IdeaProjects/GraphicsPlayground/src/Views/Copy of Liberty City House (Updatedd).wav")
        media = Media((file.toURI().toString()))
        setupPlayer()
    }

    private fun setupPlayer() {
        player = MediaPlayer(media)
        mediaView.mediaPlayer = player
        mediaView.styleClass.add("MediaView")

        player.setOnReady { onReady() }
        player.setOnEndOfMedia { onEnd() }
        player.audioSpectrumInterval = updateinterval
        player.audioSpectrumNumBands = spectrumBands
        player.audioSpectrumListener = visualizer
        player.isAutoPlay = true
    }

    private fun onReady() {
        visualizer.start(spectrumBands)
        root.children.clear()
        root.children.add(visualizer.root)
    }

    private fun onEnd() {

    }
}