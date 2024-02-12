package org.firstinspires.ftc.teamcode.teleop.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.lib.Subsystem;

public class ExtenderSubsystem extends Subsystem {
    private DcMotor extender;

    private static final double MIN_EXTEND = -300;
    private static final double MAX_EXTEND = -10000;

    public ExtenderSubsystem(TelemetryMode mode) {
        super(mode);
    }

    @Override
    public void init(HardwareMap hardwareMap) {
        extender = hardwareMap.get(DcMotor.class, "extender");
        extender.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    @Override
    public void run(Gamepad driver, Gamepad manipulator) {
        // if (manipulator.b && extender.getCurrentPosition() >= MAX_EXTEND)
        //     extender.setPower(-1);
        // else if (manipulator.x && extender.getCurrentPosition() <= MIN_EXTEND)
        //     extender.setPower(1);
        // else
            extender.setPower(0);
    }

    @Override
    public void telemetry(Telemetry telemetry) {
        telemetry.addData("Pos", extender.getCurrentPosition());
    }
}
