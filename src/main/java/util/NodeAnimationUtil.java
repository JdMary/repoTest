package util;

import javafx.animation.*;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Node;
import javafx.util.Duration;

public class NodeAnimationUtil {
    static double currentX = 0;

    public static void resizeWidth(Node node, double width, double duration){
        Timeline timeline = new Timeline();
        KeyValue keyValue = new KeyValue(NodePropertyUtil.getNodeWidthProperty(node), width); // Final height
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(duration), keyValue);
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
    }

    public static void resizeHeight(Node node, double height, double duration){
        Timeline timeline = new Timeline();
        KeyValue keyValue = new KeyValue(NodePropertyUtil.getNodeHeightProperty(node), height); // Final height
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(duration), keyValue);
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
    }

    public static void scaleX(Node node, double xScale, double duration, XScalePivot pivot, Interpolator easing){
        if(duration > 0){
            ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(duration), node);
            scaleTransition.setToX(xScale);
            scaleTransition.setInterpolator(easing);

            Timeline timeline = new Timeline();
            KeyFrame keyFrame = new KeyFrame(Duration.millis(4), event -> {
                if(pivot == XScalePivot.LEFT) {
                    double currentScale = node.getScaleX();
                    node.setTranslateX((node.getBoundsInLocal().getWidth() * currentScale / 2.) - node.getBoundsInLocal().getWidth() / 2);
                }
                if(pivot == XScalePivot.RIGHT) {
                    double currentScale = node.getScaleX();
                    node.setTranslateX(-((node.getBoundsInLocal().getWidth() * currentScale / 2.) - node.getBoundsInLocal().getWidth() / 2));
                }
            });
            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.getKeyFrames().add(keyFrame);

            scaleTransition.setOnFinished(event -> timeline.stop());

            scaleTransition.play();
            timeline.play();

        }else{
            ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(0.001), node);
            scaleTransition.setToX(xScale);
            scaleTransition.setInterpolator(easing);
            scaleTransition.play();
        }

    }

    public static void scaleY(Node node, double yScale, double duration, YScalePivot pivot, Interpolator easing){
        ScaleTransition currentScaleTransition = (ScaleTransition) node.getProperties().get(AnimationType.SCALEY);
        Timeline currentTranslateTransition = (Timeline) node.getProperties().get(AnimationType.TRANSLATEY);
        if(currentScaleTransition != null || currentTranslateTransition !=null) return;
        if(duration > 0){
            ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(duration), node);
            scaleTransition.setToY(yScale);
            scaleTransition.setInterpolator(easing);
            node.getProperties().put(AnimationType.SCALEY, scaleTransition);

            Timeline timeline = new Timeline();
            node.getProperties().put(AnimationType.TRANSLATEY, timeline);

            KeyFrame keyFrame = new KeyFrame(Duration.millis(4), event -> {
                if(pivot == YScalePivot.TOP) {
                    double currentScale = node.getScaleY();
                    node.setTranslateY((node.getBoundsInLocal().getHeight() * currentScale / 2) - node.getBoundsInLocal().getHeight() / 2);
                }
                if(pivot == YScalePivot.BOTTOM) {
                    double currentScale = node.getScaleY();
                    node.setTranslateY(-((node.getBoundsInLocal().getHeight() * currentScale / 2) - node.getBoundsInLocal().getHeight() / 2));
                }
            });
            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.getKeyFrames().add(keyFrame);

            scaleTransition.setOnFinished(event -> {
                timeline.stop();
                node.getProperties().remove(AnimationType.TRANSLATEY);
                node.getProperties().remove(AnimationType.SCALEY);
            });
            timeline.play();
            scaleTransition.play();
        }
        else{
            ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(0.001), node);
            scaleTransition.setToY(yScale);
            scaleTransition.setInterpolator(easing);
            scaleTransition.play();
        }

    }


    public static void translateX(Node node, double xTranslate, double duration, Interpolator easing){
        TranslateTransition ongoingTranslation = (TranslateTransition) node.getProperties().get(AnimationType.TRANSLATEX);
        if(ongoingTranslation != null) return ;

        TranslateTransition translateTransition = new TranslateTransition();
        translateTransition.setNode(node);
        translateTransition.setByX(xTranslate);
        translateTransition.setInterpolator(easing);
        translateTransition.setOnFinished(e -> {
            node.getProperties().remove(AnimationType.TRANSLATEX);
        });
        node.getProperties().put(AnimationType.TRANSLATEX, translateTransition);
        if(duration > 0) {
            translateTransition.setDuration(Duration.seconds(duration));
            translateTransition.play();
        }else {
            translateTransition.setDuration(Duration.seconds(0.001));
            translateTransition.play();
        }
    }

    public static void translateY(Node node, double yTranslate, double duration, Interpolator easing){
        if(duration > 0) {
            TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(duration), node);
            translateTransition.setByY(yTranslate);
            translateTransition.setInterpolator(easing);
            translateTransition.play();
        }
        else{
            TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(0.001), node);
            translateTransition.setByY(yTranslate);
            translateTransition.setInterpolator(easing);
            translateTransition.play();
        }
    }

    public static void rotate(Node node, double degree, double duration, RotationReset reset, Interpolator easing){
        RotateTransition currentRotation = (RotateTransition) node.getProperties().get(AnimationType.ROTATE);
        if(currentRotation != null) return;
        final double trueDuration =  (reset == RotationReset.REVERSE) ? duration/2 : duration;

        if(duration > 0){
            RotateTransition rotateTransition = new RotateTransition(Duration.seconds(trueDuration), node);
            rotateTransition.setByAngle( degree);
            rotateTransition.setInterpolator(easing);

            node.getProperties().put(AnimationType.ROTATE, rotateTransition);

            if(reset != RotationReset.KEEP)
                rotateTransition.setOnFinished(event -> {
                    if(reset == RotationReset.REVERSE){
                        RotateTransition reverse = new RotateTransition(Duration.seconds(trueDuration), node);
                        reverse.setByAngle(-degree);
                        reverse.setInterpolator(easing);
                        reverse.setOnFinished(e -> {
                            node.getProperties().remove(AnimationType.ROTATE);
                        });
                        node.getProperties().put(AnimationType.ROTATE, reverse);
                        reverse.play();
                    }
                    if(reset == RotationReset.RESET){
                        node.getProperties().remove(AnimationType.ROTATE);
                        node.setRotate(node.getRotate() - degree);
                    }
                });
            rotateTransition.play();
        }else{
            node.setRotate(node.getRotate() + degree);
        }
    }
    public static void jumpAnimation(Node node, String function, double startX, double endX, int maxJumpNumber, double duration, AnimationAcceleration acceleration){
        TranslateTransition tr = (TranslateTransition) node.getProperties().get(AnimationType.TRANSLATE);
        AnimationStatus as = (AnimationStatus) node.getProperties().get("translateStatus");
        if (tr != null) {
            if(tr.getStatus() == Animation.Status.RUNNING && as == AnimationStatus.TRANSLATE_RUNNING) return;
        }
        if(as == AnimationStatus.TRANSLATE_RECOVERING) stopJumping(node, false, 0);
        node.getProperties().put("translateStatus", AnimationStatus.TRANSLATE_RUNNING);
        if(duration <= 0) return ;
        TranslateTransition recovery = new TranslateTransition();
        TranslateTransition transition = new TranslateTransition();
        TranslateTransition reverse = new TranslateTransition();

        double currentTranslate = node.getTranslateY();
        recovery.setNode(node);
        recovery.setToY(currentTranslate);
        recovery.setOnFinished(e -> {
            node.getProperties().remove(AnimationType.TRANSLATE);
            node.getProperties().remove(AnimationType.TRANSLATE_RECOVERY);
            node.getProperties().put("translateStatus", AnimationStatus.TRANSLATE_IDLE);
        });

        node.getProperties().put(AnimationType.TRANSLATE_RECOVERY, recovery);

        if(maxJumpNumber > 0) {
            double speedingFactor = 0;
            if(acceleration == AnimationAcceleration.ACCELERATING)
                speedingFactor = 0.88;
            else if(acceleration == AnimationAcceleration.SLOWING)
                speedingFactor = 1.12;
            else speedingFactor = 1;

            double sum = 0;

            // Calculate the sum of the series
            for (int i = 0; i < maxJumpNumber; i++) {
                sum += Math.pow(speedingFactor, i);
            }
            // Allocate time to each value
            double factor = duration / sum;

            SimpleDoubleProperty[] durations = new SimpleDoubleProperty[maxJumpNumber];
            SimpleDoubleProperty[] functionOutputs = new SimpleDoubleProperty[maxJumpNumber];

            SimpleIntegerProperty currentPoint = new SimpleIntegerProperty(0);

            for (int i = 0; i < maxJumpNumber; i++) {
                durations[i] = new SimpleDoubleProperty(Math.pow(speedingFactor, i) * factor);
                double x = startX + (Math.abs(endX - startX) / maxJumpNumber) * (i);
                functionOutputs[i] = new SimpleDoubleProperty(MathUtil.evaluateMathFunction(function, x));
            }

            recovery.setDuration(Duration.seconds(durations[durations.length - 1].get()));

            transition.setDuration(Duration.seconds(durations[0].get()));
            transition.setNode(node);
            transition.setByY(- functionOutputs[0].get());

            reverse.setDuration(Duration.seconds(durations[0].get()));
            reverse.setNode(node);
            reverse.setByY(functionOutputs[0].get());

            transition.setOnFinished(e -> {
                reverse.play();
                node.getProperties().put(AnimationType.TRANSLATE, reverse);
            });

            reverse.setOnFinished(e -> {
                System.out.println(currentPoint);
                if(currentPoint.get() < maxJumpNumber) {
                    transition.setByY(- functionOutputs[currentPoint.get()].get());
                    transition.setDuration(Duration.seconds(durations[currentPoint.get()].get()));

                    reverse.setByY(functionOutputs[currentPoint.get()].get());
                    reverse.setDuration(Duration.seconds(durations[currentPoint.get()].get()));

                    transition.play();
                    node.getProperties().put(AnimationType.TRANSLATE, transition);
                    currentPoint.set(currentPoint.get() + 1);
                }
                else {
                    node.getProperties().remove(AnimationType.TRANSLATE);
                }

            });

        }

        else {
            transition.setDuration(Duration.seconds(duration / 2));
            transition.setNode(node);
            transition.setByY(- MathUtil.evaluateMathFunction(function, startX));


            reverse.setDuration(Duration.seconds(duration / 2));
            reverse.setNode(node);
            reverse.setByY(MathUtil.evaluateMathFunction(function, startX));



            transition.setOnFinished(e -> {
                reverse.play();
                node.getProperties().put(AnimationType.TRANSLATE, reverse);
            });

            reverse.setOnFinished(e -> {
                transition.play();
                node.getProperties().put(AnimationType.TRANSLATE, transition);
            });
        }

        transition.play();

        node.getProperties().put(AnimationType.TRANSLATE, transition);
    }

    public static void stopJumping(Node node, boolean recovery, double recoveryTime){
        TranslateTransition translateTransition = (TranslateTransition) node.getProperties().get(AnimationType.TRANSLATE);
        TranslateTransition recoveryTransition = (TranslateTransition) node.getProperties().get(AnimationType.TRANSLATE_RECOVERY);
        AnimationStatus as = (AnimationStatus) node.getProperties().get("translateStatus");
        if(translateTransition != null){
            node.getProperties().put("translateStatus", AnimationStatus.TRANSLATE_RECOVERING);
            if(recovery){
                translateTransition.stop();
                node.getProperties().put(AnimationType.TRANSLATE, recoveryTransition);
                recoveryTransition.setDuration(Duration.seconds(recoveryTime));
                recoveryTransition.play();

            } else{
                translateTransition.stop();
                node.setTranslateY(0);
                node.getProperties().remove(AnimationType.TRANSLATE);
                node.getProperties().remove(AnimationType.TRANSLATE_RECOVERY);
            }
        }
    }
    public static void fadeInOut(Node node, double opacity, double duration, Interpolator easing){
        if(duration > 0) {
            FadeTransition fadeTransition = new FadeTransition(Duration.seconds(duration), node);
            fadeTransition.setToValue(opacity);
            fadeTransition.setInterpolator(easing);
            fadeTransition.play();
        }else{
            FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.001), node);
            fadeTransition.setToValue(opacity);
            fadeTransition.setInterpolator(easing);
            fadeTransition.play();
        }
    }


    public static void oscillate(Node node, double oscillationDuration, int oscillations, double startAngle, double finishAngle, double recoveryDuration){
        final double initialRotate = node.getRotate();
        RotateTransition oscillationTransition = (RotateTransition) node.getProperties().get(AnimationType.OSCILLATION);
        RotateTransition rotateTransition = (RotateTransition) node.getProperties().get(AnimationType.ROTATE);
        if (oscillationTransition != null || rotateTransition != null) return;

        SimpleIntegerProperty cycles = new SimpleIntegerProperty(1 );
        double duration = (oscillations > 0) ? oscillationDuration / oscillations / 2 : oscillationDuration / 2;
        RotateTransition r1 = new RotateTransition(Duration.seconds(duration ), node);
        RotateTransition r2 = new RotateTransition(Duration.seconds(duration ), node);
        RotateTransition r3 = new RotateTransition(Duration.seconds(recoveryDuration), node);

        r1.setToAngle(startAngle);
        r2.setToAngle(finishAngle);
        r3.setToAngle(initialRotate);

        r1.setInterpolator(Interpolator.EASE_BOTH);
        r2.setInterpolator(Interpolator.EASE_BOTH);
        r3.setInterpolator(Interpolator.EASE_BOTH);


        node.getProperties().put(AnimationType.OSCILLATION_RECOVERY, r3);

        r3.setOnFinished(e -> {
            node.getProperties().remove(AnimationType.OSCILLATION);
            node.getProperties().remove(AnimationType.OSCILLATION_RECOVERY);
        });


        r1.setOnFinished(e -> {
            node.getProperties().put(AnimationType.OSCILLATION, r2);
            r2.play();
        });

        r2.setOnFinished(e -> {
            if(oscillations <= 0){
                r1.play();
                node.getProperties().put(AnimationType.OSCILLATION, r1);
            }
            else if(cycles.get() < oscillations) {
                r1.play();
                node.getProperties().put(AnimationType.OSCILLATION, r1);
                cycles.set(cycles.get() + 1);
            }
            else {
                r3.play();
                node.getProperties().put(AnimationType.OSCILLATION, r3);
                cycles.set(1);
            }
        });


        node.getProperties().put(AnimationType.OSCILLATION, r1);
        r1.play();

    }

    public static void stopOscillating(Node node, boolean recovery){
        RotateTransition rotateTransition = (RotateTransition) node.getProperties().get(AnimationType.OSCILLATION);
        RotateTransition recoveryTransition = (RotateTransition) node.getProperties().get(AnimationType.OSCILLATION_RECOVERY);
        if(rotateTransition == null) return;
        if(rotateTransition.getStatus() == Animation.Status.RUNNING){
            rotateTransition.stop();
            if(recovery) {
                recoveryTransition.play();
                node.getProperties().put(AnimationType.OSCILLATION, recoveryTransition);
            }
            else {
                node.setRotate(recoveryTransition.getToAngle());
                node.getProperties().remove(AnimationType.OSCILLATION);
                node.getProperties().remove(AnimationType.OSCILLATION_RECOVERY);
            }
        }
    }
}
