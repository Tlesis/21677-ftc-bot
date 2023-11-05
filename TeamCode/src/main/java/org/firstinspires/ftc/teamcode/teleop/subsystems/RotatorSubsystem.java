package org.firstinspires.ftc.teamcode.teleop.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.lib.Feedforward;
import org.firstinspires.ftc.teamcode.lib.Subsystem;

import java.util.function.DoubleSupplier;

public class RotatorSubsystem extends Subsystem {

    private DcMotor left, right;

    private Feedforward feedforward;
    private double lastTime, lastPosition;
    private ElapsedTime time;
    private double
            ks = 0,
            kv = 0;

    // FIXME: S.W.A.G.
    public static final double MAX_ROTATION = (3.0 * Math.PI) / 4.0;
    public static final double MIN_ROTATION = -Math.PI / 6.0;

    public RotatorSubsystem(ExtenderSubsystem extender) {
        feedforward = new Feedforward(
                ks,
                () -> kG(() -> extender.position()),
                kv);

        time = new ElapsedTime(ElapsedTime.Resolution.SECONDS);
    }

    @Override
    public void init(HardwareMap hardwareMap) {
        left = hardwareMap.get(DcMotor.class, "lArmMotor");
        right = hardwareMap.get(DcMotor.class, "rArmMotor");

        left.setDirection(DcMotorSimple.Direction.FORWARD);
        right.setDirection(DcMotorSimple.Direction.REVERSE);

        lastTime = time.time();
        lastPosition = avgPosition();
    }

    @Override
    public void run(Gamepad driver, Gamepad manipulator) {}

    @Override
    public void telemetry(Telemetry telemetry) {
        telemetry.addData("rotator avg pos (rad/s)", "%.3f", avgPosition());
        telemetry.addData("rotator avg vel (rad/s/s)", "%.3f", avgVelocity());

        // TODO: remove
        double ff = feedforward.calculate(avgPosition(), avgVelocity());
        telemetry.addData("ff", "%.3f", ff);
    }

    public void set(double power) {
        double ff = feedforward.calculate(avgPosition(), avgVelocity());
        power += ff;

        left.setPower(power);
        right.setPower(power);
    }

    public double avgPosition() {
        final double tick2Rad = 1.0; // FIXME
        double l = left.getCurrentPosition() * tick2Rad;
        double r = right.getCurrentPosition() * tick2Rad;

        return ((l + r) / 2);
    }

    public double avgVelocity() {
        double pos = avgPosition();
        double tF = time.time();

        double dDisplacement = pos - lastPosition;
        double dT = tF - lastTime;

        double dV = dDisplacement / dT;

        lastPosition = pos;
        lastTime = tF;

        return dV;
    }

    public double kG(DoubleSupplier extenderEncoder) {
        return 0.0;
    }
}
