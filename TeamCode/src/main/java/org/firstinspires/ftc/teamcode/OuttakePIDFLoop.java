package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@Config
@TeleOp (name = "OuttakePIDFLoop")
public class OuttakePIDFLoop extends OpMode {
    private PIDController controller;

    public static double p = 0.001, i = 0, d = 0;
    public static double f = 0.15;
    public static int target = 0;
    private final double ticks_in_degree = 700/180.0;

    private DcMotor slideLeftMotor;
    private DcMotor slideRightMotor;



    @Override
    public void init() {
        controller = new PIDController(p, i, d);
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        slideLeftMotor = hardwareMap.get(DcMotor.class, "slideLeftMotor");
        slideRightMotor = hardwareMap.get(DcMotor.class, "slideRightMotor");

    }

    @Override
    public void loop() {
        controller.setPID(p, i, d);
        int slidePosLeft = slideLeftMotor.getCurrentPosition();
        int slidePosRight = slideRightMotor.getCurrentPosition();

        double pidLeft = controller.calculate(slidePosLeft, target);
        double ffLeft = Math.cos(Math.toRadians((target/ticks_in_degree)));
        double powerLeft = pidLeft + ffLeft;
        double pidRight = controller.calculate(slidePosRight, target);
        double ffRight = Math.cos(Math.toRadians((target/ticks_in_degree)));
        double powerRight = pidRight + ffRight;
        slideLeftMotor.setPower(powerLeft);
        slideRightMotor.setPower(powerRight);
        telemetry.addData("Left Position", slidePosLeft);
        telemetry.addData("Right Position", slidePosRight);
        telemetry.addData("target", target);
        telemetry.update();



    }

}