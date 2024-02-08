package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.teleop.subsystems.CageSubsystem;
import org.firstinspires.ftc.teamcode.teleop.subsystems.ClimbSubsystem;
import org.firstinspires.ftc.teamcode.teleop.subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.teleop.subsystems.DroneSubsystem;
import org.firstinspires.ftc.teamcode.teleop.subsystems.ExtenderSubsystem;
import org.firstinspires.ftc.teamcode.teleop.subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.teleop.subsystems.RotatorSubsystem;
import org.firstinspires.ftc.teamcode.teleop.subsystems.Subsystems;

import org.firstinspires.ftc.teamcode.lib.Subsystem.TelemetryMode;
import org.firstinspires.ftc.teamcode.teleop.subsystems.VisionSubsystem;

@TeleOp
public class Teleop extends OpMode {
    private Subsystems subsystems = new Subsystems();

    private DriveSubsystem drive        = new DriveSubsystem(TelemetryMode.SILENT);
    private IntakeSubsystem intake      = new IntakeSubsystem(TelemetryMode.SILENT);
    private CageSubsystem cage          = new CageSubsystem(TelemetryMode.SILENT);
    private DroneSubsystem drone        = new DroneSubsystem(TelemetryMode.SILENT);
    private RotatorSubsystem rotator    = new RotatorSubsystem(TelemetryMode.SILENT);
    // private ClimbSubsystem climb        = new ClimbSubsystem(TelemetryMode.SILENT);

    private ExtenderSubsystem extender  = new ExtenderSubsystem(TelemetryMode.PRINT);
    private VisionSubsystem vision      = new VisionSubsystem(TelemetryMode.PRINT);

    @Override
    public void init() {
        subsystems.add(drive);
        subsystems.add(intake);
        subsystems.add(cage);
        subsystems.add(drone);
        subsystems.add(rotator);
        // subsystems.add(climb);
        subsystems.add(extender);
        subsystems.add(vision);

        subsystems.init(hardwareMap);
    }

    @Override
    public void loop() {
        subsystems.run(gamepad1, gamepad2);
        subsystems.telemetry(telemetry);
    }
}
