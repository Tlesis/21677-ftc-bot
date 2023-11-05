package org.firstinspires.ftc.teamcode.tests.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.lib.Feedforward;

@TeleOp
public class ArmFF extends OpMode {

    private double ks = 0;
    private double kg = 0;
    private double kv = 0;
    private double ka = 0;

    private ElapsedTime timer = new ElapsedTime(ElapsedTime.Resolution.SECONDS);
    private double lastTime = 0;

    private DcMotor leftArmMotor = null;
    private DcMotor  rightArmMotor = null;

    private Feedforward feedforward = new Feedforward(ks, kg, kv, ka);

    @Override
    public void init() {
        leftArmMotor = hardwareMap.get(DcMotor.class, "lArmMotor");
        rightArmMotor = hardwareMap.get(DcMotor.class, "rArmMotor");

        leftArmMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        rightArmMotor.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    @Override
    public void loop() {
        double feed = feedforward.calculate(getAvgRadPosition(), getAvgRadVelocity());
    }

    private double getAvgRadVelocity() {
        final double ticksToRadsPerSecConversion = 1;

        double position = getAvgRadPosition();
        
        double velocity = position / (lastTime - timer.time());
        lastTime = timer.time();

        return velocity;

    }

    private double getAvgRadPosition() {
        final double ticksToRadsConversion = 1;

        double total = leftArmMotor.getCurrentPosition() + rightArmMotor.getCurrentPosition();
        double avg = total / 2.0;

        return avg * ticksToRadsConversion;
    }
}
