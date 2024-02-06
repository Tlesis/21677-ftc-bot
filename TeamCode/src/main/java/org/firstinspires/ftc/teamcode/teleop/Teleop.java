package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.teleop.subsystems.CageSubsystem;
import org.firstinspires.ftc.teamcode.teleop.subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.teleop.subsystems.DroneSubsystem;
import org.firstinspires.ftc.teamcode.teleop.subsystems.ExtenderSubsystem;
import org.firstinspires.ftc.teamcode.teleop.subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.teleop.subsystems.RotatorSubsystem;
import org.firstinspires.ftc.teamcode.teleop.subsystems.Subsystems;

import org.firstinspires.ftc.teamcode.lib.Subsystem.TelemetryMode;

@TeleOp
public class Teleop extends OpMode {
    private Subsystems subsystems = new Subsystems();

    private DriveSubsystem drive        = new DriveSubsystem(TelemetryMode.SILENT);
    private IntakeSubsystem intake      = new IntakeSubsystem(TelemetryMode.SILENT);
    private CageSubsystem cage          = new CageSubsystem(TelemetryMode.PRINT);
    private DroneSubsystem drone        = new DroneSubsystem(TelemetryMode.SILENT);
    private RotatorSubsystem rotator    = new RotatorSubsystem(TelemetryMode.PRINT);
    private ExtenderSubsystem extender  = new ExtenderSubsystem(TelemetryMode.PRINT);

    @Override
    public void init() {
        subsystems.add(drive);
        subsystems.add(intake);
        subsystems.add(cage);
        subsystems.add(drone);
        subsystems.add(rotator);
        subsystems.add(extender);

        subsystems.init(hardwareMap);
    }

    @Override
    public void loop() {
        subsystems.run(gamepad1, gamepad2);
        subsystems.telemetry(telemetry);
    }
}
