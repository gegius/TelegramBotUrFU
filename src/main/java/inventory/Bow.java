package inventory;

public class Bow extends Weapon{
    private int bow_damage;
    private String bow_name;

    public Bow(String name){
        super(name);


    }

    public int getDamage() {
        return bow_damage;
    }

    public String getName() {
        return bow_name;
    }

    public void setBow_damage() {
        switch (this.bow_name) {
            case "Обычный лук" -> {
                this.bow_damage = 30; ;
            }
            case "Редкий лук" -> {
                this.bow_damage = 60;
            }
            case "Мифический лук" -> {
                this.bow_damage = 90;
            }
            case "Легендарный лук" -> {
                this.bow_damage = 120;
            }
        }
    }

    public String getInfo(){
        switch (this.bow_name) {
            case "Обычный лук" -> {
                return "1";
            }
            case "Редкий лук" -> {
                return "2";
            }
            case "Мифический лук" -> {
                return "3";
            }
            case "Легендарный лук" -> {
                return "4";
            }
            default -> {return "Такого лука нет";}
        }

    }

}
