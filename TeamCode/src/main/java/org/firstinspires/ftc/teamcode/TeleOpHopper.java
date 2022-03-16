package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;


@TeleOp(name="TeleOp Hopper", group="Linear Opmode")
public class TeleOpHopper extends OpMode {

    private DcMotor TopLeft = null;
    private DcMotor TopRight = null;
    private DcMotor BottomLeft = null;
    private DcMotor BottomRight = null;
    private DcMotorEx ClawMotor = null;
    private DcMotorEx ClawGrabber = null;
    private Servo Duck = null;

    // Stores the arm's current position
    private int armPosition;

    // Ideal encoder tick position for arm to be in full up or down position
    private final int ARM_UP_POSITION = -80;
    private final int ARM_DOWN_POSITION = -25;

    // Ideal speed (in encoder ticks/second) to try to get to up or down positions
    private final int ARM_UP_VELOCITY = 180;
    private final int ARM_DOWN_VELOCITY = 50;

    @Override
    public void init() {
        telemetry.addLine("Start init");
        telemetry.update();
        TopLeft  = hardwareMap.dcMotor.get("TopLeft");
        TopLeft.setDirection(DcMotor.Direction.FORWARD);

        TopRight  = hardwareMap.dcMotor.get("TopRight");
        TopRight.setDirection(DcMotor.Direction.REVERSE);

        BottomLeft  = hardwareMap.dcMotor.get("BottomLeft");
        BottomLeft.setDirection(DcMotor.Direction.FORWARD);

        BottomRight  = hardwareMap.dcMotor.get("BottomRight");
        BottomRight.setDirection(DcMotor.Direction.REVERSE);

        ClawMotor = hardwareMap.get(DcMotorEx.class, "ClawMotor");
//        ClawMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        ClawMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        armPosition = 0;

        ClawGrabber = hardwareMap.get(DcMotorEx.class, "ClawGrabber");
//        ClawGrabber.setDirection(DcMotorSimple.Direction.FORWARD);
        ClawGrabber.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        Duck = hardwareMap.servo.get("Duck");
        telemetry.addLine("Motor encoders reset to 0.");

    }

    @Override
    public void loop() {

        double drive = -gamepad1.left_stick_y;
        double turn  =  gamepad1.right_stick_x;
        double leftPower    = Range.clip(drive + turn, -1.0, 1.0) ;
        double rightPower   = Range.clip(drive - turn, -1.0, 1.0) ;

       TopLeft.setPower(leftPower);
       TopRight.setPower(rightPower);
       BottomLeft.setPower(leftPower);
       BottomRight.setPower(rightPower);

        if (gamepad1.a){
            // Arm goes down
            telemetry.addLine("A press: Go down");
//            ClawMotor.setPower(1);
            if (armPosition != ARM_DOWN_POSITION) {
                armPosition = ARM_DOWN_POSITION;
                ClawMotor.setTargetPosition(armPosition);
                ClawMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                ClawMotor.setVelocity(ARM_DOWN_VELOCITY);
                telemetry.addLine("   Arm set to position " + armPosition);
            }
        }
        else if (gamepad1.y){
            // Arm goes up
//            ClawMotor.setPower(-0.9);
            telemetry.addLine("Y press: Go up");
//            ClawMotor.setPower(1);
            if (armPosition != ARM_UP_POSITION) {
                armPosition = ARM_UP_POSITION;
                ClawMotor.setTargetPosition(armPosition);
                ClawMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                ClawMotor.setVelocity(ARM_UP_VELOCITY);
                telemetry.addLine("   Arm set to position " + armPosition);
            }
        }
       else if (gamepad1.x) {
            ClawGrabber.setPower(0.2);
            telemetry.addLine("X press: Claw grabber 0.2 power");
        }
        else if (gamepad1.b) {
            ClawGrabber.setPower(-0.2);
            telemetry.addLine("B press: Claw motor -0.2 power");
        }
        else if (gamepad1.left_bumper) {
            Duck.setPosition(1);
            telemetry.addLine("L Bumper press: Duck position 1");
        }
        else {
//            ClawMotor.setPower(0.0);
            ClawGrabber.setPower(0.0);
            telemetry.addLine("Claw motors stopped.");
            Duck.setPosition(0);
        }

        telemetry.update();
    }


    @Override
    public void start() {
        telemetry.addLine("Start");
        telemetry.update();

    }

    @Override
    public void stop() {
        telemetry.addLine("stop");
        telemetry.update();

        TopLeft.setPower(0.0);
        TopRight.setPower(0.0);
        BottomLeft.setPower(0.0);
        BottomRight.setPower(0.0);
    }
}
