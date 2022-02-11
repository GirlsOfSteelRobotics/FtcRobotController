package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcontroller.external.samples.HardwarePushbot;

@Autonomous(name="Pushbot: Auto Drive By Time", group="Pushbot")
@Disabled
public class autoHopper extends LinearOpMode {

    /* Declare OpMode members. */
    private DcMotor TopLeft = null;
    private DcMotor TopRight = null;
    private DcMotor BottomLeft = null;
    private DcMotor BottomRight = null;
    private DcMotor ClawMotor = null;
    private Servo ClawServo = null;

    HardwareMap hwMap =  null;
    HardwarePushbot robot = new HardwarePushbot();   // Use a Pushbot's hardware
    private ElapsedTime runtime = new ElapsedTime();


    static final double FORWARD_SPEED = 0.6;
    static final double TURN_SPEED = 0.5;

    public void init(HardwareMap ahwMap){
        hwMap = ahwMap;
        // Define and Initialize Motors
        TopLeft  = hwMap.get(DcMotor.class, "Top_Left");
        TopRight = hwMap.get(DcMotor.class, "Top_Right");
        BottomLeft = hwMap.get(DcMotor.class, "Bottom_Left");
        BottomRight = hwMap.get(DcMotor.class, "Bottom_Right");
        ClawMotor = hwMap.get(DcMotor.class, "Claw_Motor");

    }


    @Override
    public void runOpMode () {

        /*
         * Initialize the drive system variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Ready to run");    //
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        // motion starts in this one or in a new public void? -gray
    }

}