package org.firstinspires.ftc.teamcode.tests.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class Intake extends OpMode {

    private DcMotor intakeMotor = null;
    @Override
    public void init() {
        intakeMotor = hardwareMap.get(DcMotor.class, "intake");

        intakeMotor.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    @Override
    public void loop() {
        boolean intake =  gamepad1.a;
        boolean mod = gamepad1.left_bumper;

        double speed = (intake) ? 1.0 : 0.0;
        speed *= (mod) ? -1 : 1;

        intakeMotor.setPower(speed);

    }
}
