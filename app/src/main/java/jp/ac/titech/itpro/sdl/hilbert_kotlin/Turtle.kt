package jp.ac.titech.itpro.sdl.hilbert_kotlin

open class Turtle(private var drawer: Drawer) {
    public interface Drawer {
        fun drawLine(x0: Double, y0: Double, x1: Double, y1: Double): Unit
    }

    companion object {
        val rightDir: Double = Math.PI / 2
        val leftDir: Double = -rightDir
        val northDir: Double = 0.0
        val eastDir: Double = Math.PI / 2
        val southDir: Double = Math.PI
        val westDir: Double = eastDir + southDir
    }


    private var x: Double = 0.0
    private var y: Double = 0.0
    private var dir: Double = 0.0

    fun setPos(x: Double, y: Double): Unit {
        this.x = x
        this.y = y
    }

    fun setDir(dir: Double): Unit {
        this.dir = dir
    }

    fun forward(step: Double): Unit {
        val x1 = x + Math.sin(dir) * step
        val y1 = y - Math.cos(dir) * step
        drawer.drawLine(x, y, x1, y1)
        x = x1
        y = y1
    }

    fun rotate(th: Double): Unit {
        dir = (dir + th + Math.PI * 2) % (Math.PI * 2)
    }
}