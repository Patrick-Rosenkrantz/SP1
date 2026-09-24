public class Game {
    Character hero = new Character();
    Character enemy = new Character();
    Items items = new Items();
    boolean gameIsRunning;
    int rounds;

    public void shop() {
        items.printShop();
        System.out.println("What do you want to buy?");
        System.out.println("you have " + hero.gold + " gold");
        boolean isValidAnswer = false;
        while (!isValidAnswer) {
            String answer = hero.input.nextLine();
            switch (answer) {
                case "Sword":
                    isValidAnswer = true;
                    if (hero.gold < items.prices[0]) {
                        System.out.println("you dont have enough gold");
                    } else if (hero.putItemInInventory(answer)){
                        hero.gold -= items.prices[0];
                        System.out.println("You bought a "+items.shop[0]);
                    }
                    break;
                case "Shield":
                    isValidAnswer = true;
                    if (hero.gold < items.prices[1]) {
                        System.out.println("you dont have enough gold");
                    } else if(hero.putItemInInventory(answer)){
                        hero.gold = hero.gold - items.prices[1];
                        System.out.println("You bought a "+items.shop[1]);
                    }
                    break;
                case "Potion":
                    isValidAnswer = true;
                    if (hero.gold < items.prices[2]) {
                        System.out.println("you dont have enough gold");
                    } else if (hero.putItemInInventory(answer)){
                        hero.gold -= items.prices[2];
                        System.out.println("You bought a "+items.shop[2]);
                    }
                    break;
                default:
                    System.out.println("Invalid answer, try again");
            }
        }
    }


    public void newEnemy() {
        hero.putItemInInventory(items.getMonsterItem());
        enemy.enemyResetStats();
        rounds++;
        enemy.maxHealth = 100 + (20 * rounds);
        enemy.level = rounds;
        enemy.giveHealth(enemy.maxHealth);
        hero.getGold(100);
        hero.getXP(750);
        hero.giveHealth(hero.maxHealth);

    }

    public void heroAttack() {
        int heroDamage = hero.combat();
        if (heroDamage == -1){
            hero.giveHealth(20);
            System.out.println("You healed 20 HP");
            System.out.println("Your health: " + hero.health);
        } else if (heroDamage == -2){
            shop();
        } else if (heroDamage == -3) {
            hero.isAlive = false;
            gameIsRunning = false;
        }   else {
            enemy.takeDamage(heroDamage);
            enemy.enemyHealthCheck();
        }
    }

    public void enemyAttack() {
        int enemyDamage = enemy.healOrAttack();
        hero.takeDamage(enemyDamage);
        hero.healthCheck();
    }


    public void startGame() {
        gameIsRunning = true;
        rounds = 0;
        hero.resetStats();
        enemy.enemyResetStats();

        hero.welcomeMessage();
        hero.pickRole();

        while (gameIsRunning) {
            hero.printStats();
            hero.printInventory();
            enemy.enemyPrintStats();

            while (hero.isAlive && enemy.isAlive) {
                System.out.println();
                hero.checkLevel();
                heroAttack();

                if (!hero.isAlive){
                    gameIsRunning = false;
                    hero.printStats();
                    enemy.enemyPrintStats();
                    break;
                }
                if (!enemy.isAlive) {
                    newEnemy();
                    break;
                }
                enemyAttack();
                if (!hero.isAlive){
                    gameIsRunning = false;
                    System.out.println("You died to "+enemy.name);
                    hero.printStats();
                    enemy.printStats();
                }

                hero.printFightStats();
                enemy.printFightStats();
            }
        }
    }
}
