package Core.Wrappers.Sensors.Encoders

import Core.Robot.RobotOb.hardwareMap
import Core.Robot.RobotVars
import Core.Utils.CustomTimer
import Core.Utils.Vec2d
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorEx

/** Quadrature Encoder. Quadrature encoders are based on goBilda motors or REV Through-Bore encoders, thus DcMotorEx.
 * On power cycles, the position resets itself to 0, thus, only on first runs, the encoder must be reset.
 * Position returns 0.0 or offset if disabled.*/
class QuadEncoder(name: String, val rev: Boolean, val enabled: Boolean): Encoder<DcMotorEx> {
    override val enc: DcMotorEx? = if(enabled) hardwareMap.get(DcMotorEx::class.java, name) else null
    override val clk: CustomTimer = CustomTimer()
    override var offset: Double = 0.0

    override var rawRead: Double = 0.0
    override val posBuffer: Vec2d = Vec2d()
    override val velBuffer: Vec2d = Vec2d()

    override fun reset() {
        enc!!.mode = DcMotor.RunMode.STOP_AND_RESET_ENCODER
        offset = -rawRead
    }

    override fun init() {
        clk.reset()
        if (enabled) {
            if (RobotVars.isFirstRun) {
                    enc!!.mode = DcMotor.RunMode.STOP_AND_RESET_ENCODER
                    offset = 0.0
            }
            enc!!.mode = DcMotor.RunMode.RUN_USING_ENCODER
        }
    }

    override fun setPos() {
        rawRead = if(enabled) enc!!.currentPosition.toDouble() * if(rev) -1 else 1 else 0.0

        posBuffer.y = rawRead + offset
    }

    override fun update() {
        super.update()
    }
}