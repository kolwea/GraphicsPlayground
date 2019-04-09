package Resources


class ParticleSystem{
    var particles : Array<Particle> = Array(defualtParticleCount,{Particle(it)})

    constructor(particleCount : Int){
        particles = Array(particleCount,{Particle(it)})
    }

    init {
    }


    inner class Particle {
        var index:Int = 0
        var x = 0.0
        var y = 0.0

        operator fun component1() = x
        operator fun component2() = y

        constructor(index : Int){
            this.index = index
        }

        fun goto(){

        }
    }
}