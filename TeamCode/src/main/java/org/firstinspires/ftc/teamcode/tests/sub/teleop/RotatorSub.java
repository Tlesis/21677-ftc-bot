package org.firstinspires.ftc.teamcode.tests.sub.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.teleop.subsystems.RotatorSubsystem;

@TeleOp
public class RotatorSub extends OpMode {

    private RotatorSubsystem rotator = new RotatorSubsystem();

    @Override
    public void init() {
        rotator.init(hardwareMap);
    }

    @Override
    public void loop() {
        rotator.run(gamepad1, gamepad2);
        rotator.telemetry(telemetry);
    }
}
