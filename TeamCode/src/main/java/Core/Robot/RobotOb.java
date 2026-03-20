package Core.Robot;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.VoltageSensor;

public class RobotOb {
    public static LinearOpMode lom;
    public static HardwareMap hardwareMap;

    public static DAQ dash;
    public static VoltageSensor batteryVoltageSensor;
    public static LynxModule controlHub;
    public static LynxModule expansionHub;

    public static Controller controller;
    public static FiniteStateMachine fsm;

}
