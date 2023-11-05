package org.firstinspires.ftc.teamcode.tests.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class ArmMotor extends OpMode {
    private DcMotor leftArmMotor = null;
    private DcMotor  rightArmMotor = null;

    @Override
    public void init() {
        leftArmMotor = hardwareMap.get(DcMotor.class, "lArmMotor");
        rightArmMotor = hardwareMap.get(DcMotor.class, "rArmMotor");

        leftArmMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        rightArmMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        telemetry.addData("left encoder: ", "%.3f", leftArmMotor.getCurrentPosition());
        telemetry.addData("right encoder: ", "%.3f", rightArmMotor.getCurrentPosition());
    }

    @Override
    public void loop() {
        double y =  gamepad1.left_stick_y;
        telemetry.update();

        leftArmMotor.setPower(y);
        rightArmMotor.setPower(y);
    }
}
