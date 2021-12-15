package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime; 

@Autonomous (name= "Basic AutoOp", group= "Lovelace")
public class BasicAutoOp extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftDrive;
    private DcMotor rightDrive;
    private DcMotor linearSlideDrive;

    static final double FORWARD_SPEED = 0.5;

    static final double     COUNTS_PER_MOTOR_REV    = 288 ;
    static final double     DRIVE_GEAR_REDUCTION    = 54.0/15.0 ;
    static final double     WHEEL_DIAMETER_INCHES   = 2.0 ;
    static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);
    static final double     DRIVE_SPEED             = 0.6;
    static final double     TURN_SPEED              = 0.5;

    @Override
    public void runOpMode() {

        leftDrive = hardwareMap.get(DcMotor.class, "LeftDrive");
        rightDrive = hardwareMap.get(DcMotor.class, "RightDrive");
        linearSlideDrive = hardwareMap.get(DcMotor.class, "linearSlideDrive");


        leftDrive.setDirection(DcMotorSimple.Direction.REVERSE);

        telemetry.addData("Status:","Ready to run");
        telemetry.update();

        waitForStart();

        // Step 5:  Drive forward for 5 seconds
        leftDrive.setPower(FORWARD_SPEED);
        rightDrive.setPower(FORWARD_SPEED);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 8.0)) {
            telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }


        telemetry.addData("Path", "Complete");
        telemetry.update();
        sleep(1000);


    }
}
