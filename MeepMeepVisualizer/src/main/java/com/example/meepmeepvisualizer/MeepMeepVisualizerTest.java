package com.example.meepmeepvisualizer;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;
import com.noahbres.meepmeep.core.entity.Entity;

public class MeepMeepVisualizerTest {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 13.14)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(22, 70, 270))
                                .splineToSplineHeading(new Pose2d(55, 40, Math.toRadians(0)), Math.toRadians(0))
                                .waitSeconds(1)
                                .strafeRight(30)
                                .lineToLinearHeading(new Pose2d(-65, 11, Math.toRadians(0)))
                                .waitSeconds(0.5)
                                .lineToLinearHeading(new Pose2d(0, 11, Math.toRadians(0)))
                                .lineToLinearHeading(new Pose2d(55, 40, Math.toRadians(0)))
                                .waitSeconds(0.2)
                                .lineToLinearHeading(new Pose2d(0, 11, Math.toRadians(0)))
                                .lineToLinearHeading(new Pose2d(-65, 11, Math.toRadians(0)))
                                .waitSeconds(0.2)
                                .lineToLinearHeading(new Pose2d(0, 11, Math.toRadians(0)))
                                .lineToLinearHeading(new Pose2d(55, 40, Math.toRadians(0)))
                                .build()
                );


        meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}