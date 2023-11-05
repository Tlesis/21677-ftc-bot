package org.firstinspires.ftc.teamcode.tests.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class Extender extends OpMode {

    private DcMotor extender = null;

    @Override
    public void init() {
        extender = hardwareMap.get(DcMotor.class, "extender");
    }

    @Override
    public void loop() {

    }
}
