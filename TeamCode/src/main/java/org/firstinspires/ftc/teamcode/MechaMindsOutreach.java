package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@TeleOp(name = "Mecha Minds Outreach")
public class MechaMindsOutreach extends LinearOpMode {
    private DcMotor frontLeft;
    private DcMotor backLeft;
    private DcMotor frontRight;
    private DcMotor backRight;

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
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        backRight.setDirection(DcMotorSimple.Direction.REVERSE);
        double maxSpeed = 0.725;


        double f, r, s;
        double fLeftPower, bLeftPower, fRightPower, bRightPower;


        waitForStart();
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


            }
        }
    }
}