package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.lib.Subsystem;
import org.firstinspires.ftc.teamcode.teleop.subsystems.CageSubsystem;
import org.firstinspires.ftc.teamcode.teleop.subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.teleop.subsystems.IntakeSubsystem;

import java.util.ArrayList;
import java.util.List;


@TeleOp
public class Teleop extends OpMode {

    public List<Subsystem> subsystems = new ArrayList<>();
    @Override
    public void init() {
        subsystems.add(new DriveSubsystem());
        subsystems.add(new IntakeSubsystem());
        subsystems.add(new CageSubsystem());

        for (Subsystem s : subsystems)
            s.init(hardwareMap);
    }

    @Override
    public void loop() {
        for (Subsystem s : subsystems) {
            // run subsystem code
            s.run(gamepad1, gamepad2);

            // print telemetry data
            s.telemetry(telemetry);
        }
    }
}
