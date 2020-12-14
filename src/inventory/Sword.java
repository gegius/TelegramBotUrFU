package inventory;

public class Sword {
    private int sword_damage;
    private String sword_name;

    public Sword(String name) {
        this.sword_name = name;
    }

    public int getSword_damage() {
        return sword_damage;
    }

    public String getSword_name() {
        return sword_name;
    }

    public void setSword_damage(String sword_name) {
        if (this.sword_name.equals("Обычный меч")) {
            this.sword_damage = 25;
        }
        if (this.sword_name.equals("Редкий меч")) {
            this.sword_damage = 50;
        }
        if (this.sword_name.equals("Супер редкий меч")) {
            this.sword_damage = 75;
        }
        if (this.sword_name.equals("Легендарный меч")) {
            this.sword_damage = 100;
        }
    }

    public String get_Stats(){return "Статы меча";} // вывод пользователю
    public String get_Info()
    {
        if (sword_name.equals("Стартовый меч")){return "Описание меча";} // вывод пользователю
        else { return "Такого меча не существует";}
    }
}
