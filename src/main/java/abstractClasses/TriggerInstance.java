package abstractClasses;

import factorys.ProjectileFactory;
import instances.Projectile;

import javax.swing.*;

public abstract class TriggerInstance extends DrawableInstance{
    protected JLabel ammunitionLabel, loadLabel;
    protected final String ammunitionType;
    protected double angle;
    protected int ammunition;
    protected int loadTime;
    protected final int startLoadTime;
    protected int cycle;
    protected boolean loaded = true;
    public TriggerInstance(String model, int x, int y, String ammunitionType, int ammunition, int loadTime) {
        super(model, x, y);
        this.ammunitionType = ammunitionType;
        this.ammunition = ammunition;
        this.loadTime = loadTime;
        this.startLoadTime = loadTime;
    }

    /**
     * Esto función es llamada para recargar
     */
    public void load() {
        if (ammunition > 0 && !loaded) {
            if (cycle >= 20) {
                loadTime--;
                cycle = 0;
                secondAction();
                if (loadTime <= 0) {
                    loaded = true;
                }
            }
            cycle++;
        }
    }

    /**
     * Crea y lanza un proyectil.
     *
     * @return el objeto de tipo Projectile que representa el proyectil lanzado
     */
    public Projectile fire() {
        if (ammunition > 0) {
            ammunition--;
            loaded = false;
            loadTime = startLoadTime;
            if (loadLabel != null) {
                loadLabel.setText("Munición: " + ammunition);
                if (ammunition < 1) {
                    ammunitionLabel.setText("Sin munición");
                }
            }
            return new ProjectileFactory().createProjectile(this);
        }
        return null;
    }

    /**
     * Es metodo se ejecuta cada vez que la instancia decrementa un segundo en su recarga
     */
    public void secondAction() {
        String string = "Cargado";
        if (loadTime > 0) {
            string = "Cargando: " + loadTime;
        }

        if (ammunitionLabel != null) {
            ammunitionLabel.setText(string);
        }
    };

    public double getRadians() {
        return Math.toRadians(angle);
    }

    public void setRadians(double angle) {
        this.angle = angle;
    }

    public int getAmmunition() {
        return ammunition;
    }

    public void setAmmunition(int ammunition) {
        this.ammunition = ammunition;
    }

    public boolean isLoaded() {
        return loaded;
    }

    public void setLoaded(boolean loaded) {
        this.loaded = loaded;
    }

    public int getLoadTime() {
        return loadTime;
    }

    public void setLoadTime(int loadTime) {
        this.loadTime = loadTime;
    }

    public String getAmmunitionType() {
        return ammunitionType;
    }

    public void setLabels(JLabel ammunitionLabel, JLabel loadLabel) {
        this.ammunitionLabel = ammunitionLabel;
        this.loadLabel = loadLabel;
    }
}
