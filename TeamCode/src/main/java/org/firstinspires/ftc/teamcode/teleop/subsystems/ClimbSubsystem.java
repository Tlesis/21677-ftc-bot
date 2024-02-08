package org.firstinspires.ftc.teamcode.teleop.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.lib.Subsystem;

public class ClimbSubsystem extends Subsystem {

    private DcMotor climber;

    public ClimbSubsystem(TelemetryMode mode) { super(mode); }

    @Override
    public void init(HardwareMap hardwareMap) {
        climber = hardwareMap.get(DcMotor.class, "climber");
        climber.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    @Override
    public void run(Gamepad driver, Gamepad manipulator) {
        if (manipulator.dpad_up)
            climber.setPower(1);
        else if (manipulator.dpad_down && climber.getCurrentPosition() >= 0)
            climber.setPower(-0.75);
        else
            climber.setPower(0);
    }
}
