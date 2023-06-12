package instances;

import abstractClasses.TriggerInstance;
import interfaces.MobileInstance;

import javax.swing.*;

public class Stuka extends TriggerInstance implements MobileInstance {
    private int speed = 120;
    private boolean target = false;
    private boolean inTarget = false;
    private boolean onScream = false;
    private boolean projectileOnScream = false;

    private int targetX;
    private int targetY;

    public Stuka(int ammunition) {
        super("STUKA", -150, 399, "MEDIUM", ammunition, 5);
    }

    public void callAttack(int x, int y) {
        this.targetX = x;
        this.targetY = y;
        this.x = -150;
        this.y = y;
        this.target = true;
        timer.start();
    }

    private final Timer timer = new Timer(6000,e -> {
        onScream = true;
        projectileOnScream = true;
        stop();
    });

    private void stop() {
        timer.stop();
    }

    public JLabel getLabel() {
        return ammunitionLabel;
    }

    public void setLabel(JLabel jLabel) {
        this.ammunitionLabel = jLabel;
    }

    @Override
    public void avance() {
        x += speed;
        if (x > 1400) {
            onScream = false;
        }
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public boolean isOnScream() {
        return onScream;
    }

    public void setOnScream(boolean onScream) {
        this.onScream = onScream;
    }

    public int getTargetX() {
        return targetX;
    }

    public int getTargetY() {
        return targetY;
    }

    public boolean isTarget() {
        return !target;
    }

    public void setTarget(boolean target) {
        this.target = target;
    }

    public boolean isInTarget() {
        return inTarget;
    }

    public void setInTarget(boolean inTarget) {
        this.inTarget = inTarget;
    }

    public void setTargetX(int targetX) {
        this.targetX = targetX;
    }

    public void setTargetY(int targetY) {
        this.targetY = targetY;
    }

    public boolean isProjectileOnScream() {
        if (projectileOnScream) {
            projectileOnScream = false;
            return true;
        }
        return false;
    }
}
