package org.firstinspires.ftc.teamcode.tests.sub.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.teleop.subsystems.ExtenderSubsystem;

@TeleOp
public class ExtenderSub extends OpMode {

    private ExtenderSubsystem extender = new ExtenderSubsystem();

    @Override
    public void init() {
        extender.init(hardwareMap);
    }

    @Override
    public void loop() {
        extender.run(gamepad1, gamepad2);
        extender.telemetry(telemetry);
    }
}
