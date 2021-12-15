package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
@TeleOp(name="test drive up", group="test")
public class Parallel_Claw_Horizontal extends OpMode{
        // Declare OpMode members.
        private ElapsedTime runtime = new ElapsedTime();
        private DcMotor frontLeft = null;
        private DcMotor frontRight = null;
        private DcMotor backRight = null;
        private DcMotor backLeft = null;
        private DcMotor linslidemot = null;
        private DcMotor clawmot = null;

        static final double INCREMENT = 0.01;     // amount to slew servo
        static final double MAX_POS = 1.0;     // Maximum rotational position
        static final double MIN_POS = 0.0;     // Minimum rotational position

        static final double ARM_POW = .6;
        static final double CLAW_POW = .2;
        // Define class members
        Servo servo;
        double position = (MAX_POS - MIN_POS) / 2; // Start at halfway position
        boolean rampUp = true;

        /*
         * Code to run ONCE when the driver hits INIT
         */
        @Override
        public void init() {
            telemetry.addData("Status", "Initialized");

            // Initialize the hardware variables. Note that the strings used here as parameters
            // to 'get' must correspond to the names assigned during the robot configuration
            // step (using the FTC Robot Controller app on the phone).
            frontLeft  = hardwareMap.get(DcMotor.class, "front_left_drive");
            frontRight = hardwareMap.get(DcMotor.class, "front_right_drive");
            backRight  = hardwareMap.get(DcMotor.class, "back_right_drive");
            backLeft = hardwareMap.get(DcMotor.class, "back_left_drive");
            linslidemot = hardwareMap.get(DcMotor.class, "linear_slide_motor");
            clawmot = hardwareMap.get(DcMotor.class, "claw_motor");

            servo = hardwareMap.get(Servo.class, "left_hand");

            // Most robots need the motor on one side to be reversed to drive forward
            // Reverse the motor that runs backwards when connected directly to the battery
            frontLeft.setDirection(DcMotor.Direction.FORWARD);
            frontRight.setDirection(DcMotor.Direction.REVERSE);
            backLeft.setDirection(DcMotor.Direction.FORWARD);
            backRight.setDirection(DcMotor.Direction.REVERSE);

            // Tell the driver that initialization is complete.
            telemetry.addData("Status", "Initialized");

        }

        @Override
        public void loop() {
            // Setup a variable for each drive wheel to save power level for telemetry

            // Choose to drive using either Tank Mode, or POV Mode
            // POV Mode uses left stick to go forward, and right stick to turn.
            // - This uses basic math to combine motions and is easier to drive straight.
            double x = gamepad1.right_stick_x;
            double y = -gamepad1.right_stick_y;
            double rotate = gamepad1.left_stick_x;

            double power = Math.hypot(x, y);
            double angle = Math.atan2(y, x) + (3*(Math.PI/4));

            //Omni wheels
            double f_l_power = power * Math.cos(angle) - rotate;
            double f_r_power = power * Math.sin(angle) + rotate;
            double b_l_power = power * Math.sin(angle) - rotate;
            double b_r_power = power * Math.cos(angle) + rotate;


            frontLeft.setPower(f_l_power);
            frontRight.setPower(f_r_power);
            backLeft.setPower(b_l_power);
            backRight.setPower(b_r_power);

            //CLAW is on the second game pad
            //Increment
            if(gamepad2.left_bumper){
                position = position < MAX_POS ? (position + INCREMENT) : MAX_POS;
            } else if(gamepad2.left_trigger > 0){
                position = position > 0 ? (position - INCREMENT) : MIN_POS;
            }
            servo.setPosition(position);
            telemetry.addData("Servo position", position);
            //Controls gear system motor

            if(gamepad2.right_bumper) {
                //wrist down
                clawmot.setPower(CLAW_POW);
                linslidemot.setPower(-ARM_POW);
            }
            else if (gamepad2.right_trigger > 0) {
                clawmot.setPower(-CLAW_POW);
                linslidemot.setPower(ARM_POW);
            }
            else {
                clawmot.setPower(0);
            }
        }

        @Override
        public void stop() {
        }

}

