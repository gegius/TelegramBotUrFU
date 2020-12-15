package inventory;

public class Armor {
    private int armor_lvl;
    private String armor_name;
    private int armor_mana;

    public Armor( String name){
        this.armor_name = name;
        set_Stats();
    }

    public int getArmor_lvl() {
        return armor_lvl;
    }

    public int getArmor_mana() {
        return armor_mana;
    }

    public String getArmor_name() {
        return armor_name;
    }

    // Установка статов по имени брони
    public void set_Stats() {
        switch (this.armor_name) {
            case "Обычная броня" -> {
                this.armor_lvl = 1;
                this.armor_mana = 0 ;
            }
            case "Редкая броня" -> {
                this.armor_lvl = 2;
                this.armor_mana = 30;
            }
            case "Мифическая" -> {
                this.armor_lvl = 3;
                this.armor_mana = 50;
            }
            case "Легендарная броня" -> {
                this.armor_lvl = 4;
                this.armor_mana = 100;
            }
        }
    }

    //Резульат вызова этого метода будем пихать в sendMassage, когда пользователь запросит информацию
    public String get_Info(){
        switch (this.armor_name){
            case "Обычная броня" -> {return "1";}
            case "Редкая броня" -> {return "2";}
            case "Мифическая броня" -> {return "3";}
            case "Легендарная броня" -> {return "4";}
            default -> {
                return "Такой брони не существует";
            }
        }
    }

}
