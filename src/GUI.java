import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
TODO:
Images?
more upgrades that are unique
move cookie button to the middle
add grandmas somewhere
make upgrades unlockable based on previous upgrade

homework:
figure out how to move the center panel directly below the button panel

 */




public class GUI implements ActionListener {
    JFrame frame;
    JPanel buttonPanel;
    JPanel textPanel;
    JPanel fillerPanel;
    JLabel label;
    JLabel upgrade1Cost;
    JLabel upgrade2Cost;
    JButton cookieButton;
    JButton upgradeButton1;
    JButton upgradeButton2;
    JButton SuperUpgradeButton;
    Game game;
    ActionListener guiUpdate;
    Timer updateTimer;

    public GUI() {
        game = new Game();
        frame = new JFrame(); //Our Window
        buttonPanel = new JPanel();
        textPanel = new JPanel();
        fillerPanel = new JPanel();
        label = new JLabel("Cookies: ");
        upgrade1Cost = new JLabel("Upgrade Cost: " + game.upgrade1Cost);
        upgrade2Cost = new JLabel("Upgrade Cost: " + game.upgrade2Cost);
        cookieButton = new JButton("Cookie");
        upgradeButton1 = new JButton("Upgrade");
        upgradeButton2 = new JButton("Slow Cooker");
        SuperUpgradeButton = new JButton("Super Upgrade");
        cookieButton.addActionListener(e -> {
            game.cookieClick();
            label.setText("Cookies: " + game.cookieCounter);
        });
        upgradeButton1.addActionListener(e -> {
            game.upgradeButton1();
            label.setText("Cookies: " + game.cookieCounter);
        });
        upgradeButton2.addActionListener(e -> {
            game.upgradeButton2();
            label.setText("Cookies: " + game.cookieCounter);
        });
        //panel elements
        buttonPanel.add(cookieButton);
        buttonPanel.add(upgradeButton1);
        buttonPanel.add(upgradeButton2);
        textPanel.add(label);
        textPanel.add(upgrade1Cost);
        textPanel.add(upgrade2Cost);
        //panel properties
        buttonPanel.setBackground(Color.PINK);
        buttonPanel.setSize(400, 100);
        buttonPanel.setLayout(new GridLayout(1,4));
        textPanel.setBackground(Color.PINK);
        textPanel.setSize(400,100);
        textPanel.setLayout(new GridLayout(1,4));
        fillerPanel.setBackground(Color.PINK);

        frame.add(buttonPanel, BorderLayout.NORTH);
        fillerPanel.add(textPanel, BorderLayout.NORTH);
        frame.add(fillerPanel, BorderLayout.CENTER);

        //frame properties
        frame.setBackground(Color.BLACK);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setVisible(true);

        //timer go here
        guiUpdate = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
               label.setText("Cookies: " + game.cookieCounter);
               upgrade1Cost.setText("Upgrade Cost: " + game.upgrade1Cost);
               upgrade2Cost.setText("Upgrade Cost: " + game.upgrade2Cost);
               if (game.cookieCounter >= 200) {
                   buttonPanel.add(SuperUpgradeButton);
               }
                /*figure out the name of the label
                set the text of the label to something
                 */
            }
        };
        updateTimer = new Timer(500,guiUpdate);
        updateTimer.start();

    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}