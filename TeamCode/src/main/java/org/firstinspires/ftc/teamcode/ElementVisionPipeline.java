package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvPipeline;
import java.util.ArrayList;
import org.firstinspires.ftc.teamcode.OpenCVMaster;

@Config
public class ElementVisionPipeline extends OpenCvPipeline {
    //backlog or accumulation of frames that attain mean to regress elicited noise
    ArrayList<double[]> frameList;
    //public static due to dashboard tuning
    int avgCr;
    int avgCb;
    int avgY;
    static final Scalar BLUE = new Scalar(0,0,255);



    public ElementVisionPipeline() {
        frameList = new ArrayList<>();
    }

    @Override
    public Mat processFrame(Mat input) {

        if (input.empty()) {
            return input;
        }

        /*
         * Points which actually define the sample region rectangles, derived from above values
         *
         * Example of how points A and B work to define a rectangle
         *
         *   ------------------------------------
         *   | (0,0) Point A                    |
         *   |                                  |
         *   |                                  |
         *   |                                  |
         *   |                                  |
         *   |                                  |
         *   |                                  |
         *   |                  Point B (70,50) |
         *   ------------------------------------
         *
         */
        final Point TOPLEFT_ANCHOR_POINT = new Point(125,125);
        final int REGION_WIDTH = 50;
        final int REGION_HEIGHT = 50;
        Point region1_pointA = new Point(
                TOPLEFT_ANCHOR_POINT.x,
                TOPLEFT_ANCHOR_POINT.y);
        Point region1_pointB = new Point(
                TOPLEFT_ANCHOR_POINT.x + REGION_WIDTH,
                TOPLEFT_ANCHOR_POINT.y + REGION_HEIGHT);

        Mat mat = new Mat();
        Mat Cb = new Mat();
        Mat Cr = new Mat();
        Mat Y = new Mat();
        Mat region1_Cb;
        Mat region1_Cr;
        Mat region1_Y;

        //matrix is converted to YCrCb value
        Imgproc.cvtColor(input, mat, Imgproc.COLOR_RGB2YCrCb);
        // Extract Channels
        Core.extractChannel(mat, Y, 0);
        Core.extractChannel(mat, Cr, 1);
        Core.extractChannel(mat, Cb, 2);

        region1_Cb = Cb.submat(new Rect(region1_pointA, region1_pointB));
        region1_Cr = Cr.submat(new Rect(region1_pointA, region1_pointB));
        region1_Y = Y.submat(new Rect(region1_pointA, region1_pointB));

        avgCr = (int) Core.mean(region1_Cr).val[0];
        avgCb = (int) Core.mean(region1_Cb).val[0];
        avgY = (int) Core.mean(region1_Y).val[0];
        Imgproc.rectangle(
                input, // Buffer to draw on
                region1_pointA, // First point which defines the rectangle
                region1_pointB, // Second point which defines the rectangle
                BLUE, // The color the rectangle is drawn in
                2); // Thickness of the rectangle lines

        return mat;
    }


    public int getAvgCb() {
        return avgCb;
    }

    public int getAvgCr() {
        return avgCr;
    }

    public int getAvgY() {
        return avgY;
    }


    public int getCrCbDiff() {
        int tolerance = 0;
        int d = getAvgCb() - getAvgCr();
        if (d < -tolerance) return -1; // Red
        return 1; // Blue

    }
}




