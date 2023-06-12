package instances;

import abstractClasses.DrawableInstance;
import abstractClasses.Enemy;
import instances.Projectile;

public class Explosion extends DrawableInstance {
    protected final int explosionRatio;
    protected int imgX;
    protected int imgY;
    protected int imgWidth;
    protected int imgHeigth;
    protected int subImageWidth;
    protected int subImageHeigth;
    protected boolean onScreen = true;
    protected int damage;

    public Explosion(Projectile projectile, int damage, int explosionRatio) {
        super(projectile.getModel() + "-E", projectile.getX(), projectile.getY());
        this.explosionRatio = explosionRatio;
        this.damage = damage;
    }

    public void checkCollision(Enemy enemy) {
        double enemyX = enemy.getBox().getX();
        double enemyY = enemy.getBox().getY();

        double enemyWidth = enemy.getBox().getWidth();
        double enemyHeight = enemy.getBox().getHeight();

        double closestX = x;

        if (closestX < enemyX) {
            closestX = enemyX;
        } else if (closestX > enemyX + enemyWidth) {
            closestX = enemyX + enemyWidth;
        }

        double closestY = y;

        if (closestY < enemyY) {
            closestY = enemyY;
        } else if (closestY > enemyY + enemyHeight) {
            closestY = enemyY + enemyHeight;
        }

        if (Math.sqrt(Math.pow(x - closestX, 2) + Math.pow(y - closestY, 2)) < explosionRatio - 20) {
            enemy.setDamage(damage);
        }
    }

    public void progress() {
        if (imgX <= imgWidth - subImageWidth * 2) {
            imgX += subImageWidth;
        } else if (imgY <= imgHeigth - subImageHeigth * 2) {
            imgX = 0;
            imgY += subImageHeigth;
        } else {
            onScreen = false;
        }
    }

    public void setSpriteData(int width, int height, int widthParts, int heightParts) {
        imgWidth = width;
        imgHeigth = height;
        subImageWidth = width / widthParts;
        subImageHeigth = height / heightParts;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getImgX() {
        return imgX;
    }


    public int getImgY() {
        return imgY;
    }


    public int getExplosionRatio() {
        return explosionRatio;
    }

    public boolean isOnScreen() {
        return onScreen;
    }


    public String getModel() {
        return model;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getImgWidth() {
        return imgWidth;
    }

    public int getImgHeigth() {
        return imgHeigth;
    }

    public int getSubImageWidth() {
        return subImageWidth;
    }

    public int getSubImageHeigth() {
        return subImageHeigth;
    }
}
