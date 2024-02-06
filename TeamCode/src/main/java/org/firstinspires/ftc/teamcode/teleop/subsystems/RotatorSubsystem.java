package org.firstinspires.ftc.teamcode.teleop.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.lib.PIDController;
import org.firstinspires.ftc.teamcode.lib.Subsystem;

public class RotatorSubsystem extends Subsystem {

    private DcMotor rotator;

    private final PIDController pid;

    private static final double PICKUP = 0;
    private static final double DROPOFF = 135;
    private double setpoint = PICKUP;

    public RotatorSubsystem(TelemetryMode mode) {
        super(mode);

        pid = new PIDController(0.02, 0.0, 0.0);
        pid.setTolerance(0.5);
    }

    @Override
    public void init(HardwareMap hardwareMap) {
        rotator = hardwareMap.get(DcMotor.class, "armMotor");

        rotator.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }

    @Override
    public void run(Gamepad driver, Gamepad manipulator) {
        if (manipulator.y)
            setpoint = DROPOFF;
        else if (manipulator.a)
            setpoint = PICKUP;

        double power = pid.calculate(position(), setpoint);
        rotator.setPower(power);
    }

    @Override
    public void telemetry(Telemetry telemetry) {
        telemetry.addData("rotator pos", "%.3f", position());
        telemetry.addData("target pos", pid.getSetpoint());
        telemetry.addData("PID speed", pid.calculate(position()));
    }

    public double position() {
        final double ticks2rad = 22.555555;
        return rotator.getCurrentPosition() / ticks2rad;
    }
}
