package Core.Robot


object RobotFuncs {
    fun initRobot(){
        if(RobotVars.isFirstRun)
            RobotVars.isFirstRun = false
    }
}