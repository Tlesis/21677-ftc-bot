package org.firstinspires.ftc.teamcode.tests.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.teleop.subsystems.ExtenderSubsystem;

@TeleOp
public class ExtenderSubTest extends OpMode {

    private ExtenderSubsystem extender = new ExtenderSubsystem();

    @Override
    public void init() {
        extender.init(hardwareMap);
    }

    @Override
    public void loop() {
        extender.telemetry(telemetry);

        if (gamepad1.dpad_up) {
            extender.extend();
        } else if (gamepad1.dpad_down) {
            extender.retract();
        } else {
            extender.stop();
        }
    }
}
