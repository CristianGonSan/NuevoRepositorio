package gui;

import instances.Resources;

import javax.swing.*;
import java.awt.*;

public class Menu extends JPanel {
    private final Resources resources = Resources.getInstance();
    private Mediator mediator;
    public Menu(Mediator mediator) {
        this.mediator = mediator;
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setPreferredSize(new Dimension(1400,800));
        JButton jButton = new JButton("XDDDDDDDDDDDDDD");
        jButton.setPreferredSize(new Dimension(250,100));
        add(jButton);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(resources.getBufferedImage("WALLPAPER").getScaledInstance(1400,800, Image.SCALE_SMOOTH), 0, 0, this);
    }
}
