package inventory;

public class Armor {
    private int armorLvl;
    private String armor_name;
    private int armor_mana;

    public Armor( String name){
        this.armor_name = name;
        setStats();
    }

    public int getArmorlvl() {
        return armorLvl;
    }

    public int getArmorMana() {
        return armor_mana;
    }

    public String getArmor_name() {
        return armor_name;
    }


    // Установка статов по имени брони
    public void setStats() {
        switch (this.armor_name) {
            case "Обычная броня" -> {
                this.armorLvl = 1;
                this.armor_mana = 0 ;
            }
            case "Редкая броня" -> {
                this.armorLvl = 2;
                this.armor_mana = 30;
            }
            case "Мифическая броня" -> {
                this.armorLvl = 3;
                this.armor_mana = 50;
            }
            case "Легендарная броня" -> {
                this.armorLvl = 4;
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
