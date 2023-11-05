package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;

@TeleOp
public class MecanumDrive extends OpMode {
    private DcMotor fr = null;
    private DcMotor fl = null;
    private DcMotor br = null;
    private DcMotor bl = null;


    @Override
    public void init() {
        fr = hardwareMap.get(DcMotor.class, "frontRight");
        fl = hardwareMap.get(DcMotor.class, "frontLeft");
        br = hardwareMap.get(DcMotor.class, "backRight");
        bl = hardwareMap.get(DcMotor.class, "backLeft");

        fr.setDirection(DcMotorSimple.Direction.REVERSE);
        bl.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    @Override
    public void loop() {
        double y = -gamepad1.left_stick_y;
        double x = gamepad1.left_stick_x;
        double theta = gamepad1.right_stick_x;

        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(theta), 1);
        double frontRightPower = (y - x - theta) / denominator;
        double frontLeftPower = (y + x + theta) / denominator;
        double backRightPower = (y + x - theta) / denominator;
        double backLeftPower = (y - x + theta) / denominator;

        double mod = (gamepad1.b) ? 2.5 : 1.0;
        fr.setPower(frontRightPower / mod);
        fl.setPower(frontLeftPower / mod);
        br.setPower(backRightPower / mod);
        bl.setPower(backLeftPower / mod);
    }
}
