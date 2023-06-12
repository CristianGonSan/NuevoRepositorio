package instances;

import abstractClasses.DrawableInstance;
import abstractClasses.Enemy;
import abstractClasses.TriggerInstance;
import factorys.ExplotionFactory;
import interfaces.MobileInstance;

public class Projectile extends DrawableInstance implements MobileInstance {
    private final int starX;
    private final int starY;
    private final double angle;
    private int damage;
    private int speed = 2;
    private int ratio = 25;
    private boolean collided = false;
    private final String triggerInstanceModel;

    /**
     * Crea una nueva instancia Projectile
     *
     * @param triggerInstance una instancia triggerInstance de donde el projectile sera disparado
     */
    public Projectile(TriggerInstance triggerInstance, int damage) {
        super(triggerInstance.getAmmunitionType(), triggerInstance.getX(), triggerInstance.getY());
        this.damage = damage;
        this.starX = triggerInstance.getX();
        this.starY = triggerInstance.getY();
        this.angle = triggerInstance.getRadians();
        this.triggerInstanceModel = triggerInstance.getModel();
    }

    /**
     * Cambia la ubicación en x y y del la instancia en función al angulo y el radio
     */
    public void avance() {
        x = ((int) (ratio * Math.cos(angle))) + starX;
        y = ((int) (ratio * Math.sin(angle))) + starY;
        ratio += speed;
    }

    /**
     * Verifica si un objeto proyectil se ha colisionado con un tanque.
     *
     * @param enemy El objeto Enemiy con el que se verifica la colisión.
     * @return true si el proyectil ha colisionado con el tanque, false en caso contrario.
     */
    public boolean checkCollision(Enemy enemy) {
        if (enemy.getBox().contains(x, y)) {
            enemy.setDamage(damage);
            collided = true;
            return true;
        }
        return false;
    }

    public Explosion getExplosion() {
        return new ExplotionFactory().createExplotion(this);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    /**
     * Indica el daño que este proyectil puede causar
     *
     * @return daño
     */
    public int getDamage() {
        return damage;
    }

    /**
     * Establese el daño que este proyectil puede causar
     *
     * @param damage daño
     */
    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public String getModel() {
        return model;
    }

    public double getRadians() {
        return angle;
    }

    public void setCollided(boolean collided) {
        this.collided = collided;
    }

    public boolean isCollided() {
        return collided;
    }

    public String getTriggerInstanceModel() {
        return triggerInstanceModel;
    }
}
