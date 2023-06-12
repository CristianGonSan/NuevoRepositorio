package instances;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Resources {
    private static Resources resources;
    private final Map<String, BufferedImage> map = new HashMap<>();

    private Resources() {
        loadResources();
    }

    /**
     * Este método devuelve una instancia de Resources.
     *
     * @return una instancia de Resources
     */
    public static Resources getInstance() {
        if (resources == null) {
            resources = new Resources();
        }
        return resources;
    }

    public BufferedImage getBufferedImage(String model) {
        if (map.containsKey(model)) {
            return map.get(model);
        }
        return map.get("NO FOUND");
    }

    private void loadResources() {
        try {
            //Recursos del mapa
            map.put("MAP", ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/image/map/map.png"))));
            map.put("TREES", ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/image/map/trees.png"))));
            map.put("WALLPAPER", ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/image/other/wallpaper.jpg"))));

            //Enemigos
            map.put("BT-7", ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/image/enemys/BT-7.png"))));
            map.put("T-34", ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/image/enemys/T-34.png"))));
            map.put("IS-2", ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/image/enemys/IS-2.png"))));
            map.put("KV-2", ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/image/enemys/KV-2.png"))));

            //Cañon, stuka y proyectiles
            map.put("CANNON", ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/image/cannon/cannon.png"))));
            map.put("STUKA", ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/image/cannon/stuka.png"))));
            map.put("SIGHT", ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/image/cannon/punto-de-mira.png"))));

            map.put("SMALL", ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/image/cannon/projectile-small.png"))));
            map.put("MEDIUM", ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/image/cannon/projectile-medium.png"))));
            map.put("BIG", ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/image/cannon/projectile-big.png"))));

            //Sprites
            map.put("SMALL-E", ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/image/sprites/explosion-small.png"))));
            map.put("MEDIUM-E", ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/image/sprites/explosion-medium.png"))));
            map.put("BIG-E", ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/image/sprites/explosion-big.png"))));

            map.put("AMMUNITI0N", ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/image/cannon/ammunition.png"))));

            map.put("NO FOUND", ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/image/other/confundido.png"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
