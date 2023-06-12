package gui;

import instances.Level;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Window extends JFrame {
    private final JPanel jPanel = new JPanel();
    private final Level level = new Level(0);

    private final Mediator mediator = new Mediator();
    private final Menu menu = new Menu(mediator);
    private GamePanel gamePanel;

    public Window() {
        mediator.setWindow(this);
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setHgap(0);
        flowLayout.setVgap(0);

        gamePanel = new GamePanel(level, mediator);

        jPanel.setLayout(flowLayout);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        getContentPane().add(jPanel);
        addKeyListener(keyListener);

        //jPanel.add(menu);
        jPanel.add(gamePanel);

        setFocusable(true);

        pack();
        setLocationRelativeTo(null);
    }

    public void showMenu() {
        jPanel.removeAll();
        jPanel.add(menu);
        pack();
    }

    public void showGame() {
        jPanel.removeAll();
    }

    KeyListener keyListener = new KeyListener() {
        @Override
        public void keyTyped(KeyEvent e) {
            if (gamePanel != null) {
                gamePanel.sendKey(e);
            }
        }

        @Override
        public void keyPressed(KeyEvent e) {

        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    };

    public static void main(String[] args) {
        Window window = new Window();
        window.setVisible(true);
    }
}
