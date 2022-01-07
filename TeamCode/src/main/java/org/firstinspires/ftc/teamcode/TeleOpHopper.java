package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.Range;


@TeleOp(name="TeleOp Hopper", group="Linear Opmode")
public class TeleOpHopper extends OpMode {

    private DcMotor TopLeft = null;
    private DcMotor TopRight = null;
    private DcMotor BottomLeft = null;
    private DcMotor BottomRight = null;
    private DcMotor ClawMotor = null;


    @Override
    public void init() {
        telemetry.addLine("Startinit");
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

        if (gamepad1.y){
            ClawMotor.setPower(0.3);
        }
        else if (gamepad1.a){
            ClawMotor.setPower(-0.3);
        }
        else {
            ClawMotor.setPower(0.0);
        }
//        double topLeftPower   = Range.clip(drive+turn,-1.0,1.0);
//        double topRightPower   = Range.clip(drive-turn,-1.0,1.0);
//        double bottomLeftPower   = Range.clip(-drive-turn,-1.0,1.0);
//        double bottomRightPower   = Range.clip(-drive+turn,-1.0,1.0);

//        TopLeft.setPower(topLeftPower);
//        TopRight.setPower(topRightPower);
//        BottomLeft.setPower(bottomLeftPower);
//        BottomRight.setPower(bottomRightPower);


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
