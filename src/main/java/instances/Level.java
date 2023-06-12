package instances;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Level {
    private static final FileManager fileManager = FileManager.getInstance();
    private JLabel jLabel;
    private final List<String> data = new ArrayList<>();
    private final int levelNumber;
    private int totalPoints;
    private final String instructions;
    private int points;
    private int ammunition;
    private int ammunitionStuka;
    private int index;

    public Level(int levelNumber) {
        this.levelNumber = levelNumber;
        this.instructions = fileManager.getLevelData(levelNumber);
        interpret(instructions);
    }

    private void interpret(String instruction) {
        int start = 0;
        int end = 0;

        String type = "";

        for (int i = 0; i < instruction.length(); i++) {
            switch (instruction.charAt(i)) {
                case '$':
                    start = i;
                    break;
                case '=':
                    end = i;
                    type = instruction.substring(start + 1, end);
                    break;
                case ';':
                    insert(type, instruction.substring(end + 1, i));
                    break;
                case ',':
                    start = i;
                    insert(type, instruction.substring(end + 1, i));
                    end = i;
                    break;
            }
        }

        for (String data : data)
            System.out.println("Data: " + data);
    }

    private void insert(String variable, String value) {
        switch (variable.toLowerCase()) {
            case "enemy":
                data.add(value);
                break;
            case "delay":
                for (int i = 0; i < Integer.parseInt(value); i++) {
                    data.add("delay");
                }
                break;
            case "ammunition":
                ammunition = Integer.parseInt(value);
                break;
            case "totalpoints":
                totalPoints = Integer.parseInt(value);
                break;
            case "ammunitionstuka":
                ammunitionStuka = Integer.parseInt(value);
                break;
        }
    }

    public int getLevelNumber() {
        return levelNumber;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public String getInstructions() {
        return instructions;
    }

    public void addPoints(int points) {
        this.points += points;
        if (jLabel != null) {
            jLabel.setText("Puntos: " + this.points);
        }
    }

    public int getPoints() {
        return points;
    }

    public boolean hasNext() {
        return index < data.size();
    }

    public String next() {
        index++;
        return data.get(index - 1);
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getAmmunition() {
        return ammunition;
    }

    public void setAmmunition(int ammunition) {
        this.ammunition = ammunition;
    }

    public int getAmmunitionStuka() {
        return ammunitionStuka;
    }

    public void setAmmunitionStuka(int ammunitionStuka) {
        this.ammunitionStuka = ammunitionStuka;
    }

    public JLabel getLabel() {
        return jLabel;
    }

    public void setLabel(JLabel jLabel) {
        this.jLabel = jLabel;
    }

    public void updatePoints() {
        fileManager.updateLevelPoints(levelNumber, points);
    }
}
