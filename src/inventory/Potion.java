package inventory;

public class Potion {
    private int potion_healing;
    private String potion_name;

    public Potion(String name) {
        this.potion_name = name;
    }

    public int getPotion_healing() {
        return potion_healing;
    }

    public String getName() {
        return potion_name;
    }

    public void setPotion_healing(String potion_healing) {
        if (potion_name.equals("Стартовое зелье")) {
            this.potion_healing = 50;
        }
        if (potion_name.equals("Зелье мастера")) {
            this.potion_healing = 75;
        }
        if (potion_name.equals("Зелье победителя")) {
            this.potion_healing = 100;
        }
        if (potion_name.equals("Зелье дракона")) {
            this.potion_healing = 150;
        }
    }
}
