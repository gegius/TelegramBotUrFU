package inventory;

public class Sword {
    private int sword_damage;
    private String sword_name;

    public Sword(String name) {
    this.sword_name = name;
    setSword_damage();
    }

    public int getSword_damage(){
        return sword_damage;
    }

    public String getSword_name() {
        return sword_name;
    }

    //Установка статов по имени меча
    public void setSword_damage() {
        switch (this.sword_name) {
            case "Обычный меч" -> {
                this.sword_damage = 35;
            }
            case "Редкий меч" -> {
                this.sword_damage = 50;
            }
            case "Мифический меч" -> {
                this.sword_damage = 80;
            }
            case "Легендарний меч" -> {
                this.sword_damage = 120;
            }
        }
    }

    public String get_Stats(){return "Статы меча";} // вывод пользователю


    //Резульат вызова этого метода будем пихать в sendMassage, когда пользователь запросит информацию
    public String get_Info() {
        switch (this.sword_name){
            case "Обычный щит" -> {return "A";}
            case "Редкий меч" -> {return "B";}
            case "Мифический меч" ->{return "C";}
            case "Легендарный меч" ->{return "D";}
            default -> {return "Такого меча не существует";}
        }

    }
}
