package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@TeleOp (name = "SayWattTeleOp")
public class SayWattTeleOp extends LinearOpMode {
    private DcMotor frontLeft;
    private DcMotor backLeft;
    private DcMotor frontRight;
    private DcMotor backRight;
    private DcMotor intakeMotor;
    private DcMotor slideLeftMotor;
    private DcMotor slideRightMotor;
    private Servo airplane;
    private Servo deposit;
    private Servo latch;
    private Rev2mDistanceSensor distance;
    private int Pos;

    @Override
    public void runOpMode() throws InterruptedException {

        // Color code refers to red, green, blue
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        // Color code refers to blue, green, red
        backLeft = hardwareMap.dcMotor.get("backLeft");
        // Color code refers to green, red, blue
        frontRight = hardwareMap.dcMotor.get("frontRight");
        // Color code refers to blue, red, green
        backRight = hardwareMap.dcMotor.get("backRight");
        intakeMotor = hardwareMap.dcMotor.get("intakeMotor");
        slideLeftMotor = hardwareMap.dcMotor.get("slideLeftMotor");
        slideRightMotor = hardwareMap.dcMotor.get("slideRightMotor");
        airplane = hardwareMap.servo.get("airplane");
        deposit = hardwareMap.servo.get("deposit");
        latch = hardwareMap.servo.get("latch");
        distance = hardwareMap.get(Rev2mDistanceSensor.class, "distance");
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        backRight.setDirection(DcMotorSimple.Direction.REVERSE);
        double maxSpeed = 0.725;
        Pos = 0;

        double f, r, s;
        double fLeftPower, bLeftPower, fRightPower, bRightPower;
        double length = distance.getDistance(DistanceUnit.MM);

        waitForStart();
        latch.setPosition(0.895);
        airplane.setPosition(0.89);
        deposit.setPosition(0.155);
        if (opModeIsActive()) {
            while (opModeIsActive()) {

                f = gamepad1.left_stick_y;
                r = -gamepad1.right_stick_x;
                s = -gamepad1.left_stick_x;
                fLeftPower = f + r + s;
                bLeftPower = f + r - s;
                fRightPower = f - r - s;
                bRightPower = f - r + s;
                double maxN = Math.max(Math.abs(fLeftPower), Math.max(Math.abs(bLeftPower),
                        Math.max(Math.abs(fRightPower), Math.abs(bRightPower))));

                if (maxN > 1) {
                    fLeftPower /= maxN;
                    bLeftPower /= maxN;
                    fRightPower /= maxN;
                    bRightPower /= maxN;
                }
                frontLeft.setPower(fLeftPower * maxSpeed);
                backLeft.setPower(bLeftPower * maxSpeed);
                frontRight.setPower(fRightPower * maxSpeed);
                backRight.setPower(bRightPower * maxSpeed);


                if (gamepad2.left_bumper) {
                    latch.setPosition(0.915);
                } else if (gamepad2.right_bumper) {
                    latch.setPosition(0.58);
                }

                if (gamepad2.dpad_down) {
                    deposit.setPosition(0.45);
                } else if (gamepad2.dpad_up) {
                    deposit.setPosition(0.145);
                }
                if (gamepad2.dpad_right) {
                    deposit.setPosition(0.23);
                }

                if (gamepad1.x) {
                    intakeMotor.setPower(0.75);
                } else if (gamepad1.b) {
                    intakeMotor.setPower(-0.75);
                } else {
                    intakeMotor.setPower(0);
                }
                if (gamepad2.a) {
                    slideLeftMotor.setPower(-0.7);
                    slideRightMotor.setPower(0.7);
                } else if (gamepad2.y) {
                    slideLeftMotor.setPower(0.7);
                    slideRightMotor.setPower(-0.7);
                } else {
                    slideLeftMotor.setPower(0);
                    slideRightMotor.setPower(0);
                }

                if (gamepad2.x) {
                    airplane.setPosition(0);
                } else if (gamepad2.b) {
                    airplane.setPosition(0.895);
                }


            }


        }

    }
    public void intakeProcedure(double speed, long time) throws InterruptedException {
        intakeMotor.setPower(speed);
        Thread.sleep(time);
    }
    public void lift(int target, double speed) throws InterruptedException {
        Pos += target;

        slideLeftMotor.setTargetPosition(Pos);
        slideRightMotor.setTargetPosition(Pos);

        slideLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        slideRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        slideLeftMotor.setPower(speed);
        slideRightMotor.setPower(speed);
    }

    }