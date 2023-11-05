package org.firstinspires.ftc.teamcode.tests.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class MecanumButtonWheelTest extends OpMode {
    private DcMotor
            fr = null,
            fl = null,
            br = null,
            bl = null;


    @Override
    public void init() {
        fr = hardwareMap.get(DcMotor.class, "frontRight");
        fl = hardwareMap.get(DcMotor.class, "frontLeft");
        br = hardwareMap.get(DcMotor.class, "backRight");
        bl = hardwareMap.get(DcMotor.class, "backLeft");

        fr.setDirection(DcMotorSimple.Direction.REVERSE);
        bl.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    /**
     * Xbox/PS4 Button - Motor
     *   X / ▢         - Front Left
     *   Y / Δ         - Front Right
     *   B / O         - Rear  Right
     *   A / X         - Rear  Left
     *                                    The buttons are mapped to match the wheels spatially if you
     *                                    were to rotate the gamepad 45deg°. x/square is the front left
     *                    ________        and each button corresponds to the wheel as you go clockwise
     *                   / ______ \
     *     ------------.-'   _  '-..+              Front of Bot
     *              /   _  ( Y )  _  \                  ^
     *             |  ( X )  _  ( B ) |     Front Left   \    Front Right
     *        ___  '.      ( A )     /|       Wheel       \      Wheel
     *      .'    '.    '-._____.-'  .'       (x/▢)        \     (Y/Δ)
     *     |       |                 |                      \
     *      '.___.' '.               |          Back Left    \   Back Right
     *               '.             /             Wheel       \    Wheel
     *                \.          .'              (A/X)        \   (B/O)
     *                  \________/
     */
    @Override
    public void loop() {
        fl.setPower((gamepad1.x) ? 1 : 0);
        fr.setPower((gamepad1.y) ? 1 : 0);
        bl.setPower((gamepad1.a) ? 1 : 0);
        br.setPower((gamepad1.b) ? 1 : 0);
    }
}
