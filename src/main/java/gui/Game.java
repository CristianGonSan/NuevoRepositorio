package gui;

import abstractClasses.Enemy;
import instances.Level;
import instances.Explosion;
import factorys.EnemyFactory;
import instances.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

public class Game extends JPanel {
    private Level level;
    private final Resources resources = Resources.getInstance();
    private final List<Enemy> enemies = new ArrayList<>();
    private final List<Projectile> projectiles = new ArrayList<>();
    private final List<Explosion> explosions = new ArrayList<>();

    private Cannon cannon;
    private Stuka stuka;

    private final ProjectileCalculator projectileCalculator = new ProjectileCalculator();

    private final EnemyFactory enemyFactory = new EnemyFactory();

    private int totalSeconds;
    private int ending;
    private boolean running = true;
    private boolean finalized = false;

    private final int cannonX, cannonY;
    private int targetX = -100, targetY;

    public Game(Level level) {
        this.level = level;
        this.cannon = new Cannon(level.getAmmunition(), 68, 399, 0.5);
        this.stuka = new Stuka(level.getAmmunitionStuka());
        this.cannonX = cannon.getX();
        this.cannonY = cannon.getY();
        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (stuka.isTarget() && running) {
                    super.mouseClicked(e);
                    targetX = e.getX();
                    targetY = e.getY();
                }
            }
        };
        addMouseListener(mouseAdapter);
        Thread thread = new Thread(projectileCalculator);
        timer.start();
        totalTimer.start();
        thread.start();
    }

    private final Timer timer = new Timer(50, e -> {
        SwingUtilities.invokeLater(this::repaint);
        calculate();
    });

    private final Timer totalTimer = new Timer(1000, e -> {
        totalSeconds++;

        if (totalSeconds >= 3 && level.hasNext()) {
            while (level.hasNext()) {
                String next = level.next();
                if (next.equals("delay")) {
                    break;
                }
                enemies.add(enemyFactory.createTank(next));
            }
        }

        if (ending == 0) {
            if (((cannon.getAmmunition() <= 0 && stuka.getAmmunition() <= 0) || enemies.size() == 0) && !level.hasNext()) {
                ending = totalSeconds + 4;
            }
        } else if (totalSeconds >= ending) {
            level.updatePoints();
            finalized = true;
            repaint();
            stop();
        }
    });

    private void calculate() {
        if (totalSeconds < 3) {
            return;
        }

        for (Enemy enemy : enemies) {
            enemy.avance();
        }

        for (Explosion explosion : explosions) {
            explosion.progress();
        }

        for (int i = explosions.size() - 1; i >= 0; i--) {
            if (!explosions.get(i).isOnScreen()) {
                explosions.remove(i);
            }
        }

        if (stuka.isOnScream()) {
            if (stuka.isProjectileOnScream()) {
                Projectile projectile = stuka.fire();
                projectiles.add(projectile);
            }
            stuka.avance();
        }

        cannon.load();
        stuka.load();
    }

    /**
     * Reanuda el juego
     */
    public void start() {
        if (!running && !finalized) {
            projectileCalculator.start();
            Thread thread = new Thread(projectileCalculator);
            running = true;
            timer.start();
            totalTimer.start();
            thread.start();
        }
    }

    /**
     * Pausa el juego
     */
    public void stop() {
        if (running) {
            running = false;
            projectileCalculator.stop();
            timer.stop();
            totalTimer.stop();
        }
    }

    public void setKey(KeyEvent e) {
        if (running) {
            switch (e.getKeyChar()) {
                case 'a':
                case 'A':
                    cannon.rotateLeft();
                    break;
                case 'd':
                case 'D':
                    cannon.rotateRight();
                    break;
                case KeyEvent.VK_SPACE:
                    if (cannon.isLoaded()) {
                        projectiles.add(cannon.fire());
                    }
                    break;
                case 't':
                case 'T':
                    if (stuka.isLoaded() && targetX > 0 && stuka.isTarget()) {
                        stuka.callAttack(targetX, targetY);
                    }
                    break;
                default:
                    break;
            }
        }
    }

    public Level getLevel() {
        return level;
    }

    public void setCannonLabels(JLabel ammunitionLabel, JLabel loadLabel) {
        cannon.setLabels(ammunitionLabel, loadLabel);
    }

    public void setStukaLabels(JLabel ammunitionLabel, JLabel loadLabel) {
        stuka.setLabels(ammunitionLabel, loadLabel);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;

        g.drawImage(resources.getBufferedImage("MAP"), 0, 0, null);

        for (Enemy enemy : enemies) {
            if (enemy.isInUse()) {
                g.drawImage(resources.getBufferedImage(enemy.getModel()), enemy.getX(), enemy.getY(), null);
            }
        }

        for (Projectile projectile : projectiles) {
            AffineTransform pro = AffineTransform.getTranslateInstance(projectile.getX(), projectile.getY());
            pro.rotate(projectile.getRadians(), 6, 3);
            pro.scale(0.5, 0.5);
            graphics2D.drawImage(resources.getBufferedImage(projectile.getModel()), pro, null);
        }

        double angle = cannon.getRadians();

        AffineTransform can = AffineTransform.getTranslateInstance(cannonX - 15, cannonY - 11);
        can.rotate(angle, cannon.getCannonCenterX(), cannon.getCannonCenterY());
        graphics2D.drawImage(resources.getBufferedImage(cannon.getModel()), can, null);

        AffineTransform sight = AffineTransform.getTranslateInstance(
                (int) (350 * Math.cos(angle)) + cannonX - 31,
                (int) (350 * Math.sin(angle)) + cannonY - 31);
        sight.rotate(angle, 31, 31);
        sight.scale(0.25, 0.25);
        graphics2D.drawImage(resources.getBufferedImage("SIGHT"), sight, null);

        if (!stuka.isInTarget()) {
            AffineTransform sight1 = AffineTransform.getTranslateInstance(targetX - 31, targetY - 31);
            sight1.scale(0.25, 0.25);
            graphics2D.drawImage(resources.getBufferedImage("SIGHT"), sight1, null);
        }

        for (Explosion explosion : explosions) {
            g.drawImage(resources.getBufferedImage(explosion.getModel()),
                    explosion.getX() - explosion.getExplosionRatio(),
                    explosion.getY() - explosion.getExplosionRatio(),
                    explosion.getX() + explosion.getExplosionRatio(),
                    explosion.getY() + explosion.getExplosionRatio(),
                    explosion.getImgX(), explosion.getImgY(),
                    explosion.getImgX() + explosion.getSubImageWidth(),
                    explosion.getImgY() + explosion.getSubImageHeigth(), this);
        }

        //Pintar los arboles
        g.drawImage(resources.getBufferedImage("TREES"), 0, 0, null);

        //Pintar stuka
        if (stuka.isOnScream()) {
            g.drawImage(resources.getBufferedImage("STUKA"), stuka.getX() - 58, stuka.getY() - 69, null);
        }

        if (totalSeconds < 3) {
            g.setColor(Color.white);
            g.setFont(new Font("Tahoma", Font.BOLD, 100));
            g.drawString("" + (3 - totalSeconds), 580, 420);
        } else if (finalized) {
            g.setColor(Color.white);
            g.setFont(new Font("Tahoma", Font.BOLD, 100));
            g.drawString("PARTIDA FINALIZADA!", 20, 420);
        }

        g.dispose();
    }

    private class ProjectileCalculator implements Runnable {
        private boolean running = true;

        /**
         * Crea una instancia que implementa Runnable, procure solo crear una instancia
         */
        public ProjectileCalculator() {
        }

        @Override
        public void run() {
            running = true;
            while (running) {
                for (Projectile projectile : projectiles) {
                    projectile.avance();

                    if (projectile.getTriggerInstanceModel().equals("STUKA")) {
                        if (projectile.getX() + 2 >= stuka.getTargetX()) {
                            targetX = -100;
                            stuka.setTarget(false);
                            stuka.setInTarget(false);
                            projectile.setCollided(true);

                            for (Enemy enemy1 : enemies) {
                                if (projectile.checkCollision(enemy1)) {
                                    break;
                                }
                            }

                            Explosion explosion = projectile.getExplosion();
                            explosions.add(explosion);

                            for (Enemy enemy1 : enemies) {
                                explosion.checkCollision(enemy1);
                            }
                        }

                    } else {
                        if (projectile.getX() > getWidth() || projectile.getX() < 0 || projectile.getY() > getHeight() || projectile.getY() < 0) {
                            projectile.setCollided(true);
                            continue;
                        }

                        for (Enemy enemy : enemies) {

                            if (enemy.isInUse() && projectile.checkCollision(enemy)) {
                                Explosion explosion = projectile.getExplosion();
                                explosions.add(explosion);
                                for (Enemy enemy1 : enemies) {
                                    if (enemy.isInUse()) {
                                        explosion.checkCollision(enemy1);
                                    }
                                }
                                break;
                            }

                        }
                    }
                }

                for (int i = enemies.size() - 1; i >= 0; i--) {
                    if (enemies.get(i).isInUse()) {
                        if (enemies.get(i).getHealth() <= 0) {
                            level.addPoints(enemies.get(i).getPoints());
                        }
                        enemies.remove(i);
                    }
                }

                for (int i = projectiles.size() - 1; i >= 0; i--) {
                    if (projectiles.get(i).isCollided()) {
                        projectiles.remove(i);
                    }
                }

                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        /**
         * Cambia el estado de running a false, lo que finaliza el while que mantiene al hilo ejecutandose
         */
        public void stop() {
            running = false;
        }

        /**
         * Cambia el estado de running a true, lo que permite que el while mantenga el hilo ejecutandose
         */
        public void start() {
            running = true;
        }
    }

    private class EnemyPoool {

        public EnemyPoool () {

        }

        public void addEnemy(String model) {
            for (Enemy enemy : enemies) {
                if (enemy.getModel().equals(model) && !enemy.isInUse()) {
                    //enemy.recet();
                    return;
                }
            }
            enemies.add(enemyFactory.createTank(model));
        }
    }
}
