package org.firstinspires.ftc.teamcode.tests.sub.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.teleop.subsystems.DriveSubsystem;

@TeleOp
public class DriveSub extends OpMode {
    private DriveSubsystem drive = new DriveSubsystem();

    @Override
    public void init() {
        drive.init(hardwareMap);
    }

    @Override
    public void loop() {
        drive.run(gamepad1, gamepad2);
        drive.telemetry(telemetry);
    }
}
