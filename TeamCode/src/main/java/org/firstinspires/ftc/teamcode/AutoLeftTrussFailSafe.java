package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous (name = "AutoLeftTrussFailSafe", preselectTeleOp = "SayWattTeleOp")
public class AutoLeftTrussFailSafe extends LinearOpMode {
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

        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        backRight.setDirection(DcMotorSimple.Direction.REVERSE);

        Pos = 0;

        waitForStart();
        latch.setPosition(0.58);
        airplane.setPosition(0.89);
        deposit.setPosition(0.147);
        Thread.sleep(300);
        strafeLeft(200, 0.3);
        Thread.sleep(275);
        driveForward(3700, 0.4);
        Thread.sleep(3700);
        strafeLeft(1800, 0.3);
        Thread.sleep(1800);
        stop(250);
        lift(500, 0.8);
        Thread.sleep(500);
        slideLeftMotor.setPower(0.3);
        slideLeftMotor.setPower(0.3);
        deposit.setPosition(0.45);
        Thread.sleep(300);
        latch.setPosition(0.86);
        Thread.sleep(800);
        deposit.setPosition(0.147);
        Thread.sleep(500);
        lift(-1400, 0.8);
        Thread.sleep(500);
        strafeRight(1250, 0.4);
        Thread.sleep(1250);

        if (isStopRequested()) return;

    }

    public void strafeLeft( int target, double speed) throws InterruptedException  {
        Pos += target;

        frontLeft.setTargetPosition(Pos);
        backLeft.setTargetPosition(-Pos);
        frontRight.setTargetPosition(-Pos);
        backRight.setTargetPosition(Pos);

        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        frontLeft.setPower(speed);
        backRight.setPower(speed);
        frontRight.setPower(speed);
        backLeft.setPower(speed);
    }
    public void strafeRight( int target, double speed) throws InterruptedException  {
        Pos += target;

        frontLeft.setTargetPosition(-Pos);
        backLeft.setTargetPosition(Pos);
        frontRight.setTargetPosition(Pos);
        backRight.setTargetPosition(-Pos);

        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        frontLeft.setPower(speed);
        backRight.setPower(speed);
        frontRight.setPower(speed);
        backLeft.setPower(speed);
    }
    public void driveBack( int target, double speed) throws InterruptedException {
        Pos += target;

        frontLeft.setTargetPosition(Pos);
        backLeft.setTargetPosition(Pos);
        frontRight.setTargetPosition(Pos);
        backRight.setTargetPosition(Pos);

        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        frontLeft.setPower(speed);
        backRight.setPower(speed);
        frontRight.setPower(speed);
        backLeft.setPower(speed);

    }
    public void driveForward( int target, double speed) throws InterruptedException {
        Pos += target;

        frontLeft.setTargetPosition(-Pos);
        backLeft.setTargetPosition(-Pos);
        frontRight.setTargetPosition(-Pos);
        backRight.setTargetPosition(-Pos);

        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        frontLeft.setPower(speed);
        backRight.setPower(speed);
        frontRight.setPower(speed);
        backLeft.setPower(speed);

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
    public void stop( long time) throws InterruptedException  {

        frontLeft.setPower(0);
        backRight.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        Thread.sleep(time);
    }

}

