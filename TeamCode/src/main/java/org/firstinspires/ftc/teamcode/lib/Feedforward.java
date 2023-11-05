package org.firstinspires.ftc.teamcode.lib;

import java.util.function.DoubleSupplier;

public class Feedforward {

    public final double ks;
    public final double kg;
    public final double kv;
    public final double ka;

    public Feedforward(double ks, double kg,
                       double kv, double ka) {
        this.ks = ks;
        this.kg = kg;
        this.kv = kv;
        this.ka = ka;
    }

    public Feedforward(double ks, double kg, double kv) {
        this(ks, kg, kv, 0);
    }

    public double calculate(double positionRadians,
                            double velocityRadPerSec, double accelRadPerSecSquared) {
        return ks * Math.signum(velocityRadPerSec)
                + kg * Math.cos(positionRadians)
                + kv * velocityRadPerSec
                + ka * accelRadPerSecSquared;
    }

    public double calculate(double positionRadians, double velocity) {
        return calculate(positionRadians, velocity, 0);
    }

    public double maxAchievableVelocity(double maxVoltage, double angle, double acceleration) {
        // Assume max velocity is positive
        return (maxVoltage - ks - Math.cos(angle) * kg
                - acceleration * ka) / kv;
    }
}