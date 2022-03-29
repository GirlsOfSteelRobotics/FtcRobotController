package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.DcMotorEx;


@TeleOp(name="TeleOp Hypatia Red", group="Linear Opmode")
public class TeleOpHypatiaRed extends OpMode {

    private DcMotor TopLeft = null;
    private DcMotor TopRight = null;
    private DcMotor BottomLeft = null;
    private DcMotor BottomRight = null;
    private DcMotorEx Elbow = null;
    private DcMotorEx Arm = null;
    private Servo Claw = null;
    private Servo Carousel = null;
    // phone 9820

    // Stores the arm's and elbow's current position
    private int armPosition;
    private int elbowPosition;

    // Ideal encoder tick position for arm to be in full up or down position
    private final int ARM_UP_POSITION = -35;
    private final int ARM_DOWN_POSITION = 0;

    private final int ELBOW_UP_POSITION = 60;
    private final int ELBOW_DOWN_POSITION = 0;

    // Ideal speed (in encoder ticks/second) to try to get to up or down positions
    private final int ARM_UP_VELOCITY = 70;
    private final int ARM_DOWN_VELOCITY = 70;

    private final int ELBOW_UP_VELOCITY = 100;
    private final int ELBOW_DOWN_VELOCITY = 100;


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

        Elbow = hardwareMap.get(DcMotorEx.class, "Elbow");
        Elbow.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        elbowPosition = 0;

        Arm = hardwareMap.get(DcMotorEx.class, "Arm");
        Arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        armPosition = 0;

        Claw = hardwareMap.servo.get("Claw");
        Claw.setDirection(Servo.Direction.FORWARD);
        Claw.setPosition(1.0);
        // Claw = hardwareMap.get(Servo.class, "Claw");

        Carousel = hardwareMap.servo.get("Carousel");
        Carousel.setDirection(Servo.Direction.REVERSE);
        Carousel.setPosition(1.0);

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
            // Elbow goes up
            telemetry.addLine("Elbow up");
            if (elbowPosition != ELBOW_UP_POSITION){
                elbowPosition = ELBOW_UP_POSITION;
                Elbow.setTargetPosition(elbowPosition);
                Elbow.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                Elbow.setVelocity(ELBOW_UP_VELOCITY);
                telemetry.addLine("Elbow set to position " + elbowPosition);
            }
        }
        else if(gamepad1.dpad_down){
            // Elbow goes down
            telemetry.addLine("Elbow down");
            if (elbowPosition != ELBOW_DOWN_POSITION) {
                elbowPosition = ELBOW_DOWN_POSITION;
                Elbow.setTargetPosition(elbowPosition);
                Elbow.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                Elbow.setVelocity(ELBOW_DOWN_VELOCITY);
                telemetry.addLine("Elbow set to position " + elbowPosition);
            }
        }
/*        else{
            Elbow.setPower(0.0);
        }*/

        if(gamepad1.a){
            // Arm goes down
            telemetry.addLine("Arm down");
            if (armPosition != ARM_DOWN_POSITION) {
                armPosition = ARM_DOWN_POSITION;
                Arm.setTargetPosition(armPosition);
                Arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                Arm.setVelocity(ARM_DOWN_VELOCITY);
                telemetry.addLine("Arm set to position" + armPosition);
            }
        }
//
        else if(gamepad1.y){
            // Arm goes up
            telemetry.addLine("Arm up");
            if (armPosition != ARM_UP_POSITION){
                armPosition = ARM_UP_POSITION;
                Arm.setTargetPosition(armPosition);
                Arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                Arm.setVelocity(ARM_UP_VELOCITY);
                telemetry.addLine("Arm set to position" + armPosition);
            }
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
        if (Elbow.isBusy()){
            telemetry.addData("Elbow position", Elbow.getCurrentPosition());
        }
        if (Arm.isBusy()){
            telemetry.addData("Arm position", Arm.getCurrentPosition());
/*            if (armPosition == ARM_UP_POSITION & Math.abs(armPosition-Arm.getCurrentPosition()) < 5){
                Arm.setVelocity(0);
            }
            if (armPosition == ARM_DOWN_POSITION & Math.abs(armPosition-Arm.getCurrentPosition()) < 5){
                Arm.setVelocity(0);
            }*/
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
        // Elbow.setPower(0.0);
        // Wrist.setPosition(0.0);
        // Claw.setPosition(0.0);
    }
}
