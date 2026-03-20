package Core.Utils

import Core.Utils.Maths.angNorm
import Core.Utils.Maths.epsEq
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

@Suppress("MemberVisibilityCanBePrivate")
class Pose(@JvmField var x: Double, @JvmField var y: Double, @JvmField var h: Double) {
    constructor(v: Vec2d, h: Double) : this(v.x, v.y, h)
    constructor(pose: Pose2d): this(pose.x, pose.y, pose.heading)
    constructor() : this(0.0, 0.0, 0.0)

    fun dist2(): Double = x * x + y * y
    fun dist(): Double = sqrt(dist2())
    fun v2d(): Vec2d = Vec2d(x, y)
    fun rotated(a: Double): Pose {
        val ar = vec().rotated(a)
        return Pose(ar.x, ar.y, h + a)
    }

    fun duplicate(): Pose = Pose(x, y, h)

    fun vec() = Vec2d(x, y)
    fun headingVec() = Vec2d(cos(h), sin(h))

    operator fun get(id: Int) = when (id) {
        0 -> x
        1 -> y
        else -> h
    }

    operator fun unaryMinus() = Pose(-x, -y, -h)

    operator fun plus(pose: Pose) = Pose(x + pose.x, y + pose.y, h + pose.h)

    operator fun minus(pose: Pose) = Pose(x - pose.x, y - pose.y, h - pose.h)

    operator fun div(pose: Pose) = Pose(x / pose.x, y / pose.y, h / pose.h)

    operator fun times(pose: Pose) = Pose(x * pose.x, y * pose.y, h * pose.h)
    operator fun times(s: Double) = Pose(x * s, y * s, h * s)
    operator fun div(s: Double) = Pose(x / s, y / s, h / s)
    operator fun times(s: Int) = times(s.toDouble())
    operator fun divAssign(s: Double) { x /= s; y /= s; h /= s }
    operator fun divAssign(s: Int) = divAssign(s.toDouble())

    fun negX() = Pose(-x, y, h)
    override fun toString() = String.format("(%.3f, %.3f, %.3f)", x, y, h)
}

class Vec2d(@JvmField var x: Double, @JvmField var y: Double) {
    constructor() : this(0.0, 0.0)

    fun dist2(): Double = x * x + y * y
    fun dist(): Double = sqrt(dist2())
    fun pose(): Pose = Pose(x, y, 0.0)

    fun rotated(angle: Double): Vec2d {
        val newX = x * cos(angle) - y * sin(angle)
        val newY = x * sin(angle) + y * cos(angle)
        return Vec2d(newX, newY)
    }

    fun norm() = this / dist()

    fun duplicate() = Vec2d(x, y)

    fun negY() = Vec2d(x, -y)

    operator fun unaryMinus() = Vec2d(-x, -y)

    operator fun plus(vec: Vec2d) = Vec2d(x + vec.x, y + vec.y)

    operator fun minus(vec: Vec2d) = Vec2d(x - vec.x, y - vec.y)

    operator fun div(vec: Vec2d) = Vec2d(x / vec.x, y / vec.y)
    operator fun div(s: Double) = Vec2d(x / s, y / s)
    operator fun divAssign(s: Int) { x /= s.toDouble(); y /= s.toDouble() }

    operator fun times(vec: Vec2d) = Vec2d(x * vec.x, y * vec.y)
    operator fun times(s: Double) = Vec2d(x * s, y * s)

    fun polar() = Vec2d(x * cos(y), x * sin(y))
    operator fun get(i: Int){
        when(i){
            0 -> x
            else -> y
        }
    }
    operator fun set(i: Int, value: Double){
        when(i){
            0 -> x = value
            else -> y = value
        }
    }

    override fun toString() = String.format("(%.3f, %.3f)", x, y)
}

