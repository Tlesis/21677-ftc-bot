package org.firstinspires.ftc.teamcode.lib;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public abstract class Subsystem {

    public abstract void init(HardwareMap hardwareMap);
    public abstract void run(Gamepad driver, Gamepad manipulator);
    public void telemetry(Telemetry telemetry) {}
}
