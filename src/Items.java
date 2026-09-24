
public class Items {
    public String[] shop = {"Sword", "Shield", "Potion"};
    public int[] prices = {150,100,50};

    public void printShop() {
        System.out.println("The Shop has these items");
        for (int i = 0; i < shop.length; i++) {
            System.out.println(" - " + shop[i] + " Price: " + prices[i]);
        }
    }
    public String[] enemyDrops = {"Rotten flesh", "Golden apple", "Skull"};


    public String getMonsterItem() {
        int randomNumber = (int) (Math.random() * 2);
        if (randomNumber == 0) {
            System.out.println("The monster dropped something!");
            int random = (int) ((Math.random()) * enemyDrops.length);
            return enemyDrops[random];
        }
        return null;
    }
}

