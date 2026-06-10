import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
TODO:
Images?
move cookie button to the middle
make button turn red when unavailable

 */




public class GUI implements ActionListener {
    JFrame frame;
    JPanel buttonPanel;
    JPanel textPanel;
    JPanel fillerPanel;
    JPanel button2Panel;
    JLabel label;
    JLabel upgrade1Cost;
    JLabel upgrade2Cost;
    JLabel comboCost;
    JLabel discountCost;
    JLabel criticalCost;
    JLabel goldenCost;
    JLabel printerCost;
    JLabel insuranceCost;
    JButton cookieButton;
    JButton upgradeButton1;
    JButton upgradeButton2;
    JButton SuperUpgradeButton;
    JButton comboButton;
    JButton discountButton;
    JButton criticalButton;
    JButton goldenPopUp;
    JButton printerButton;
    JButton insuranceButton;
    Game game;
    ActionListener guiUpdate;
    Timer updateTimer;
    Timer goldenSpawnTime;
    Timer goldenLifeTime;
    private boolean goldenVisible = false;
    private boolean superAdded = false;

    public GUI() {
        game = new Game();
        frame = new JFrame(); //Our Window
        buttonPanel = new JPanel();
        textPanel = new JPanel();
        fillerPanel = new JPanel();
        button2Panel = new JPanel();
        label = new JLabel("Cookies: ");
        upgrade1Cost = new JLabel("Upgrade Cost: " + game.upgrade1Cost);
        upgrade2Cost = new JLabel("Upgrade Cost: " + game.upgrade2Cost);
        comboCost = new JLabel("Combo Cost: " + game.comboUpgradeCost);
        discountCost = new JLabel("Discount Cost: " + game.discountUpgradeCost);
        criticalCost = new JLabel("Critical Cost: " + game.criticalUpgradeCost);
        goldenCost = new JLabel("Golden Cost: " + game.goldenUpgradeCost);
        printerCost = new JLabel("Printer Cost: " + game.printerUpgradeCost);
        insuranceCost = new JLabel("Insurance Cost: " + game.insuranceUpgradeCost);
        cookieButton = new JButton("Cookie");
        upgradeButton1 = new JButton("Upgrade");
        upgradeButton2 = new JButton("Slow Cooker");
        SuperUpgradeButton = new JButton("Super Upgrade");
        comboButton = new JButton("Combo Multiplier");
        discountButton = new JButton("Discount");
        criticalButton = new JButton("Critical Click");
        goldenPopUp = new JButton("Golden!");
        printerButton = new JButton("Cookie Printer");
        insuranceButton = new JButton("Insurance");
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
        goldenPopUp.addActionListener(e -> {
            game.buyGoldenCookie();
            removeGoldenPopUp();
            refreshLabels();
        });
        //panel elements
        buttonPanel.add(cookieButton);
        cookieButton.setBackground(Color.ORANGE);
        buttonPanel.add(upgradeButton1);
        upgradeButton1.setBackground(Color.CYAN);
        buttonPanel.add(upgradeButton2);
        upgradeButton2.setBackground(Color.PINK);
        //new
        buttonPanel.add(comboButton);
        goldenPopUp.setBackground(new Color(255, 215, 0));
        goldenPopUp.setVisible(false);

        textPanel.add(label);
        textPanel.add(upgrade1Cost);
        textPanel.add(upgrade2Cost);
        textPanel.add(comboCost);

        //panel properties
        buttonPanel.setBackground(Color.PINK);
        buttonPanel.setSize(600, 200);
        buttonPanel.setLayout(new GridLayout(1, 5));
        textPanel.setBackground(new Color(223, 197, 254));
        textPanel.setSize(600, 200);
        textPanel.setLayout(new GridLayout(1, 5));
        button2Panel.setLayout(new GridLayout(1, 5));
        fillerPanel.setBackground(new Color(223, 197, 254));

        frame.add(buttonPanel, BorderLayout.NORTH);
        fillerPanel.add(textPanel, BorderLayout.NORTH);
        frame.add(fillerPanel, BorderLayout.CENTER);
        fillerPanel.add(button2Panel, BorderLayout.AFTER_LINE_ENDS);
        //new
        JPanel bottomCosts = new JPanel(new GridLayout(1, 4));
        bottomCosts.setBackground(new Color(223, 197, 254));
        bottomCosts.setSize(600,200);
        fillerPanel.add(bottomCosts, BorderLayout.SOUTH);

        //frame properties
        frame.setBackground(Color.BLACK);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 500);
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
                    superAdded = true;
                    button2Panel.add(discountButton);
                    button2Panel.add(printerButton);
                    button2Panel.add(criticalButton);
                    button2Panel.add(insuranceButton);
                    bottomCosts.add(discountCost);
                    bottomCosts.add(printerCost);
                    bottomCosts.add(criticalCost);
                    bottomCosts.add(insuranceCost);
                    buttonPanel.revalidate();
                    buttonPanel.repaint();
                }
                /*figure out the name of the label
                set the text of the label to something
                 */
            }
        };
        updateTimer = new Timer(500, guiUpdate);
        updateTimer.start();
    }

    private void refreshLabels() {
        label.setText("Cookies: " + game.cookieCounter);
        upgradeButton1.setText("Upgrade Cost: " + game.upgrade1Cost);
        upgradeButton2.setText("Upgrade Cost: " + game.upgrade2Cost);
        //new
        comboCost.setText("Combo Cost: " + game.comboUpgradeCost);
        discountCost.setText("Discount Cost: " + game.discountUpgradeCost);
        criticalCost.setText("Critical Cost:" + game.criticalUpgradeCost);
        goldenCost.setText("Golden Cost:" + game.goldenUpgradeCost);
        printerCost.setText("Printer Cost: " + game.printerUpgradeCost);
        insuranceCost.setText("Insurance Cost: " + game.insuranceUpgradeCost);
    }

    private void showGoldenPopUp() {
        if (!goldenVisible) {
            button2Panel.add(goldenPopUp);
            goldenPopUp.setVisible(true);
            button2Panel.revalidate();
            button2Panel.repaint();

            int lifeMs = 4500;
            if (goldenLifeTime != null && goldenLifeTime.isRunning()) {
                goldenLifeTime.stop();
                goldenLifeTime = new Timer(lifeMs, ev -> removeGoldenPopUp());
                goldenLifeTime.setRepeats(false);
                goldenLifeTime.start();
            }
        }
        final int minSpawnSec = 10;
        final int maxSpawnSec = 30;
        Runnable scheduleNextGolden = new Runnable() {
            @Override
            public void run() {
                int delaySec = minSpawnSec + (int) (Math.random() * (maxSpawnSec-minSpawnSec + 1));
                if (goldenSpawnTime != null && goldenSpawnTime.isRunning()) goldenSpawnTime.stop();
                goldenSpawnTime = new Timer(delaySec * 1000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (!goldenVisible) {
                            showGoldenPopUp();
                        }
                        goldenSpawnTime.stop();
                        //SwingUtilities.invokeLater();
                    }
                });
                goldenSpawnTime.setRepeats(false);
                goldenSpawnTime.start();
                }
            };
        SwingUtilities.invokeLater(scheduleNextGolden);
    }

    private void removeGoldenPopUp() {
        if (goldenVisible) {
            if (goldenLifeTime != null && goldenLifeTime.isRunning()) goldenLifeTime.stop();
            button2Panel.remove(goldenPopUp);
            goldenPopUp.setVisible(false);
            goldenVisible = false;
            button2Panel.revalidate();
            button2Panel.repaint();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}


