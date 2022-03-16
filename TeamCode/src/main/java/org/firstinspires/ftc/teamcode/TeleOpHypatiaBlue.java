package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;


@TeleOp(name="TeleOp Hypatia Blue", group="Linear Opmode")
public class TeleOpHypatiaBlue extends OpMode {

    private DcMotor TopLeft = null;
    private DcMotor TopRight = null;
    private DcMotor BottomLeft = null;
    private DcMotor BottomRight = null;
    private DcMotor Elbow = null;
    private Servo Wrist = null;
    private Servo Claw = null;
    private Servo Carousel = null;
    // phone 9820


    @Override
    public void init() {
        telemetry.addLine("Startinit");
        telemetry.update();

        TopLeft  = hardwareMap.dcMotor.get("TopLeft");
        TopLeft.setDirection(DcMotor.Direction.FORWARD); //reverse

        TopRight  = hardwareMap.dcMotor.get("TopRight");
        TopRight.setDirection(DcMotor.Direction.REVERSE);

        BottomLeft  = hardwareMap.dcMotor.get("BottomLeft");
       BottomLeft.setDirection(DcMotor.Direction.FORWARD);

       BottomRight  = hardwareMap.dcMotor.get("BottomRight");
       BottomRight.setDirection(DcMotor.Direction.REVERSE); //reverse

       Elbow = hardwareMap.dcMotor.get("Elbow");
       Elbow.setDirection(DcMotor.Direction.FORWARD);

       // Wrist = hardwareMap.get(Servo.class, "Wrist");
       // Wrist.setDirection(Servo.Direction.FORWARD);
        Wrist = hardwareMap.servo.get("Wrist");
        Wrist.setDirection(Servo.Direction.FORWARD);
//        Wrist.setPosition(-1.0);

        Claw = hardwareMap.servo.get("Claw");
        Claw.setDirection(Servo.Direction.FORWARD);
        Claw.setPosition(1.0);
       // Claw = hardwareMap.get(Servo.class, "Claw");
    }

    @Override
    public void loop() {
//        telemetry.addLine("Loop");
        telemetry.update();

        double drive = gamepad1.left_stick_y;
        double turn  = -gamepad1.right_stick_x;
        double leftPower    = Range.clip(drive + turn, -1.0, 1.0) ;
        double rightPower   = Range.clip(drive - turn, -1.0, 1.0) ;

        TopLeft.setPower(leftPower);
        TopRight.setPower(rightPower);
        BottomLeft.setPower(leftPower);
        BottomRight.setPower(rightPower);

        if(gamepad1.dpad_up){
            Elbow.setPower(0.4);
            telemetry.addLine("Elbow up");
        }
        else if(gamepad1.dpad_down){
            Elbow.setPower(-0.6);
            telemetry.addLine("Elbow down");
        }
        else{
            Elbow.setPower(0.0);
        }

//        telemetry.addLine("Checking buttons");
        if(gamepad1.y){
            Wrist.setPosition(0.3);
            telemetry.addLine("Wrist up");
            telemetry.addLine("Wrist position: " + Wrist.getPosition());
        }
        else if (gamepad1.a){
            Wrist.setPosition(0.1);
            telemetry.addLine("Wrist down");
            telemetry.addLine("Wrist position: " + Wrist.getPosition());
        }

        if(gamepad1.b){
            Claw.setPosition(0.5);
            telemetry.addLine("Claw open");
            telemetry.addLine("Claw position: " + Claw.getPosition());
        }
        else if (gamepad1.x){
            Claw.setPosition(0.1);
            telemetry.addLine("Claw close");
            telemetry.addLine("Claw position: " + Claw.getPosition());
        }

        telemetry.update();
    }


    @Override
    public void start() {
        telemetry.addLine("Start");
        telemetry.update();

        Carousel.setPosition(-1);
    }

    @Override
    public void stop() {
        telemetry.addLine("stop");
        telemetry.update();

        TopLeft.setPower(0.0);
        TopRight.setPower(0.0);
        BottomLeft.setPower(0.0);
        BottomRight.setPower(0.0);
        Elbow.setPower(0.0);
        // Wrist.setPosition(0.0);
        // Claw.setPosition(0.0);
    }
}
