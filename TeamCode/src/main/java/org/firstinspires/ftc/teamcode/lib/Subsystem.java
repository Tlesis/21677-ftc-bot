package org.firstinspires.ftc.teamcode.lib;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.ArrayList;
import java.util.List;

public abstract class Subsystem {
    protected TelemetryMode printMode;

    public Subsystem(TelemetryMode mode) { printMode = mode; }
    public abstract void init(HardwareMap hardwareMap);
    public abstract void run(Gamepad driver, Gamepad manipulator);
    public void telemetry(Telemetry telemetry) {}

    public TelemetryMode getMode() { return printMode; }

    public enum TelemetryMode {
        PRINT,
        SILENT;
    }
}
