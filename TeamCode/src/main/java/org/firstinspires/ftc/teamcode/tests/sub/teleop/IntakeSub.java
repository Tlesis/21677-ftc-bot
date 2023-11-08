package org.firstinspires.ftc.teamcode.tests.sub.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.teleop.subsystems.IntakeSubsystem;

@TeleOp
public class IntakeSub extends OpMode {
    private IntakeSubsystem intake = new IntakeSubsystem();

    @Override
    public void init() {
       intake.init(hardwareMap);
    }

    @Override
    public void loop() {
        intake.run(gamepad1, gamepad2);
        intake.telemetry(telemetry);
    }
}
