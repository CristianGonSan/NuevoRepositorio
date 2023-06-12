package instances;

import java.io.*;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FileManager {
    private static FileManager fileManager;
    private final List<String> levelData = new ArrayList<>();
    private final List<String> levelPoints = new ArrayList<>();

    private FileManager() {
        readLevelsData();
        readLevelsPoints();
    }

    public static FileManager getInstance() {
        if (fileManager == null) {
            fileManager = new FileManager();
        }
        return fileManager;
    }

    private void readLevelsData() {
        File file;
        try {
            file = new File(Objects.requireNonNull(getClass().getResource("/files/levelsdata.txt")).toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                levelData.add(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readLevelsPoints() {
        File file;
        try {
            file = new File(Objects.requireNonNull(getClass().getResource("/files/levelspoints.txt")).toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader reader = new BufferedReader(fileReader);
            String line;
            while ((line = reader.readLine()) != null) {
                levelPoints.add(line);
                System.out.println("POINTS: " + line);
            }
            reader.close();
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateLevelPoints(int level, int points) {
        if (level < levelPoints.size()) {
            if (points > Integer.parseInt(levelPoints.get(level))) {
                levelPoints.set(level, String.valueOf(points));
                updatePoints();
            }
        } else {
            levelPoints.add(String.valueOf(points));
            updatePoints();
        }
    }

    private void updatePoints() {
        File file;
        try {
            file = new File(Objects.requireNonNull(getClass().getResource("/files/levelspoints.txt")).toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        try {
            FileWriter writer = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            System.out.println("SIZE: " + levelPoints.size());
            for (String points : levelPoints) {
                bufferedWriter.write(points + "\n");
                System.out.println("Puntos Escritos: " + points);
            }
            bufferedWriter.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getLevelData(int i) {
        return levelData.get(i);
    }
}
