package org.firstinspires.ftc.teamcode.tests.sub.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.teleop.subsystems.CageSubsystem;

@TeleOp
public class CageSub extends OpMode {

    private CageSubsystem cage = new CageSubsystem();

    @Override
    public void init() {
        cage.init(hardwareMap);
    }

    @Override
    public void loop() {
        cage.run(gamepad1, gamepad2);
        cage.telemetry(telemetry);
    }
}