class Vec2(@JvmField var a: Double, @JvmField var b: Double) { constructor(): this(0.0, 0.0) operator fun get(i: Int) = when (i) { 0 -> a; else -> b } }
class Vec3(@JvmField var a: Double, @JvmField var b: Double, @JvmField var c: Double) { constructor(): this(0.0, 0.0, 0.0) operator fun get(i: Int) = when (i) { 0 -> a; 1 -> b; else -> c } }
class Vec4(@JvmField var a: Double, @JvmField var b: Double, @JvmField var c: Double, @JvmField var d: Double) { operator fun get(i: Int) = when (i) { 0 -> a; 1 -> b; 2 -> c; else -> d } }
class Vec5(@JvmField var a: Double, @JvmField var b: Double, @JvmField var c: Double, @JvmField var d: Double, @JvmField var e: Double) { operator fun get(i: Int) = when (i) { 0 -> a; 1 -> b; 2 -> c; 3 -> d; else -> e } }
class Vec6(@JvmField var a: Double, @JvmField var b: Double, @JvmField var c: Double, @JvmField var d: Double, @JvmField var e: Double, @JvmField var f: Double) { operator fun get(i: Int) = when (i) { 0 -> a; 1 -> b; 2 -> c; 3 -> d; 4 -> e else -> f} }
class Vec7(@JvmField var a: Double, @JvmField var b: Double, @JvmField var c: Double, @JvmField var d: Double, @JvmField var e: Double, @JvmField var f: Double, @JvmField var g: Double) { operator fun get(i: Int) = when (i) { 0 -> a; 1 -> b; 2 -> c; 3 -> d; 4 -> e; 5 -> f; else -> g} }
class Vec8(@JvmField var a: Double, @JvmField var b: Double, @JvmField var c: Double, @JvmField var d: Double, @JvmField var e: Double, @JvmField var f: Double, @JvmField var g: Double,@JvmField var h: Double) { operator fun get(i: Int) = when (i) { 0 -> a; 1 -> b; 2 -> c; 3 -> d; 4 -> e; 5 -> f; 6 -> g else -> h} }
class Vec9(@JvmField var a: Double, @JvmField var b: Double, @JvmField var c: Double, @JvmField var d: Double, @JvmField var e: Double, @JvmField var f: Double, @JvmField var g: Double,@JvmField var h: Double, @JvmField var ii:Double) { operator fun get(i: Int) = when (i) { 0 -> a; 1 -> b; 2 -> c; 3 -> d; 4 -> e; 5 -> f; 6 -> g; 7 -> h else -> ii} }

class Vec2T(@JvmField var a: TrajCoef, @JvmField var b: TrajCoef) { operator fun get(i: Int) = when (i) { 0 -> a; else -> b } }
class Vec3T(@JvmField var a: TrajCoef, @JvmField var b: TrajCoef, @JvmField var c: TrajCoef) { operator fun get(i: Int) = when (i) { 0 -> a; 1 -> b; else -> c } }
class Vec4T(@JvmField var a: TrajCoef, @JvmField var b: TrajCoef, @JvmField var c: TrajCoef, @JvmField var d: TrajCoef) { operator fun get(i: Int) = when (i) { 0 -> a; 1 -> b; 2 -> c; else -> d } }

/// All these exist for is for them to be able to be visible in the dashboard :(

