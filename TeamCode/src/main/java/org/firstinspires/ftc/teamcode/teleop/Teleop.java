package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.lib.Subsystem;
import org.firstinspires.ftc.teamcode.teleop.subsystems.ArmSubsystem;
import org.firstinspires.ftc.teamcode.teleop.subsystems.CageSubsystem;
import org.firstinspires.ftc.teamcode.teleop.subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.teleop.subsystems.ExtenderSubsystem;
import org.firstinspires.ftc.teamcode.teleop.subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.teleop.subsystems.RotatorSubsystem;
import org.firstinspires.ftc.teamcode.teleop.subsystems.Subsystems;

import java.util.ArrayList;
import java.util.List;


@TeleOp
public class Teleop extends OpMode {
    private Subsystems subsystems = new Subsystems();

    private ExtenderSubsystem extender = new ExtenderSubsystem();
    private RotatorSubsystem rotator = new RotatorSubsystem(extender);

    @Override
    public void init() {
        subsystems.add(new DriveSubsystem());
        subsystems.add(new IntakeSubsystem());
        subsystems.add(new CageSubsystem());
        subsystems.add(new ArmSubsystem(rotator, extender));

        subsystems.init(hardwareMap);
    }

    @Override
    public void loop() {
        subsystems.run(gamepad1, gamepad2);
        subsystems.telemetry(telemetry);
    }
}
