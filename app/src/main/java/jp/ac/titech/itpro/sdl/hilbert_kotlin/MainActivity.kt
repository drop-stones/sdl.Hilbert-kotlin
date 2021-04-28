package jp.ac.titech.itpro.sdl.hilbert_kotlin

import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private val KEY_NAME: String = "order.name"

    private val MAX_ORDER: Int = 9
    private var order: Int = 1

    private lateinit var orderView: TextView
    private lateinit var hilbertView: HilbertView
    private lateinit var decButton: Button
    private lateinit var incButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState != null) {
            order = savedInstanceState.getInt(KEY_NAME)
        }

        orderView = findViewById(R.id.order_view)
        hilbertView = findViewById(R.id.hilbert_view)
        decButton = findViewById(R.id.dec_button)
        incButton = findViewById(R.id.inc_button)

        decButton.setOnClickListener {
            assertTrue(order > 1, "A room to decrement order should exist")
            order--
            display()
        }

        incButton.setOnClickListener {
            assertTrue(order < MAX_ORDER, "A room to increment order should exist")
            order++
            display()
        }
    }

    override fun onResume(): Unit {
        super.onResume()
        display()
    }

    private fun display(): Unit {
        orderView.text = getString(R.string.order_view_format, order)
        hilbertView.setOrder(order)
        decButton.isEnabled = order > 1
        incButton.isEnabled = order < MAX_ORDER
    }

    fun assertTrue(f: Boolean, message: String): Unit {
        //if (BuildConfig.DEBUG && !f) {
        if (!f) {
            throw AssertionError(message)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_NAME, order)
    }
}