import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/*
TODO:
make the numbers increase when we but more upgrades


 */

public class Game {
    protected int cookieCounter;
    private int cookiesPerClick;
    private int cookiesPerSecond;
    protected int upgrade1Cost;
    protected int upgrade2Cost;
    private int upgradeCount;
    //new
    private int clickStreak;

    public int comboUpgradeCost;
    public int discountUpgradeCost;
    public int criticalUpgradeCost;
    public int goldenUpgradeCost;
    public int printerUpgradeCost;
    public int insuranceUpgradeCost;

    public boolean hasCombo;
    public boolean hasDiscount;
    public boolean hasCritical;
    public boolean hasPrinter;
    public boolean hasInsurance;

    public double comboMultiplier;
    public double discountPercent;
    public double criticalChance;
    public int criticalBonus;
    public int printerBonusPerClick;

    private Random rand;

    public Game () {
        cookieCounter = 0;
        cookiesPerClick = 1;
        cookiesPerSecond = 0;
        upgrade1Cost = 5;
        upgrade2Cost = 25;
        upgradeCount = 0;
        //new
        comboUpgradeCost = 200;
        discountUpgradeCost = 500;
        criticalUpgradeCost = 400;
        goldenUpgradeCost = 800;
        printerUpgradeCost = 1000;
        insuranceUpgradeCost = 1200;

        hasCombo = false;
        hasDiscount = false;
        hasCritical = false;
        hasPrinter = false;
        hasInsurance = false;

        comboMultiplier = 1.0;
        discountPercent = 0.10;
        criticalChance = 0.20;
        criticalBonus = 50;
        printerBonusPerClick = 5;

        rand = new Random();

        Timer slowCooker = new Timer();
        TimerTask slow = new TimerTask() {
            @Override
            public void run() {
                cookieCounter += cookiesPerSecond;
            }
        };
        slowCooker.schedule(slow, 0, 1000 );
    }

    public void cookieClick () {
        cookieCounter += cookiesPerClick;
        int totalClick = cookiesPerClick;
        if (hasCritical) {
            double roll = rand.nextDouble();
            if (roll < criticalChance) {
                totalClick += criticalBonus;
            }
        }
        if (hasCombo == true) {
            clickStreak++;
            int comboContribution = (int) (clickStreak * comboMultiplier);
            cookieCounter += totalClick + comboContribution;
        }
    }

    public void upgradeButton1() {
        if (cookieCounter >= upgrade1Cost) {
            cookiesPerClick++;
            cookieCounter -= upgrade1Cost;
            upgradeCount++;
            upgrade1Cost += upgradeCount;
        }
    }
    public void upgradeButton2() {
        if (cookieCounter >= upgrade2Cost) {
            cookiesPerSecond++;
            cookieCounter -= upgrade2Cost;
            upgradeCount++;
            upgrade2Cost += upgradeCount;
        }
    }
    //new
    public void buyComboMultiplier () {
        if (cookieCounter >= comboUpgradeCost) {
            cookieCounter -= comboUpgradeCost;
            comboMultiplier += 0.5;
            hasCombo = true;
            comboUpgradeCost *= 2;
        }
    }

    public void buyDiscount() {
        if (cookieCounter >= discountUpgradeCost && !hasDiscount) {
         cookieCounter -= discountUpgradeCost;
         hasDiscount = true;
         applyDiscountToAllUpgrades();
         discountUpgradeCost *= 2;
        }
    }

    private void applyDiscountToAllUpgrades () {
        upgrade1Cost = Math.max(1, upgrade1Cost - (int)(upgrade1Cost * discountPercent));
        upgrade2Cost = Math.max(1, upgrade2Cost - (int)(upgrade2Cost * discountPercent));

        comboUpgradeCost = Math.max(1, comboUpgradeCost - (int)(comboUpgradeCost * discountPercent));
        criticalUpgradeCost = Math.max(1, criticalUpgradeCost - (int)(criticalUpgradeCost * discountPercent));
        goldenUpgradeCost = Math.max(1, goldenUpgradeCost - (int)(goldenUpgradeCost * discountPercent));
        printerUpgradeCost = Math.max(1, printerUpgradeCost - (int)(printerUpgradeCost * discountPercent));
        insuranceUpgradeCost = Math.max(1, insuranceUpgradeCost - (int)(insuranceUpgradeCost * discountPercent));
        discountUpgradeCost = Math.max(1, discountUpgradeCost - (int)(discountUpgradeCost * discountPercent));
    }
    public void buyCriticalClick() {
        if (cookieCounter >= criticalUpgradeCost) {
            cookieCounter -= criticalUpgradeCost;
            hasCritical = true;
            criticalUpgradeCost *= 2;
        }
    }
    public void buyGoldenCookie () {
        if (cookieCounter >= goldenUpgradeCost) {
            cookieCounter -= goldenUpgradeCost;
            int bonus = 100 + rand.nextInt(401);
            cookieCounter += bonus;
            goldenUpgradeCost *= 2;
        }
    }
    public void buyCookiePrinter () {
        if (cookieCounter >= printerUpgradeCost) {
            cookieCounter -= printerUpgradeCost;
            hasPrinter = true;
            cookiesPerClick += printerBonusPerClick;
            printerUpgradeCost *= 2;
        }
    }
    public void buyInsurance () {
        if (cookieCounter >= insuranceUpgradeCost) {
            cookieCounter -= insuranceUpgradeCost;
            hasInsurance = true;
            insuranceUpgradeCost *= 2;
        }
    }
    public void loseCookiePercent () {
        int loss = (int) Math.ceil(cookieCounter * discountPercent);
        if (hasInsurance == true) {
            loss = loss / 2;
        }
        cookieCounter -= loss;
        if (cookieCounter < 0) {
            cookieCounter = 0;
        }
    }


}
