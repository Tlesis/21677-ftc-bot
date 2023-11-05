package org.firstinspires.ftc.teamcode.teleop.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.lib.Subsystem;

public class IntakeSubsystem extends Subsystem {

    private DcMotor intakeMotor;

    @Override
    public void init(HardwareMap hardwareMap) {
        intakeMotor = hardwareMap.get(DcMotor.class, "intake");

        intakeMotor.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    @Override
    public void run(Gamepad driver, Gamepad manipulator) {
        boolean intake =  manipulator.a;
        boolean mod = manipulator.left_bumper;

        double speed = (intake) ? 1.0 : 0.0;
        speed *= (mod) ? -1 : 1;

        intakeMotor.setPower(speed);
    }

    @Override
    public void telemetry(Telemetry telemetry) {}
}
