package Views

import GraphicsPlayground.Views.GraphicsView
import javafx.scene.layout.Pane
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

class MusicViz:GraphicsView {


    override val label: String = "Music Visualizer"
    override var root: Pane = Pane()

    override fun onOpen() {

    }

    override fun onClose() {
        
    }

    override fun willOpen() {

    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//    lateinit var player : SamplePlayer
////    lateinit var sample : Sample
////    var context = AudioContext()
////
////    lateinit var analyzer: Analyzer
////    var fft: FFT = FFT()
////    val beads = BeadArray()
////
////
////
////    private fun setup(){
////
////        sample = Sample("/Users/kolbe/IdeaProjects/GraphicsPlayground/src/Views/Copy of Liberty City House (Updatedd).wav")
////        player = SamplePlayer(context,sample)
////        val g = Gain(context, 2, 0.2f)
////        g.addInput(player)
////        context.out.addInput(g)
////        context.start()
////
////        println("Number Frames: ${sample.numFrames}")
////        println("Number Channels: ${sample.numChannels}")
////
////    }
////
////    inner class analSettings:FFT(){

    }
}