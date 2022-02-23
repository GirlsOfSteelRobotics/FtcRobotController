package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;


@TeleOp(name="TeleOp Hypatia", group="Linear Opmode")
public class TeleOpHypatia extends OpMode {

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
      TopLeft.setDirection(DcMotor.Direction.REVERSE);

        TopRight  = hardwareMap.dcMotor.get("TopRight");
        TopRight.setDirection(DcMotor.Direction.FORWARD);

        BottomLeft  = hardwareMap.dcMotor.get("BottomLeft");
       BottomLeft.setDirection(DcMotor.Direction.REVERSE);

       BottomRight  = hardwareMap.dcMotor.get("BottomRight");
       BottomRight.setDirection(DcMotor.Direction.FORWARD);

       Elbow = hardwareMap.dcMotor.get("Elbow");
       Elbow.setDirection(DcMotor.Direction.FORWARD);

       // Wrist = hardwareMap.get(Servo.class, "Wrist");
       // Wrist.setDirection(Servo.Direction.FORWARD);
        Wrist = hardwareMap.servo.get("Wrist");
        Wrist.setPosition(-1.0);

        Claw = hardwareMap.servo.get("Claw");
        Claw.setPosition(1.0);
       // Claw = hardwareMap.get(Servo.class, "Claw");
    }

    @Override
    public void loop() {
//        telemetry.addLine("Loop");
        telemetry.update();

        double drive = -gamepad1.left_stick_y;
        double turn  =  gamepad1.right_stick_x;
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
            Elbow.setPower(-0.4);
            telemetry.addLine("Elbow down");
        }
        else{
            Elbow.setPower(0.0);
        }

//        telemetry.addLine("Checking buttons");
        if(gamepad1.y){
            Wrist.setPosition(1.0);
            telemetry.addLine("Wrist up");
        }
        else if (gamepad1.a){
            Wrist.setPosition(-1.0);
            telemetry.addLine("Wrist down");
        }

        if(gamepad1.b){
            Claw.setPosition(1.0);
            telemetry.addLine("Claw open");
        }
        else if (gamepad1.x){
            Claw.setPosition(-1.0);
            telemetry.addLine("Claw close");
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
        Elbow.setPower(0.0);
        // Wrist.setPosition(0.0);
        // Claw.setPosition(0.0);
    }
}
