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


    override val label: String = "Music Visualizer"
    override var root: Pane = StackPane()

    lateinit var media: Media
    lateinit var player: MediaPlayer
    lateinit var mediaView: MediaView

    var visualizer = CenterCircle()

    val spectrumBands = 10
    val updateinterval = 0.01

    init {
        root.styleClass.add("MusicViz")
        mediaView = MediaView()
        root.children.add(mediaView)
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
        player.setAudioSpectrumListener { timestamp, duration, magnitudes, phases ->
            visualizer.update(timestamp, duration, magnitudes, phases)
        }
        player.isAutoPlay = true
    }

    private fun onReady() {
        visualizer.start(spectrumBands,root)
    }

    private fun onEnd() {

    }


//    private fun handleReady(){
//        val duration = mediaPlayer.getTotalDuration()
//        lengthText.setText(duration.toString())
//        val ct = mediaPlayer.getCurrentTime()
//        currentText.setText(ct.toString())
//        currentVisualizer.start(numBands, vizPane)
//        timeSlider.setMin(0.0)
//        timeSlider.setMax(duration.toMillis())
//    }

}