class Vec2i(@JvmField var a: Int, @JvmField var b: Int) { operator fun get(i: Int) = when (i) { 0 -> a; else -> b } }
class Vec3i(@JvmField var a: Int, @JvmField var b: Int, @JvmField var c: Int) { operator fun get(i: Int) = when (i) { 0 -> a; 1 -> b; else -> c; } }
class Vec4i(@JvmField var a: Int, @JvmField var b: Int, @JvmField var c: Int, @JvmField var d: Int) { operator fun get(i: Int) = when (i) { 0 -> a; 1 -> b; 2 -> c; else -> d } }
class Vec6i(@JvmField var a: Int, @JvmField var b: Int, @JvmField var c: Int, @JvmField var d: Int, @JvmField var e: Int, @JvmField var f: Int) { operator fun get(i: Int) = when (i) { 0 -> a; 1 -> b; 2 -> c; 3 -> d; 4 -> e else -> f} }
class Vec7i(@JvmField var a: Int, @JvmField var b: Int, @JvmField var c: Int, @JvmField var d: Int, @JvmField var e: Int, @JvmField var f: Int, @JvmField var g: Int) { operator fun get(i: Int) = when (i) { 0 -> a; 1 -> b; 2 -> c; 3 -> d; 4 -> e 5 -> f else -> g} }
class Vec8i(@JvmField var a: Int, @JvmField var b: Int, @JvmField var c: Int, @JvmField var d: Int, @JvmField var e: Int, @JvmField var f: Int, @JvmField var g: Int, @JvmField var h: Int) { operator fun get(i: Int) = when (i) { 0 -> a; 1 -> b; 2 -> c; 3 -> d; 4 -> e 5 -> f 6 -> g else -> h} }
class Vec9i(@JvmField var a: Int, @JvmField var b: Int, @JvmField var c: Int, @JvmField var d: Int, @JvmField var e: Int, @JvmField var f: Int, @JvmField var g: Int, @JvmField var h: Int, @JvmField var i: Int) { operator fun get(i: Int) = when (i) { 0 -> a; 1 -> b; 2 -> c; 3 -> d; 4 -> e 5 -> f 6 -> g 7 -> h else -> i} }

class TunableDouble(@JvmField var v: Double) {
    fun duplicate() = TunableDouble(v)
    operator fun unaryMinus() = -v
    operator fun plus(p: Double) = v + p
    operator fun minus(p: Double) = v - p
    operator fun div(s: Double) = v / s
    operator fun times(s: Double) = v * s
}/// I FUCKING HATE THIS

/** Imported from RoadRunner for TwoWheelOdo **/
data class Pose2d(val x: Double, val y: Double, val heading: Double) {
    constructor(pos: Vector2d, heading: Double) : this(pos.x, pos.y, heading)
    constructor() : this(0.0, 0.0, 0.0)
    constructor(pos: Pose) : this(pos.x, pos.y, pos.h)
    fun vec() = Vector2d(x, y)

    fun headingVec() = Vector2d(cos(heading), sin(heading))

    operator fun plus(other: Pose2d) =
        Pose2d(x + other.x, y + other.y, heading + other.heading)

    operator fun minus(other: Pose2d) =
        Pose2d(x - other.x, y - other.y, heading - other.heading)

    operator fun times(scalar: Double) =
        Pose2d(scalar * x, scalar * y, scalar * heading)

    operator fun div(scalar: Double) =
        Pose2d(x / scalar, y / scalar, heading / scalar)

    operator fun unaryMinus() = Pose2d(-x, -y, -heading)

    infix fun epsilonEquals(other: Pose2d) =
        epsEq(x, other.x) && epsEq(y, other.y) && epsEq(heading, other.heading)

    infix fun epsilonEqualsHeading(other: Pose2d) =
        epsEq(x, other.x) && epsEq(y, other.y) && epsEq(angNorm(heading - other.heading), 0.0)

    override fun toString() = String.format("(%.3f, %.3f, %.3f°)", x, y, Math.toDegrees(heading))

    operator fun Double.times(pose: Pose2d) = pose.times(this)

    operator fun Double.div(pose: Pose2d) = pose.div(this)
}

data class Vector2d(@JvmField val x: Double, @JvmField val y: Double) {
    operator fun plus(v: Vector2d) = Vector2d(x + v.x, y + v.y)
    operator fun minus(v: Vector2d) = Vector2d(x - v.x, y - v.y)
    operator fun unaryMinus() = Vector2d(-x, -y)

    operator fun times(z: Double) = Vector2d(x * z, y * z)
    operator fun div(z: Double) = Vector2d(x / z, y / z)

    infix fun dot(v: Vector2d) = x * v.x + y * v.y
    fun sqrNorm() = this dot this

    fun rotate(ang: Double) = Vector2d(x*cos(ang)-y*sin(ang), x*sin(ang)+y*cos(ang))
    fun norm() = sqrt(sqrNorm())
}
