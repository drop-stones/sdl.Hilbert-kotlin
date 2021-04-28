package jp.ac.titech.itpro.sdl.hilbert_kotlin

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

//class HilbertView(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0): View(context, attrs, defStyleAttr) {
class HilbertView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val paint: Paint = Paint()
    private lateinit var canvas: Canvas

    private var order: Int = 1

    private val turtle: HilbertTurtle = HilbertTurtle(
        object: Turtle.Drawer {
            override fun drawLine(x0: Double, y0: Double, x1: Double, y1: Double): Unit {
                canvas.drawLine(x0.toFloat(), y0.toFloat(), x1.toFloat(), y1.toFloat(), paint)
            }
        }
    )

    override fun onDraw(canvas: Canvas): Unit {
        super.onDraw(canvas)
        this.canvas = canvas

        //val w: Float = width.toFloat()
        //val h: Float = height.toFloat()
        val w: Float = canvas.getWidth().toFloat()
        val h: Float = canvas.getHeight().toFloat()
        paint.color = Color.DKGRAY
        canvas.drawRect(0F, 0F, w, h, paint)

        paint.color = Color.WHITE
        paint.strokeWidth = 3F
        val size: Float = Math.min(w, h)
        val step: Double = size.toDouble() / (1 shl order)
        turtle.setPos((w - size + step) / 2, (h + size - step) / 2)
        turtle.setDir(Turtle.eastDir)
        turtle.draw(order, step, Turtle.rightDir)
    }

    fun setOrder(n: Int): Unit {
        order = n
        invalidate()
    }
}