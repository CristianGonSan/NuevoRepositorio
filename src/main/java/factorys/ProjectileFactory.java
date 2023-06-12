package factorys;

import abstractClasses.TriggerInstance;
import instances.Projectile;

public class ProjectileFactory {
    public ProjectileFactory() {
    }

    /**
     * Crea un objeto Projectile basado en el tipo de munición especificado en la instancia del disparador.
     *
     * @param triggerInstance La instancia del disparador que contiene la información sobre el tipo de munición.
     * @return Un objeto Projectile de tipo Small, Medium o Big, dependiendo del tipo de munición en la instancia del disparador, o null si el tipo de munición no es reconocido.
     */
    public Projectile createProjectile(TriggerInstance triggerInstance) {
        switch (triggerInstance.getAmmunitionType()) {
            case "SMALL":
                return new ProjectileSmall(triggerInstance);
            case "MEDIUM":
                return new ProjectileMedium(triggerInstance);
            case "BIG":
                return new ProjectileBig(triggerInstance);
            default:
                return null;
        }
    }

    private static class ProjectileSmall extends Projectile {

        public ProjectileSmall(TriggerInstance triggerInstance) {
            super(triggerInstance, 1);
        }

        @Override
        public int getX() {
            return super.getX() - 2;
        }

        @Override
        public int getY() {
            return super.getY() - 2;
        }
    }

    private static class ProjectileMedium extends Projectile {

        public ProjectileMedium(TriggerInstance triggerInstance) {
            super(triggerInstance, 2);
        }

        @Override
        public int getX() {
            return super.getX() - 2;
        }

        @Override
        public int getY() {
            return super.getY() - 2;
        }
    }

    private static class ProjectileBig extends Projectile {

        public ProjectileBig(TriggerInstance triggerInstance) {
            super(triggerInstance, 3);
        }

        @Override
        public int getX() {
            return super.getX() - 2;
        }

        @Override
        public int getY() {
            return super.getY() - 2;
        }
    }
}
