package org.firstinspires.ftc.teamcode.tests.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class ArmMotor extends OpMode {
    private DcMotor armMotor = null;

    @Override
    public void init() {
        armMotor = hardwareMap.get(DcMotor.class, "armMotor");

        armMotor.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    @Override
    public void loop() {
        telemetry.addData("rotator encoder", armMotor.getCurrentPosition());

        if (gamepad1.y)
            armMotor.setPower(0.5);
        else if (gamepad1.a)
            armMotor.setPower(-0.5);
        else
            armMotor.setPower(0.0);
    }
}
