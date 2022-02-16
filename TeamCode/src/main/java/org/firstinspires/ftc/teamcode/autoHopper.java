package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

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

    HardwareMap hwMap = null;
    HardwarePushbot robot = new HardwarePushbot();   // Use a Pushbot's hardware
    private ElapsedTime runtime = new ElapsedTime();

    static final double FORWARD_SPEED = 0.6;
    static final double TURN_SPEED = 0.5;

    public void init(HardwareMap ahwMap) {
        hwMap = ahwMap;
        // Define and Initialize Motors
        TopLeft = hwMap.get(DcMotor.class, "Top_Left");
        TopRight = hwMap.get(DcMotor.class, "Top_Right");
        BottomLeft = hwMap.get(DcMotor.class, "Bottom_Left");
        BottomRight = hwMap.get(DcMotor.class, "Bottom_Right");
        ClawMotor = hwMap.get(DcMotor.class, "Claw_Motor");
        //leftDrive.setDirection(DcMotor.Direction.FORWARD); // Set to REVERSE if using AndyMark motors
        //rightDrive.setDirection(DcMotor.Direction.REVERSE);// Set to FORWARD if using AndyMark motors
    }

    @Override
    public void runOpMode() {
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

        TopLeft.setPower(0.5);
        TopRight.setPower(0.5);
        BottomLeft.setPower(0.5);
        BottomRight.setPower(0.5);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 3.0)) {
            telemetry.addData("Path", runtime.seconds());
            telemetry.update();


        }

    }
}