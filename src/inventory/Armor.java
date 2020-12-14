package inventory;

public class Armor {
    private int armor_lvl;
    private String armor_name;
    private int armor_mana;

    public Armor(int lvl_of_armor, String name){
        this.armor_lvl = lvl_of_armor;
        this.armor_name = name;
    }

    public int get_Armor(){return armor_lvl;}
    public String get_Name(){return armor_name;}

    public void setArmor_mana(String armor_name) {
        if (armor_name.equals("Стартовая броня") || armor_name.equals("Броня номер ддва")){
            this.armor_mana = 0;
        }
        else{
            this.armor_mana = 100;
        }
    }

    public int getArmor_mana() {
        return armor_mana;
    }

    public String get_Info(){
        if (this.armor_name.equals("Стартовая броня")){
            return "Описание брони"; // так для каждой брони..............
        }
        else{
            return "Такой брони не существует"; // Тут ретерны будут связаны с отправкой сообщения пользователю
        }
    }

}
