package Views

import GraphicsPlayground.Views.GraphicsView
import javafx.scene.layout.Pane
import net.beadsproject.beads.analysis.Analyzer
import net.beadsproject.beads.analysis.FeatureTrack
import net.beadsproject.beads.analysis.featureextractors.FFT
import net.beadsproject.beads.analysis.featureextractors.PowerSpectrum
import net.beadsproject.beads.core.AudioContext
import net.beadsproject.beads.core.Bead
import net.beadsproject.beads.core.TimeStamp
import net.beadsproject.beads.core.UGen
import net.beadsproject.beads.data.Sample
import net.beadsproject.beads.data.SampleManager
import net.beadsproject.beads.ugens.Gain
import net.beadsproject.beads.ugens.SamplePlayer

class MusicVisualizer:GraphicsView {
    override val label: String = "Music Visualizer"
    override var root: Pane = Pane()

    override fun onOpen() {
        setup()
    }

    override fun onClose() {

    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    lateinit var player : SamplePlayer
    lateinit var sample : Sample
    var context = AudioContext()

    lateinit var analyzer: Analyzer
    var fft: FFT = FFT()



    private fun setup(){
        sample = Sample("/Users/kolbe/IdeaProjects/GraphicsPlayground/src/Views/Copy of Liberty City House (Updatedd).wav")
        player = SamplePlayer(context,sample)
        val g = Gain(context, 2, 0.2f)
        g.addInput(player)
        context.out.addInput(g)
        context.out.
        context.start()

        context.invokeAfterEveryFrame(updateBead())
    }

    inner class updateBead:FFT(){
        private var frameCount = 0;

        init {
            addListener(powerSpecReader())
        }

        override fun messageReceived(p0: Bead?) {
            super.messageReceived(p0)
            val parent = context
            frameCount += 1
            val timeStampA = parent.generateTimeStamp(frameCount)
            val timeStampB = parent.generateTimeStamp(frameCount+1)
            val array = FloatArray(2,{it.toFloat()})
            sample.getFrame(frameCount,array)
            process(timeStampA,timeStampB,array)
        }

        override fun process(p0: TimeStamp?, p1: TimeStamp?, p2: FloatArray?) {
            super.process(p0, p1, p2)
            println("wtf")
        }
    }

    inner class powerSpecReader:PowerSpectrum(){
        override fun messageReceived(p0: Bead?) {
            super.messageReceived(p0)
            println("Power bttms rule")
        }

    }



    private fun setupFileControls(){

    }

    private fun setupTimeline(){

    }

    private fun setupAnalysis(){
//        println(sample.numFrames)
//        val array = FloatArray(100, { it.toFloat()})
//        val frame = sample.getFrame(4,array)
//        for (num in array)
//            println(num)
    }


}