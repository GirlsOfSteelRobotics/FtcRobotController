package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;


@TeleOp(name="TestTeleOp", group="Linear Opmode")
public class TeleOpDrive extends OpMode {

    private DcMotor TopLeft = null;
    private DcMotor TopRight = null;
    private DcMotor BottomLeft = null;
    private DcMotor BottomRight = null;


    @Override
    public void init() {
        telemetry.addLine("Startinit");
        telemetry.update();

        TopLeft  = hardwareMap.dcMotor.get("TopLeft");
//        TopLeft.setDirection(DcMotor.Direction.FORWARD);

        TopRight  = hardwareMap.dcMotor.get("TopRight");
//        TopRight.setDirection(DcMotor.Direction.FORWARD);

        BottomLeft  = hardwareMap.dcMotor.get("BottomLeft");
//        BottomLeft.setDirection(DcMotor.Direction.FORWARD);

        BottomRight  = hardwareMap.dcMotor.get("BottomRight");
//        BottomRight.setDirection(DcMotor.Direction.FORWARD);
    }

    @Override
    public void loop() {
        telemetry.addLine("Loop");
        telemetry.update();

        double drivevalue = -gamepad1.left_stick_y;
        double leftPower    = Range.clip(drivevalue, -1.0, 1.0) ;

//        if (gamepad1.left_stick_y>0.5){
            TopLeft.setPower(leftPower);
            TopRight.setPower(leftPower);
            BottomLeft.setPower(leftPower);
            BottomRight.setPower(leftPower);
//        }
//        else if (gamepad1.left_stick_y<-0.5){
//            TopLeft.setPower(-0.5);
//            TopRight.setPower(-0.5);
//            BottomLeft.setPower(-0.5);
//            BottomRight.setPower(-0.5);
//        }
//        else {
//            TopLeft.setPower(0.0);
//            TopRight.setPower(0.0);
//            BottomLeft.setPower(0.0);
//            BottomRight.setPower(0.0);
//        }

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
