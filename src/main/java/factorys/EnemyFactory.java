package factorys;

import instances.Tank;

public class EnemyFactory {
    public static final String T29 = "BT-7";
    public static final String T34 = "T-34";
    public static final String IS2 = "IS-2";
    public static final String KV2 = "KV-2";

    public EnemyFactory() {
    }

    /**
     * Crea una intancia instances.Enemy con todos los paramestros de un modelo
     *
     * @param model el modelo del Enemigo a construir
     * @return instancia instances.Enemy del modelo especificado
     */
    public Tank createTank(String model) {
        switch (model) {
            case T29:
                return new Tank(model, 1, 1,4, 56, 25, 0);
            case T34:
                return new Tank(model, 2, 2,3, 67, 31, 5);
            case IS2:
                return new Tank(model, 3, 3,2, 70, 31, 30);
            case KV2:
                return new Tank(model, 4, 4,1, 66, 33, 2);
            default:
                return null;
        }
    }

}
