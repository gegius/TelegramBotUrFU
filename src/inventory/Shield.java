package inventory;

public class Shield {
    private int shield_coefficient;
    private String shield_name;

    public Shield(int coefficient, String name){
        this.shield_coefficient = coefficient;
        this.shield_name = name;
    }

    public int getShield_coefficient() {
        return shield_coefficient;
    }

    public String getShield_name() {
        return shield_name;
    }

    public void setShield_coefficient(String shield_name) {
        if (this.shield_name.equals("Обычный щит")) {
            this.shield_coefficient = 1;}
        if (this.shield_name.equals("Обычный щит")) {
            this.shield_coefficient = 2;}
        if (this.shield_name.equals("Обычный щит")) {
            this.shield_coefficient = 3;}
        if (this.shield_name.equals("Обычный щит")) {
            this.shield_coefficient = 4;}

    }
    public String get_Info() {
        if (this.shield_name.equals("Стартовый щит")) {
            return "Описание брони"; // так для каждой брони..............
        } else {
            return "Такого щита не существует"; // Тут ретерны будут связаны с отправкой сообщения пользователю
        }
    }


}
