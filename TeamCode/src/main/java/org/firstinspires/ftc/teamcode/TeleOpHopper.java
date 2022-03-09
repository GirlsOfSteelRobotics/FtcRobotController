package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;


@TeleOp(name="TeleOp Hopper", group="Linear Opmode")
public class TeleOpHopper extends OpMode {

    private DcMotor TopLeft = null;
    private DcMotor TopRight = null;
    private DcMotor BottomLeft = null;
    private DcMotor BottomRight = null;
    private DcMotor ClawMotor = null;
    private DcMotor ClawGrabber = null;
    private Servo Duck = null;

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

        ClawMotor = hardwareMap.dcMotor.get("ClawMotor");
        ClawMotor.setDirection(DcMotorSimple.Direction.FORWARD);
//        ClawMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        ClawMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        ClawGrabber = hardwareMap.dcMotor.get("ClawGrabber");
        ClawGrabber.setDirection(DcMotorSimple.Direction.FORWARD);
//        ClawGrabber.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        ClawGrabber.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        Duck = hardwareMap.servo.get("Duck");


    }

    @Override
    public void loop() {
        telemetry.addLine("Loop");
        telemetry.update();

     double drive = -gamepad1.left_stick_y;
     double turn  =  gamepad1.right_stick_x;
     double leftPower    = Range.clip(drive + turn, -1.0, 1.0) ;
     double rightPower   = Range.clip(drive - turn, -1.0, 1.0) ;

       TopLeft.setPower(leftPower);
       TopRight.setPower(rightPower);
       BottomLeft.setPower(leftPower);
       BottomRight.setPower(rightPower);

//       if (!ClawMotor.isBusy()) {
//           ClawMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//       }
//       if (!ClawGrabber.isBusy()) {
//           ClawGrabber.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//       }

        if (gamepad1.y){
//            ClawMotor.setTargetPosition(1);
//            ClawMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//            ClawMotor.setPower(0.5);
        }
        else if (gamepad1.a){
//            ClawMotor.setTargetPosition(0);
//            ClawMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//            ClawMotor.setPower(0.5);
        }
       else if (gamepad1.x) {
//            ClawGrabber.setTargetPosition(20);
//            ClawGrabber.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }
        else if (gamepad1.b) {
//            ClawGrabber.setTargetPosition(-20);
//            ClawGrabber.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }
        else if (gamepad1.left_bumper) {
            Duck.setPosition(1);
        }
        else {
            ClawMotor.setPower(0.0);
            ClawGrabber.setPower(0.0);
        }

    }


    @Override
    public void start() {
        telemetry.addLine("Start");
        telemetry.update(
        );
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
