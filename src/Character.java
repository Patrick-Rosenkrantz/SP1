import java.util.Scanner;
public class Character {

    String name;
    int health;
    int maxHealth;
    int level;
    int xp;
    int gold;
    boolean isAlive;
    String role;

    String[] inventory = new String[5];

    Scanner input = new Scanner(System.in);

    public void welcomeMessage() {
        System.out.println("Welcome to the game!");
    }



    public void pickRole() {
        boolean isValidRole = false;
        System.out.println("What class do you wanna be?");
        System.out.println("(W) Warrior, (M) Mage, (R) Ravager");
        while (!isValidRole) {
            String answer = input.nextLine();
            role = answer;

            switch (role) {
                case "W" :
                    System.out.println("Welcome Warrior");
                    System.out.println("What is your name?");
                    answer = input.nextLine();
                    name = answer;
                    maxHealth = 150;
                    health = maxHealth;
                    isValidRole = true;
                    break;

                case "M" :
                    System.out.println("Welcome Mage");
                    System.out.println("What is your name?");
                    answer = input.nextLine();
                    name = answer;
                    maxHealth = 125;
                    health = maxHealth;
                    isValidRole = true;
                    break;

                case "R" :
                    System.out.println("Welcome Ravager");
                    System.out.println("What is your name?");
                    answer = input.nextLine();
                    name = answer;
                    maxHealth = 100;
                    health = maxHealth;
                    isValidRole = true;
                    break;
                default:
                    System.out.println("Invalid answer, try again!");
            }
        }

    }

    public void printStats() {
        System.out.println("=== " + name.toUpperCase() + "'S STATS ===");
        System.out.println("Name: " + name);
        System.out.println("Health points: " + health);
        System.out.println("Max health: " + maxHealth);
        System.out.println("Level: " + level);
        System.out.println("Experience points: " + xp);
        System.out.println("Gold: " + gold);
        System.out.println("Is alive: " + isAlive);
        System.out.println("Type: " + role);
        System.out.println();
    }



    public void healthCheck() {
        if (health < (maxHealth * 0.25) && health > 0) {
            System.out.println("WARNING: Health critical");
        }
        if (health <= 0) {
            isAlive = false;
            System.out.println("You died");
        }
    }

    public void checkLevel() {
        while (xp >= 1000) {
            level += 1;
            getXP(-1000);
            System.out.println("You leveled up!");
            maxHealth = maxHealth + (level * 20);
            health = maxHealth;
        }
    }

    // Inventory
    public void printInventory() {
        System.out.println("=== Inventory ===");
        int count = 0;
        if (inventory[0] == null) {
            System.out.println(" You have no items");
            System.out.println();
        } else {
            for (String item : inventory) {
                if (item == null) {
                } else {
                    System.out.println(" - " + item);
                    count++;
                }
            }
            System.out.println("You have " + count + " items");
            System.out.println();
        }
    }


    public boolean putItemInInventory(String item) {
        for (int i = 0; i < inventory.length; i++) {
            if (item == null) {
                return false;
            }
            if (inventory[i] == null) {
                inventory[i] = item;
                return true;
            }
        }
        System.out.println("You dont have any more room in your inventory");
        return false;
    }


    public void maxHealth() {
        if (health > maxHealth) {
            health = maxHealth;
        }
    }

    public void getGold(int gold) {
        this.gold = this.gold + gold;
    }

    public void getXP(int xp) {
        this.xp = this.xp + xp;
    }

    public void takeDamage(int damage) {
        health = health - damage;
    }

    public void resetStats() {
        level = 0;
        xp = 0;
        gold = 0;
        isAlive = true;
        for (int i = 0; i < inventory.length; i++) {
            inventory[i] = null;
        }
    }

    public int giveHealth(int giveHealth) {
        health = health + giveHealth;
        if (health  > maxHealth) {
            health = maxHealth;
        } return health;
    }






    // Enemy
    public void enemyResetStats() {
        String[] enemyNames = {"Crawler", "Zombie", "Vampire"};
        int randomNumber = (int) (Math.random() * enemyNames.length);
        name = enemyNames[randomNumber];
        health = 100;
        maxHealth = 100;
        level = 0;
        isAlive = true;
        role = "Monster";
    }

    public void enemyPrintStats() {
        System.out.println("=== " + name.toUpperCase() + "'S STATS ===");
        System.out.println("Name: " + name);
        System.out.println("Health points: " + health);
        System.out.println("Max health: " + maxHealth);
        System.out.println("Level: " + level);
        System.out.println("Is alive: " + isAlive);
        System.out.println("Type: " + role);
        System.out.println();
    }











    // Combat

    public void printFightStats() {
        System.out.println("Name: " + name + " | Health: " + health + "/" + maxHealth + " | Level : " + level);
    }

    int healOrAttack() {
        int randomNumber = (int) (Math.random() * 2);
        if (randomNumber == 0) {
            attackDamage = (int) (Math.random() * 41);
            System.out.println(name + " Attacks for " + attackDamage + " HP");
            System.out.println();
            return attackDamage;
        } else {
            health = health + 20;
            maxHealth();
            System.out.println(name + " Healed 20 HP");
            System.out.println();
            return 0;
        }
    }

    public void enemyHealthCheck() {
        if (health <= 0) {
            isAlive = false;
            System.out.println("You win, but another is coming!");
        }
    }


    int attackDamage;

    void attackWarrior() {
        attackDamage = (int) (Math.random() * 41);
        if (attackDamage >= 30) {
            System.out.println("Critical hit");
        }
        if (attackDamage < 10) {
            attackDamage = 10;
        }
        System.out.println("Attack was " + attackDamage + "HP!");
        System.out.println();
    }

    void attackMage() {
        attackDamage = (int) (Math.random() * 51);
        if (attackDamage >= 40) {
            System.out.println("Critical hit");
        }
        System.out.println("Attack was " + attackDamage + "HP!");

        System.out.println();
    }

    void attackRavager() {
        attackDamage = (int) (Math.random() * 75);
        if (attackDamage >= 65) {
            System.out.println("Critical hit");
        }
        if (attackDamage >= 25 && attackDamage < 65) {
            attackDamage = 0;
            System.out.println("Ravager missed attack!");
        }
        System.out.println("Attack was " + attackDamage + "HP!");
        System.out.println();
    }



    String combatAnswer;

    public int combat() {
        boolean isValidAnswer = false;
        while (!isValidAnswer) {
            System.out.println("Attack, Heal, Shop or StopGame?");
            combatAnswer = input.nextLine();
            switch (combatAnswer) {
                case "Attack":
                    switch (role) {
                        case "W":
                            attackWarrior();
                            isValidAnswer = true;
                            return attackDamage;


                        case "M":
                            attackMage();
                            isValidAnswer = true;
                            return attackDamage;


                        case "R":
                            attackRavager();
                            isValidAnswer = true;
                            return attackDamage;

                    }

                case "Heal":
                    isValidAnswer = true;
                    return -1;


                case "Shop":
                    isValidAnswer = true;
                    return -2;


                case "StopGame":
                    isValidAnswer = true;
                    return -3;
                default:
                    System.out.println("Invalid answer, try again!");
                    break;
            }
        }
        return 0;
    }
}
