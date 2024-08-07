package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.teamcode.OuttakePIDFLoop;

@Disabled
@TeleOp (name = "Automated Functions")
public class AutomatedFunctions extends LinearOpMode {
    private DcMotor frontLeft;
    private DcMotor backLeft;
    private DcMotor frontRight;
    private DcMotor backRight;
    private DcMotor intakeMotor;
    private DcMotor slideLeftMotor;
    private DcMotor slideRightMotor;
    private Servo airplane;
    private CRServo deposit;
    private Servo latch;

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
        deposit = hardwareMap.crservo.get("deposit");
        latch = hardwareMap.servo.get("latch");
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        backRight.setDirection(DcMotorSimple.Direction.REVERSE);


        waitForStart();

        while (opModeIsActive()) {


        }


    }
}