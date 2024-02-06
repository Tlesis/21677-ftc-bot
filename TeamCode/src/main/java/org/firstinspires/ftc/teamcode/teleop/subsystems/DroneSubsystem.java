package org.firstinspires.ftc.teamcode.teleop.subsystems;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.lib.Subsystem;

public class DroneSubsystem extends Subsystem {

    private Servo drone;

    public DroneSubsystem(TelemetryMode mode) {
        super(mode);
    }

    @Override
    public void init(HardwareMap hardwareMap) {
        drone = hardwareMap.get(Servo.class, "drone");
    }

    @Override
    public void run(Gamepad driver, Gamepad manipulator) {
        if (manipulator.start) {
            drone.setPosition(0.0);
        } else {
            drone.setPosition(0.5);
        }

    }

    @Override
    public void telemetry(Telemetry telemetry) {
        telemetry.addData("drone", drone.getPosition());
    }
}
