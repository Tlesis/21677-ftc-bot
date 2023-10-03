package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

@TeleOp
public class MecanumFieldOriented extends OpMode {
    private DcMotor fr = null;
    private DcMotor fl = null;
    private DcMotor br = null;
    private DcMotor bl = null;

    private IMU gyro = null;


    @Override
    public void init() {
        fr = hardwareMap.get(DcMotor.class, "frontRight");
        fl = hardwareMap.get(DcMotor.class, "frontLeft");
        br = hardwareMap.get(DcMotor.class, "backRight");
        bl = hardwareMap.get(DcMotor.class, "backLeft");

        fl.setDirection(DcMotorSimple.Direction.REVERSE);
        br.setDirection(DcMotorSimple.Direction.REVERSE);

        gyro = hardwareMap.get(IMU.class, "imu");

        IMU.Parameters parameters = new IMU.Parameters(
                new RevHubOrientationOnRobot(
                    RevHubOrientationOnRobot.LogoFacingDirection.UP,
                    RevHubOrientationOnRobot.UsbFacingDirection.BACKWARD));
        gyro.initialize(parameters);
    }

    @Override
    public void loop() {
        double y = -gamepad1.left_stick_y;
        double x = gamepad1.left_stick_x;
        double theta = gamepad1.right_stick_x;

        telemetry.addData("gyro yaw", gyro.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES));

        if (gamepad1.start) {
            gyro.resetYaw();
        }

        double botHeading = gyro.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);

        double rotX = x * Math.cos(-botHeading) - y * Math.sin(-botHeading);
        double rotY = x * Math.sin(-botHeading) + y * Math.cos(-botHeading);

        rotX = rotX * 1.1;  // Counteract imperfect strafing

        double denominator = Math.max(Math.abs(rotY) + Math.abs(rotX) + Math.abs(theta), 1);
        double frontLeftPower = (rotY + rotX + theta) / denominator;
        double backLeftPower = (rotY - rotX + theta) / denominator;
        double frontRightPower = (rotY - rotX - theta) / denominator;
        double backRightPower = (rotY + rotX - theta) / denominator;

        double mod = (gamepad1.b) ? 2.5 : 1.0;
        fl.setPower(frontLeftPower / mod);
        bl.setPower(backLeftPower / mod);
        fr.setPower(frontRightPower / mod);
        br.setPower(backRightPower / mod);
    }
}
