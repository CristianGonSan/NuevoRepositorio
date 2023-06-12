package gui;

import instances.Level;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class GamePanel extends JPanel {
    private final Game game;
    private final Mediator mediator;

    public GamePanel(Level level, Mediator mediator) {
        this.game = new Game(level);
        InterfacePanel interfacePanel = new InterfacePanel();
        this.mediator = mediator;

        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setHgap(0);
        flowLayout.setVgap(0);

        setLayout(flowLayout);

        game.setPreferredSize(new Dimension(1200, 800));
        interfacePanel.setPreferredSize(new Dimension(300, 800));

        add(game);
        add(interfacePanel);
    }

    public void sendKey(KeyEvent e) {
        game.setKey(e);
    }

    private class InterfacePanel extends JPanel {

        public InterfacePanel() {
            Dimension dimension = new Dimension(300, 50);
            setBackground(Color.GRAY);
            setLayout(new FlowLayout());

            JLabel level = new JLabel("Nivel: " + game.getLevel().getLevelNumber());
            JLabel poins = new JLabel("Puntos: 0");
            JLabel cannonLabel1 = new JLabel("Cargado");
            cannonLabel1.setBorder(BorderFactory.createTitledBorder("Cañon"));
            JLabel cannonLabel2 = new JLabel("Munición: " + game.getLevel().getAmmunition());
            cannonLabel2.setBorder(BorderFactory.createTitledBorder("Cañon"));
            JLabel stukaLabel1 = new JLabel("Cargado");
            stukaLabel1.setBorder(BorderFactory.createTitledBorder("Stuka"));
            JLabel stukaLabel2 = new JLabel("Munición: " + game.getLevel().getAmmunitionStuka());
            stukaLabel2.setBorder(BorderFactory.createTitledBorder("Stuka"));

            JLabel[] jLabels = new JLabel[]{level, poins, cannonLabel1, cannonLabel2, stukaLabel1, stukaLabel2};

            for (JLabel jLabel : jLabels) {
                jLabel.setHorizontalAlignment(SwingConstants.CENTER);
                jLabel.setFont(new Font("Arial", Font.BOLD, 20));
                jLabel.setBackground(Color.white);
                jLabel.setOpaque(true);
                jLabel.setPreferredSize(dimension);
                add(jLabel);
            }

            JButton jButton1 = new JButton("Reanudar");
            JButton jButton2 = new JButton("Pausar");
            JButton jButton3 = new JButton("Ir al Menú");

            JButton[] jButtons = new JButton[]{jButton1, jButton2, jButton3};

            ModernButtonUI modernButtonUI = new ModernButtonUI();

            for (JButton jButton : jButtons) {
                jButton.setPreferredSize(dimension);
                jButton.setUI(modernButtonUI);
                jButton.setFocusable(false);
                add(jButton);
            }

            jButton1.addActionListener(start);
            jButton2.addActionListener(stop);
            jButton3.addActionListener(menu);

            game.setCannonLabels(cannonLabel1, cannonLabel2);
            game.setStukaLabels(stukaLabel1, stukaLabel2);
            game.getLevel().setLabel(poins);
        }

        private final ActionListener start = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.start();
            }
        };

        private final ActionListener stop = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.stop();
            }
        };

        private final ActionListener menu = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.stop();
                mediator.showMenu();
            }
        };

        private class ModernButtonUI extends BasicButtonUI {
            @Override
            public void installUI(JComponent c) {
                super.installUI(c);
                AbstractButton button = (AbstractButton) c;
                button.setOpaque(false);
                button.setBorderPainted(false);
            }

            @Override
            public void paint(Graphics g, JComponent c) {
                AbstractButton b = (AbstractButton) c;
                paintBackground(g, b, b.getModel().isPressed() ? 2 : 0);
                super.paint(g, c);
            }

            protected void paintBackground(Graphics g, JComponent c, int yOffset) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(c.getBackground().darker());
                g2.fillRect(0, yOffset, c.getWidth(), c.getHeight() - yOffset);
                g2.setColor(c.getBackground());
                g2.fillRect(0, 0, c.getWidth(), c.getHeight() - yOffset);
                g2.dispose();
            }
        }
    }

}
