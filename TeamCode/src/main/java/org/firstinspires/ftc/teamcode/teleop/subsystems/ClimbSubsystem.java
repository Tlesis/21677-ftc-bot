package org.firstinspires.ftc.teamcode.teleop.subsystems;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.lib.Subsystem;

public class ClimbSubsystem extends Subsystem {

    private DcMotor climber;
    private CRServo release;

    public ClimbSubsystem(TelemetryMode mode) { super(mode); }

    @Override
    public void init(HardwareMap hardwareMap) {
        climber = hardwareMap.get(DcMotor.class, "climber");
        release = hardwareMap.get(CRServo.class, "r");

        climber.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    @Override
    public void run(Gamepad driver, Gamepad manipulator) {
        if (manipulator.dpad_up) {
            climber.setPower(-1);
        } else if (manipulator.dpad_down) {
            climber.setPower(1);
        } else {
            climber.setPower(0);
        }

        if (manipulator.left_stick_button)
            release.setPower(1);
        else if (manipulator.right_stick_button)
            release.setPower(-1);
        else
            release.setPower(0);
    }
}
