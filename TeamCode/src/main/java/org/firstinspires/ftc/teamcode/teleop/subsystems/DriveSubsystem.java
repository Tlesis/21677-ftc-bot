package org.firstinspires.ftc.teamcode.teleop.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.lib.Subsystem;

public class DriveSubsystem extends Subsystem {
    private DcMotor fr;
    private DcMotor fl;
    private DcMotor br;
    private DcMotor bl;

    public DriveSubsystem(TelemetryMode mode) {
        super(mode);
    }

    @Override
    public void init(HardwareMap hardwareMap) {
        fr = hardwareMap.get(DcMotor.class, "frontRight");
        fl = hardwareMap.get(DcMotor.class, "frontLeft");
        br = hardwareMap.get(DcMotor.class, "backRight");
        bl = hardwareMap.get(DcMotor.class, "backLeft");

        fr.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    @Override
    public void run(Gamepad driver, Gamepad manipulator) {
        // get input
        double y = driver.left_stick_y;
        double x = -driver.left_stick_x;
        double theta = driver.right_stick_x;

        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(theta), 1);
        double frontRightPower = (y - x - theta) / denominator;
        double frontLeftPower = (y + x + theta) / denominator;
        double backRightPower = (y + x - theta) / denominator;
        double backLeftPower = (y - x + theta) / denominator;

        double mod = (driver.b) ? 2.5 : 1.0;
        fr.setPower(frontRightPower / mod);
        fl.setPower(frontLeftPower / mod);
        br.setPower(backRightPower / mod);
        bl.setPower(backLeftPower / mod);
    }

    @Override
    public void telemetry(Telemetry telemetry) {}

    public void drive(double forward, double strafe, double theta) {
        double denominator = Math.max(Math.abs(forward) + Math.abs(strafe) + Math.abs(theta), 1);
        double frontRightPower = (forward - strafe - theta) / denominator;
        double frontLeftPower = (forward + strafe + theta) / denominator;
        double backRightPower = (forward + strafe - theta) / denominator;
        double backLeftPower = (forward - strafe + theta) / denominator;

        fr.setPower(frontRightPower);
        fl.setPower(frontLeftPower);
        br.setPower(backRightPower);
        bl.setPower(backLeftPower);
    }

    public void stop() {
        this.drive(0.0, 0.0, 0.0);
    }
}
