package Core.Utils

import android.annotation.SuppressLint

/** Custom Atomic Timer.**/

@SuppressLint("DefaultLocale")
class CustomTimer {
    private var initTime = System.nanoTime()
    var seconds = (System.nanoTime() - initTime) / 1000000000.0

    fun reset() {
        initTime = System.nanoTime()
    }

    fun blowUp() {
        initTime = 0L
    }

    override fun toString() = String.format("%.2f", seconds)
}
