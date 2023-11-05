package org.firstinspires.ftc.teamcode.teleop.subsystems;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.lib.Subsystem;

public class CageSubsystem extends Subsystem {
    private Servo cage;

    @Override
    public void init(HardwareMap hardwareMap) {
        cage = hardwareMap.get(Servo.class, "cage");
    }

    @Override
    public void run(Gamepad driver, Gamepad manipulator) {
        if (manipulator.b) {
            cage.setPosition(0.5);
        } else {
            cage.setPosition(0.0);
        }
    }

    @Override
    public void telemetry(Telemetry telemetry) {
        telemetry.addData("cage", "%.3f", cage.getPosition());
    }
}
