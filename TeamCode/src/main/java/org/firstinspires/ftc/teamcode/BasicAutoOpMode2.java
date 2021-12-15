package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;


@Autonomous (name= "BasicAutoOpMode2", group= "Lovelace")
public class BasicAutoOpMode2 extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftDrive;
    private DcMotor rightDrive;
    private DcMotor linear_slide;

    static final double FORWARD_SPEED = -0.5;

    static final double     COUNTS_PER_MOTOR_REV    = 288 ;
    static final double     DRIVE_GEAR_REDUCTION    = 54.0/15.0 ;
    static final double     WHEEL_DIAMETER_INCHES   = 2.0 ;
    static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);
    static final double     LINEAR_SLIDE_SPEED      = -0.7;
    static final double     DRIVE_SPEED             = 0.6;
    static final double     TURN_SPEED              = 0.5;

    @Override
    public void runOpMode() {

        leftDrive = hardwareMap.get(DcMotor.class, "LeftDrive");
        rightDrive = hardwareMap.get(DcMotor.class, "RightDrive");
        linear_slide = hardwareMap.get(DcMotor.class, "linearSlide");

        leftDrive.setDirection(DcMotorSimple.Direction.REVERSE);

        telemetry.addData("Status:", "Ready to run");
        telemetry.update();

        waitForStart();

        encoderDrive(LINEAR_SLIDE_SPEED, -1, 4.0);
        //lifts linear slide x inches
        //wait(5000);
        runtime.reset();
        while (opModeIsActive() && runtime.seconds() < 1.0) {
            leftDrive.setPower(0.7);
            //turns left
        }
        leftDrive.setPower(0.0);

        // move back a tiny bit
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 0.2)) {
            telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();

            // Step 5:  Drive forward for 5 seconds
            leftDrive.setPower(FORWARD_SPEED);
            rightDrive.setPower(FORWARD_SPEED);
        }
        leftDrive.setPower(0.0);
        rightDrive.setPower(0.0);

        // turn back right
        runtime.reset();
        while (opModeIsActive() && runtime.seconds() < 1.0) {
            rightDrive.setPower(0.7);
        }
        rightDrive.setPower(0.0);

        //else { rightDrive.setPower(FORWARD_SPEED);}
        runtime.reset();
        //while (opModeIsActive() && (runtime.seconds() < 2.0)) {
            //telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
            //telemetry.update();
            //This should move the robot out of the landing depot

            //linear_slide.setPower(-FORWARD_SPEED);
            //forward goes up, backwards goes down
        //}
        linear_slide.setPower(0.0);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 2.0)) {
            telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();

            // Step 5:  Drive forward for 5 seconds
            leftDrive.setPower(FORWARD_SPEED);
            rightDrive.setPower(FORWARD_SPEED);
        }
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 5.0)) {
            telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }

        telemetry.addData("Path", "Complete");
        telemetry.update();
        sleep(1000);

    }
// Negative inches means going down, positive inches means going up (DON"T TOUCH FUNCTION)
    public void encoderDrive( double speed, double leftInches, double timeoutS) {
        int slideTarget;
        // Ensure that the opmode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            slideTarget = linear_slide.getCurrentPosition() + (int)(leftInches * COUNTS_PER_INCH);
            //newRightTarget = robot.rightDrive.getCurrentPosition() + (int)(rightInches * COUNTS_PER_INCH);
            linear_slide.setTargetPosition(slideTarget);
            //robot.rightDrive.setTargetPosition(newRightTarget);

            // Turn On RUN_TO_POSITION
            linear_slide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            //robot.rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset();
            linear_slide.setPower(Math.abs(speed));
            //robot.rightDrive.setPower(Math.abs(speed));

            // keep looping while we are still active, and there is time left, and both motors are running.
            // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
            // its target position, the motion will stop.  This is "safer" in the event that the robot will
            // always end the motion as soon as possible.
            // However, if you require that BOTH motors have finished their moves before the robot continues
            // onto the next step, use (isBusy() || isBusy()) in the loop test.
            while (opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (linear_slide.isBusy())) {

                // Display it for the driver.
                telemetry.addData("Path1",  "Running to %7d ", linear_slide.getCurrentPosition());
                telemetry.addData("Path2",  "Running at %7d ",
                        linear_slide.getCurrentPosition());
                telemetry.update();
            }

            // Stop all motion;
            linear_slide.setPower(0);
            //robot.rightDrive.setPower(0);

            // Turn off RUN_TO_POSITION
            linear_slide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            //robot.rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

              sleep(250);   // optional pause after each move
        }
    }
}