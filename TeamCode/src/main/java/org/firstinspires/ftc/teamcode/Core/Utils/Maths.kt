package org.firstinspires.ftc.teamcode.Core.Utils

import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.floor
import kotlin.math.max
import kotlin.math.min

/** List of widely used functions throughout the code.*/
object Maths {
    const val eps = 0.00004
    /** Returns true if the difference between the arguments is very small.*/
    @JvmStatic
    fun epsEq(o1: Double, o2: Double): Boolean {
        return abs(o1 - o2) < eps
    }

    /** Returns the mod between 2 floating point numbers.*/
    @JvmStatic
    fun floatMod(o1: Double, o2: Double): Double {
        return o1 - floor(o1 / o2) * o2
    }

    /** Returns the normalized value of an angle (reduced to it's corresponding value between 0 & 2PI). */
    @JvmStatic
    fun angNorm(o1: Double): Double {
        return floatMod(o1, 2 * PI)
    }

    /** Returns the "smallest" difference between 2 angles, which is represented by the shortest path on a circle between 2 points on it.*/
    @JvmStatic
    fun angDiff(o1: Double, o2: Double): Double {
        return floatMod(o2 - o1 + PI, PI * 2) - PI
    }

    /** Returns the down value if the value is smaller than it, the up value if the value is bigger than it, or the value itself if it is between the two. */
    @JvmStatic
    fun clamp(v: Double, down: Double, up: Double): Double {
        return min(max(v, down), up)
    }

    /** Returns the a-th value between low and high. a must between 0 and 1. */
    @JvmStatic
    fun lerp(a: Double, low: Double, high: Double): Double {
        return (1 - a) * low + a * high;
    }
}