package factorys;

import instances.Explosion;
import instances.Projectile;

public class ExplotionFactory {

    public ExplotionFactory() {
    }

    /**
     * Crea una instancia de la clase "Explosion" con el tamaño especificado y el proyectil proporcionado.
     *
     * @param projectile El objeto de tipo "Projectile" que causó la explosión.
     * @return una instancia de la clase "Explosion" con los valores apropiados de tamaño y daño. Devuelve null si el tamaño de la explosión no coincide con ningún caso especificado.
     */
    public Explosion createExplotion(Projectile projectile) {
        switch (projectile.getModel()){
            case "SMALL":
                return new ExplosionSmall(projectile);
            case "MEDIUM":
                return new ExplotionMedium(projectile);
            case "BIG":
                return new ExplosionBig(projectile);
            default:
                return null;
        }
    }

    private static class ExplosionSmall extends Explosion {

        public ExplosionSmall(Projectile projectile) {
            super(projectile, 1, 50);
            setSpriteData(512,512,4,4);
        }
    }

    private static class ExplotionMedium extends Explosion{

        public ExplotionMedium(Projectile projectile) {
            super(projectile, 2, 70);
            setSpriteData(2048,1052,8,4);
        }
    }

    private static class ExplosionBig extends Explosion {

        public ExplosionBig(Projectile projectile) {
            super(projectile, 4, 100);
            setSpriteData(2048,1530,8,6);
        }
    }
}
