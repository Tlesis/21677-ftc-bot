package org.firstinspires.ftc.teamcode.teleop.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.lib.Subsystem;

import java.util.function.DoubleSupplier;

public class RotatorSubsystem extends Subsystem {

    private DcMotor rotator;

    private double lastTime, lastPosition;
    private ElapsedTime time;

    // FIXME: S.W.A.G.
    public static final double MAX_ROTATION = (3.0 * Math.PI) / 4.0;
    public static final double MIN_ROTATION = -Math.PI / 6.0;

    public RotatorSubsystem() {
        time = new ElapsedTime(ElapsedTime.Resolution.SECONDS);
    }

    @Override
    public void init(HardwareMap hardwareMap) {
        rotator = hardwareMap.get(DcMotor.class, "armMotor");

        lastTime = time.time();
        lastPosition = position();
    }

    @Override
    public void run(Gamepad driver, Gamepad manipulator) {}

    @Override
    public void telemetry(Telemetry telemetry) {
        telemetry.addData("rotator avg pos (rad/s)", "%.3f", position());
        telemetry.addData("rotator avg vel (rad/s/s)", "%.3f", velocity());
    }

    public void set(double power) {
        rotator.setPower(power);
    }

    public double position() {
        final double tick2Rad = 1.0; // FIXME
        return (rotator.getCurrentPosition() * tick2Rad);
    }

    public double velocity() {
        double pos = position();
        double tF = time.time();

        double dDisplacement = pos - lastPosition;
        double dT = tF - lastTime;

        double dV = dDisplacement / dT;

        lastPosition = pos;
        lastTime = tF;

        return dV;
    }
}
