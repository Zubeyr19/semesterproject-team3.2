package oceanCleanup.src.GUI;

import javafx.animation.AnimationTimer;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;

import java.io.File;

public class PlayerMove {

    private BooleanProperty wPressed = new SimpleBooleanProperty();
    private BooleanProperty aPressed = new SimpleBooleanProperty();
    private BooleanProperty sPressed = new SimpleBooleanProperty();
    private BooleanProperty dPressed = new SimpleBooleanProperty();

    private BooleanBinding keyPressed = wPressed.or(aPressed).or(sPressed).or(dPressed);


    private ImageView image;
    final private double playerHeight = 65;
    final private double playerWidth = 45;

    private AnchorPane scene;

    public void makeMovable(ImageView image, AnchorPane scene) {
        this.image = image;
        this.scene = scene;

        keyPressed.addListener(((observableValue, aBoolean, t1) -> {
            if (!aBoolean) timer.start();
            else timer.stop();
        }));
    }

    private int movementVariable = 10;
    AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(long timestamp) {
            if (wPressed.get()) image.setLayoutY(image.getLayoutY() - movementVariable);
            if (sPressed.get()) image.setLayoutY(image.getLayoutY() + movementVariable);
            if (aPressed.get()) image.setLayoutX(image.getLayoutX() - movementVariable);
            if (dPressed.get()) image.setLayoutX(image.getLayoutX() + movementVariable);
            playerAtBorder();
        }
    };

    public void onKeyPressedMovement(KeyCode code) {
        if (code == KeyCode.W) {
            if (!wPressed.get()) {
                File file = new File(getClass().getResource("player/walk_left.gif").getPath());
                image.setImage(new Image("file:" + file.getAbsolutePath()));
            }
            wPressed.set(true);
        }
        if (code == KeyCode.A) {
            if (!aPressed.get()) {
                File file = new File(getClass().getResource("player/walk_left.gif").getPath());
                image.setImage(new Image("file:" + file.getAbsolutePath()));
            }
            aPressed.set(true);
        }
        if (code == KeyCode.S) {
            if (!sPressed.get()) {
                File file = new File(getClass().getResource("player/walk_right.gif").getPath());
                image.setImage(new Image("file:" + file.getAbsolutePath()));
            }
            sPressed.set(true);
        }
        if (code == KeyCode.D) {
            if (!dPressed.get()) {
                File file = new File(getClass().getResource("player/walk_right.gif").getPath());
                image.setImage(new Image("file:" + file.getAbsolutePath()));
            }
            dPressed.set(true);
        }
    }

    public void onKeyReleasedMovement(KeyCode code) {
        if (code == KeyCode.W) {
            wPressed.set(false);
        }
        if (code == KeyCode.A) {
            aPressed.set(false);
        }
        if (code == KeyCode.S) {
            sPressed.set(false);
        }
        if (code == KeyCode.D) {
            dPressed.set(false);
        }
        if ((code == KeyCode.W) || (code == KeyCode.A)) {
            if (!wPressed.get() && !aPressed.get() && !sPressed.get() && !dPressed.get()) {
                File file = new File(getClass().getResource("player/stand_left.png").getPath());
                image.setImage(new Image("file:" + file.getAbsolutePath()));
            }
        } else if ((code == KeyCode.S) || (code == KeyCode.D)) {
            if (!wPressed.get() && !aPressed.get() && !sPressed.get() && !dPressed.get()) {
                File file = new File(getClass().getResource("player/stand_right.png").getPath());
                image.setImage(new Image("file:" + file.getAbsolutePath()));
            }
        }
    }


    public void playerAtBorder() {
        double leftBorder = -20;
        double rightBorder = scene.getWidth() - playerWidth;
        double bottomBorder = scene.getHeight() - playerHeight;
        double topBorder = -20;

        if (image.getLayoutX() <= leftBorder) {
            image.setLayoutX(leftBorder);
        }

        if (image.getLayoutX() >= rightBorder) {
            image.setLayoutX(rightBorder);
        }

        if (image.getLayoutY() <= topBorder) {
            image.setLayoutY(topBorder);
        }

        if (image.getLayoutY() >= bottomBorder) {
            image.setLayoutY(bottomBorder);
        }
    }

}
