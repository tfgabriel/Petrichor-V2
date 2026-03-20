package org.firstinspires.ftc.teamcode.Core.Wrappers.Sensors.Encoders

import org.firstinspires.ftc.teamcode.Core.Utils.CustomTimer
import org.firstinspires.ftc.teamcode.Core.Utils.Vec2d

/** Encoder interface. The used position that should be used out of this encoder is the processed one, as the raw one is used more for debugging.*/
interface Encoder<T> {
    /** Variable encoder source, can be anything, considering its type (relative/absolute).*/
    val enc: T?
    /** Clock to calculate velocity.*/
    val clk: CustomTimer
    /** Constant position offset. Used for resetting positions or starting off with already existing offsets.*/
    val offset: Double

    /** Raw encoder reading, unfiltered and unprocessed.*/
    val rawRead: Double
    /** Processed position buffer (has normalizations/regulations made and offsets added): first element is the last position, second the current one. */
    val posBuffer: Vec2d
    /** Processed velocity buffer: first element is the last speed, second the current one. */
    val velBuffer: Vec2d

    /** Reset function. Initialises the encoder on (processed) position 0.*/
    fun reset()
    /** Somewhat self explanatory.*/
    fun init()
    /** Processes raw read into useful positions. Can be altered to anything after for different uses.*/
    fun setPos()
    /** Updates positions, speeds and resets clock. */
    fun update(){
        setPos()
        velBuffer.y = (posBuffer.y - posBuffer.x) / clk.seconds
        posBuffer.x = posBuffer.y
        velBuffer.x = velBuffer.y
        clk.reset()
    }
}