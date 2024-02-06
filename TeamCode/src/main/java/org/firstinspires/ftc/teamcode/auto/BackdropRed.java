package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.teleop.subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.teleop.subsystems.IntakeSubsystem;

import org.firstinspires.ftc.teamcode.lib.Subsystem.TelemetryMode;

@Autonomous
public class BackdropRed extends OpMode {

    private ElapsedTime timer = new ElapsedTime(ElapsedTime.Resolution.SECONDS);

    private DriveSubsystem drive = new DriveSubsystem(TelemetryMode.PRINT);
    private IntakeSubsystem intake = new IntakeSubsystem(TelemetryMode.PRINT);

    private boolean h = false;

    @Override
    public void init() {
        drive.init(hardwareMap);
        intake.init(hardwareMap);

        timer.reset();
    }

    @Override
    public void loop() {
        if (!h) {
            h = true;
            timer.reset();
        }

        if (time() < 0.5)
            drive.drive(0.0, 0.45, 0.0);
        else if (time() < 2.0)
            drive.drive(0.45, 0, 0); // drive up to spike mark
        else if (time() < 2.5)
            drive.stop(); // stop
        else if (time() < 3.5)
            intake.set(-0.5); // spit pixel if it is there
        else if (time() < 4.5)
            intake.set(0.0); // stop intake
        else if (time() < 6.5)
            drive.drive(0, 0.45, 0.0); // strafe into park
        else
            drive.stop();

        telemetry.addData("time", time());
    }

    private double time() {
        return timer.time();
    }
}
