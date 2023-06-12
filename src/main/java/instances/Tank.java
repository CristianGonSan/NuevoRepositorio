package instances;

import abstractClasses.Enemy;

public class Tank extends Enemy {
    private final int canonSize;
    public Tank(String model, int health, int points, int speed, int width, int height, int canonSize) {
        super(model, health, points, speed, width, height);
        this.canonSize = canonSize;
    }

    @Override
    public void avance() {
        x -= speed;
        if (x < -150) {
            inUse = true;
        }
        box.setLocation(x - canonSize, y);
    }

    @Override
    public void setX(int x) {
        this.x = x;
        this.box.setLocation(x - canonSize, y);
    }

    @Override
    public void setY(int y) {
        this.y = y;
        this.box.setLocation(x - canonSize, y);
    }
}
