package org.firstinspires.ftc.teamcode.teleop.subsystems;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.lib.Subsystem;

import java.util.ArrayList;
import java.util.List;

public class Subsystems extends Subsystem {
    public static List<Subsystem> subsystems = new ArrayList<>();

    public Subsystems() {
        super(TelemetryMode.PRINT);
    }

    @Override
    public void init(HardwareMap hardwareMap) {
        for (Subsystem s : subsystems)
            s.init(hardwareMap);
    }

    @Override
    public void run(Gamepad driver, Gamepad manipulator) {
       for (Subsystem s : subsystems) {
           s.run(driver, manipulator);
       }
    }

    @Override
    public void telemetry(Telemetry telemetry) {
        for (Subsystem s : subsystems) {
            if (s.getMode() == TelemetryMode.PRINT)
                s.telemetry(telemetry);
        }
    }

    public void add(Subsystem s) {
        subsystems.add(s);
    }
}
