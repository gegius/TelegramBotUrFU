package inventory;

public class Shield {
    private double shield_coefficient;
    private String shield_name;

    public Shield(int coefficient, String name) {
        this.shield_name = name;
        setShield_coefficient();
    }

    public double getShield_coefficient() {
        return shield_coefficient;
    }

    public String getShield_name() {
        return shield_name;
    }

    //Задаем коофицент снижения урона по имени щита
    public void setShield_coefficient() {
        switch (this.shield_name) {
            case "Обычный щит" -> {
                this.shield_coefficient = 0.2;
            }
            case "Редкий щит" -> {
                this.shield_coefficient = 0.4;
            }
            case "Мифический щит" -> {
                this.shield_coefficient = 0.6;
            }
            case "Легендарний щит" -> {
                this.shield_coefficient = 0.8;
            }
        }
    }

    //Резульат вызова этого метода будем пихать в sendMassage, когда пользователь запросит информацию
    public String get_Info() {
        switch (this.shield_name) {
            case "Обычный щит" -> {
                return "A";
            }
            case "Редкий щит" -> {
                return "B";
            }
            case "Мифический щит" -> {
                return "C";
            }
            case "Легендарный щит" -> {
                return "D";
            }
            default -> {return "Такого щита нет";}
        }
    }
}



