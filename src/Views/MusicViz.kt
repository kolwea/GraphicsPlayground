package Views


import Views.Resources.Interfaces.GraphicsView
import Views.Resources.Visualizers.CenterCircle

import javafx.scene.layout.Pane
import javafx.scene.layout.StackPane
import javafx.scene.media.Media
import javafx.scene.media.MediaPlayer
import javafx.scene.media.MediaView
import java.io.File

class MusicViz : GraphicsView {
    override val styleClass: String = "MusicViz"
    override val label: String = "Music Visualizer"
    override var root: Pane = StackPane()

    lateinit var media: Media
    lateinit var player: MediaPlayer
    private var mediaView: MediaView = MediaView()

    var visualizer = CenterCircle()

    val spectrumBands:Int? = null
    val updateinterval = 0.01
    val audioThreshold = -100

    init {
        root = visualizer.root
    }

    override fun onOpen() {

    }

    override fun onClose() {
        player.stop()
    }

    override fun willOpen() {
        openMedia()
        setupPlayer()
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    fun openMedia() {
        val file =
            File("D:/Users/Kolbe/Desktop/why piano.wav")

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
        if(spectrumBands!=null)
            player.audioSpectrumNumBands = spectrumBands
        player.audioSpectrumListener = visualizer
        player.isAutoPlay = true
        player.audioSpectrumThreshold = audioThreshold
    }

    private fun onReady() {
        visualizer.start(player.audioSpectrumNumBands)
    }

    private fun onEnd() {

    }
}