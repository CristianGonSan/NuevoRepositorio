package abstractClasses;

import interfaces.MobileInstance;

import java.awt.*;

public abstract class Enemy extends DrawableInstance implements MobileInstance {
    protected int health;
    protected final int points;
    protected int speed;
    protected final int width;
    protected final int height;
    protected final Rectangle box;
    protected boolean inUse = false;

    public Enemy(String model, int health, int points, int speed, int width, int height) {
        super(model, 1300, 100 + (int) ((Math.random() * 600)));
        this.health = health;
        this.points = points;
        this.speed = speed;
        this.width = width;
        this.height = height;
        this.box = new Rectangle(width, height);
    }

    /**
     * Decrementa la salud actual en el valor especificado de daño y devuelve un valor booleano
     * indicando si la salud ha alcanzado o superado 0.
     *
     * @param damage El valor de daño a restar de la salud actual.
     */
    public void setDamage(int damage) {
        health -= damage;
        if (health <= 0) {
            inUse = true;
        }
    }

    public String getModel() {
        return model;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getX() {
        return x;
    }

    public abstract void setX(int x);

    public int getY() {
        return y;
    }

    public abstract void setY(int y);

    public Rectangle getBox() {
        return box;
    }

    public void setInUse(boolean inUse) {
        this.inUse = inUse;
    }

    public boolean isInUse() {
        return inUse;
    }

    public int getPoints() {
        return points;
    }
}
