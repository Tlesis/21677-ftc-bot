package org.firstinspires.ftc.teamcode.teleop.subsystems;

import android.annotation.SuppressLint;
import android.util.Size;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.teamcode.lib.Subsystem;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.tfod.TfodProcessor;

import java.util.List;

public class VisionSubsystem extends Subsystem {

    private VisionPortal visionPortal;
    private TfodProcessor tfod;

    private final String[] LABELS = {
        "RedCerberusRight",
        "RedCerberusMid",
        "RedCerberusLeft",
        "BlueCerberusLeft",
        "BlueCerberusRight",
        "BlueCerberus"
    };

    @SuppressLint("SdCardPath")
    private static final String TFOD_MODEL_FILE = "/sdcard/FIRST/tflitemodels/HeadHeadHeadDogDetector.tflite";

    public VisionSubsystem(TelemetryMode mode) {
        super(mode);
    }

    @Override
    public void init(HardwareMap hardwareMap) {
        tfod = new TfodProcessor.Builder()
                .setModelFileName(TFOD_MODEL_FILE)
                .setModelLabels(LABELS)
                .setIsModelQuantized(true)
                .setModelInputSize(969)
                .setModelAspectRatio(1)
                .build();

        tfod.setMinResultConfidence(0.75f);

        visionPortal = new VisionPortal.Builder()
            .setCamera(hardwareMap.get(WebcamName.class, "Webcam 1"))
            .setCameraResolution(new Size(320, 320))
            .enableLiveView(true)
            .setAutoStopLiveView(true)
            .addProcessor(tfod)
            .build();
    }

    @Override
    public void run(Gamepad driver, Gamepad manipulator) {}

    @Override
    public void telemetry(Telemetry telemetry) {
        List<Recognition> currentRecognitions = tfod.getRecognitions();
        telemetry.addData("# Objects Detected", currentRecognitions.size());

        for (Recognition recognition : currentRecognitions) {
            double x = (recognition.getLeft() + recognition.getRight()) / 2 ;
            double y = (recognition.getTop()  + recognition.getBottom()) / 2 ;

            telemetry.addData(""," ");
            telemetry.addData("Image", "%s (%.0f %% Conf.)", recognition.getLabel(), recognition.getConfidence() * 100);
            telemetry.addData("- Position", "%.0f / %.0f", x, y);
            telemetry.addData("- Size", "%.0f x %.0f", recognition.getWidth(), recognition.getHeight());
        }
    }
}
