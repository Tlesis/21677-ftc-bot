package org.firstinspires.ftc.teamcode.teleop.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.configuration.ConfigurationType;
import com.qualcomm.robotcore.hardware.configuration.typecontainers.MotorConfigurationType;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.lib.Subsystem;

public class ExtenderSubsystem extends Subsystem {

    private DcMotor extender;

    public static final int MIN_ENCODER = 0;
    public static final int MAX_ENCODER = 1900;

    @Override
    public void init(HardwareMap hardwareMap) {
        extender = hardwareMap.get(DcMotor.class, "extender");

        extender.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    @Override
    public void run(Gamepad driver, Gamepad manipulator) {}

    @Override
    public void telemetry(Telemetry telemetry) {
        telemetry.addData("extender", extender.getCurrentPosition());
    }

    public double position() { return extender.getCurrentPosition(); }

    public void extend() {
        if (position() < MAX_ENCODER)
            extender.setPower(1.0);
    }

    public void retract() {
        if (position() > MIN_ENCODER)
            extender.setPower(-1.0);
    }

    public void stop() { extender.setPower(0.0); }
}
