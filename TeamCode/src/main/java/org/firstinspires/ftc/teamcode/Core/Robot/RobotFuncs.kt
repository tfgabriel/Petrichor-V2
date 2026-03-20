package org.firstinspires.ftc.teamcode.Core.Robot

object RobotFuncs {
    fun initRobot(){
        if(RobotVars.isFirstRun)
            RobotVars.isFirstRun = false
    }
}