package Views

import GraphicsPlayground.Views.GraphicsView
import Views.MusicVisualizers.CenterCircle
import javafx.scene.layout.Pane
import javafx.scene.media.Media
import javafx.scene.media.MediaPlayer
import javafx.scene.media.MediaView
import java.io.File

class MusicViz : GraphicsView {
    override val styleClass: String = "MusicViz"
    override val label: String = "Music Visualizer"
    override var root: Pane = Pane()

    lateinit var media: Media
    lateinit var player: MediaPlayer
    private var mediaView: MediaView = MediaView()

    var visualizer = CenterCircle()

    val spectrumBands = 10
    val updateinterval = 0.01

    init {
        root.styleClass.add("MusicViz")
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