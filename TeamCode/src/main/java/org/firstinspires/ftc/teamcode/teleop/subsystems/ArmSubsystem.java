package org.firstinspires.ftc.teamcode.teleop.subsystems;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.lib.Subsystem;

public class ArmSubsystem extends Subsystem {

    private RotatorSubsystem rotator;
    private ExtenderSubsystem extender;

    // FIXME: no clue if 20 ticks is a reasonable amount
    private static final double MAX_EXTEND_MOVE = 20; // ticks
    private static final double MIN_ROTATOR_ANGLE = Math.PI / 2;

    public ArmSubsystem(RotatorSubsystem rotatorSubsystem, ExtenderSubsystem extenderSubsystem) {
        rotator = rotatorSubsystem;
        extender = extenderSubsystem;
    }

    @Override
    public void init(HardwareMap hardwareMap) {
        rotator.init(hardwareMap);
        extender.init(hardwareMap);
    }

    @Override
    public void run(Gamepad driver, Gamepad manipulator) {
        rotator.run(driver, manipulator);
        extender.run(driver, manipulator);

        boolean allowRotatorMovement = true;
        boolean allowExtenderMovement = true;

        // check states
        if ((rotator.position() <= MIN_ROTATOR_ANGLE) && (extender.position() > MAX_EXTEND_MOVE))
            allowRotatorMovement = false;

        if ((rotator.position() >= MIN_ROTATOR_ANGLE))
            allowExtenderMovement = false;

        // manage extender
        if (manipulator.dpad_up && allowExtenderMovement && (extender.position() < extender.MAX_ENCODER)) {
            extender.extend();
        } else if (manipulator.dpad_down && (extender.position() > extender.MIN_ENCODER)) {
            extender.retract();
        } else {
            extender.stop();
        }

        // manage rotator
        if (manipulator.y && (rotator.position() > rotator.MAX_ROTATION)) {
            rotator.set(0.75);
        } else if (manipulator.a && allowRotatorMovement && (rotator.position() > rotator.MIN_ROTATION)) {
            rotator.set(-0.75);
        } else {
            rotator.set(0.0);
        }
    }

    @Override
    public void telemetry(Telemetry telemetry) {
        rotator.telemetry(telemetry);
        extender.telemetry(telemetry);
    }
}
