package inventory;

public class Weapon {
    private int damage;
    private String name;


    public Weapon(String name) {
        this.name = name;
        setDamage();
    }


    public int getDamage() {
        return damage;
    }

    public String getName() {
        return name;
    }

    public void setDamage(){}

}

