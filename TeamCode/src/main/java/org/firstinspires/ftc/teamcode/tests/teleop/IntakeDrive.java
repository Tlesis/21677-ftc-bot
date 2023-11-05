package org.firstinspires.ftc.teamcode.tests.teleop;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad2;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.helpers.IntakeHelper;
import org.firstinspires.ftc.teamcode.helpers.MecanumHelper;

@TeleOp
public class IntakeDrive extends OpMode {

    private DcMotor fr = null;
    private DcMotor fl = null;
    private DcMotor br = null;
    private DcMotor bl = null;
    private IMU gyro = null;

    private DcMotor intakeMotor = null;

    private Servo cage = null;

    @Override
    public void init() {
        intakeMotor = hardwareMap.get(DcMotor.class, "intake");

        intakeMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        fr = hardwareMap.get(DcMotor.class, "frontRight");
        fl = hardwareMap.get(DcMotor.class, "frontLeft");
        br = hardwareMap.get(DcMotor.class, "backRight");
        bl = hardwareMap.get(DcMotor.class, "backLeft");

        fr.setDirection(DcMotorSimple.Direction.REVERSE);
        bl.setDirection(DcMotorSimple.Direction.REVERSE);

        gyro = hardwareMap.get(IMU.class, "imu");

        IMU.Parameters parameters = new IMU.Parameters(
                new RevHubOrientationOnRobot(
                        RevHubOrientationOnRobot.LogoFacingDirection.UP,
                        RevHubOrientationOnRobot.UsbFacingDirection.FORWARD));
        gyro.initialize(parameters);

        cage = hardwareMap.get(Servo.class, "cage");
    }

    @Override
    public void loop() {
        double y = -gamepad2.left_stick_y;
        double x = gamepad2.left_stick_x;
        double theta = gamepad2.right_stick_x;

        telemetry.addData("gyro yaw", gyro.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES));

        if (gamepad2.start) {
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

        double mod = (gamepad2.b) ? 2.5 : 1.0;
        fl.setPower(frontLeftPower / mod);
        bl.setPower(backLeftPower / mod);
        fr.setPower(frontRightPower / mod);
        br.setPower(backRightPower / mod);

        if (gamepad1.a) {
            intakeMotor.setPower((gamepad1.left_bumper) ? -1.0 : 1.0);
        } else {
            intakeMotor.setPower(0.0);
        }

        if (gamepad1.b) {
            cage.setPosition(0.5);
        } else {
            cage.setPosition(0.0);
        }

        telemetry.addData("cage", "%.3f", cage.getPosition());
    }
}
