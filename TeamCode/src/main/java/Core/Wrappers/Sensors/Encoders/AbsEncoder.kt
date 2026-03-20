package Core.Wrappers.Sensors.Encoders

import Core.Robot.RobotOb.hardwareMap
import Core.Utils.CustomTimer
import Core.Utils.Vec2d
import com.qualcomm.robotcore.hardware.AnalogInput
import kotlin.math.max
import kotlin.math.min

/** Absolute Encoder. Absolute encoders are based on an analog input, ranging from 0 from 3.3 volts.
 * Thus, their position is independent of power cycles, but is dependent on the circuit resistances, where additional voltage drops may appear, hence the rmv (real max voltage) is needed.
 * Position returns 0.0 or offset if disabled.*/
class AbsEncoder(name: String, val rev: Boolean, off: Double, var rmv: Double, val enabled: Boolean): Encoder<AnalogInput> {
    override val enc: AnalogInput? = if(enabled) hardwareMap.get(AnalogInput::class.java, name) else null
    override val clk: CustomTimer = CustomTimer()
    override val offset: Double = off

    override var rawRead: Double = 0.0
    override val posBuffer: Vec2d = Vec2d()
    override val velBuffer: Vec2d = Vec2d()

    override fun reset() {
    }

    override fun init() {
        clk.reset()
    }

    override fun setPos() {
        rawRead = if(enabled) enc!!.voltage else 0.0

        rmv = min(max(rawRead, rmv), 3.3)
        posBuffer.y = (rawRead * if(rev) -1 else 1) / rmv + offset
    }

    override fun update() {
        super.update()
    }

}