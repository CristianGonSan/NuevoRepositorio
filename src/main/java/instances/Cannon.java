package instances;

import abstractClasses.TriggerInstance;

import javax.swing.*;

public class Cannon extends TriggerInstance {
    private final double sensitivity;

    public Cannon(int ammunition, int x, int y, double sensitivity) {
        super("CANNON", x, y, "SMALL", ammunition, 4);
        this.sensitivity = sensitivity;
    }

    public int getX() {
        return super.getX();
    }

    public int getY() {
        return super.getY();
    }

    public void rotateLeft() {
        angle -= sensitivity;
    }

    public void rotateRight() {
        angle += sensitivity;
    }

    public int getCannonCenterX() {
        return 17;
    }

    public int getCannonCenterY() {
        return 12;
    }

    public JLabel getjLabel() {
        return ammunitionLabel;
    }

    public void setjLabel(JLabel jLabel) {
        this.ammunitionLabel = jLabel;
    }
}
