package org.firstinspires.ftc.teamcode.tests.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.drive.opmode.AutomaticFeedforwardTuner;

@TeleOp
public class DroneLaunch extends OpMode {

    private Servo droneServo = null;

    @Override
    public void init() {
        droneServo = hardwareMap.get(Servo.class, "droneServo");
    }

    @Override
    public void loop() {
        if (gamepad1.y) {
            droneServo.setPosition(0.0);
        } else if (gamepad1.a) {
            droneServo.setPosition(1.0);
        }
    }
}
