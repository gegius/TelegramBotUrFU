package inventory;

import com.fasterxml.jackson.databind.JsonSerializer;

import javax.validation.constraints.Null;

public class Potion {
    private int potion_healing;
    private String potion_name;

    public Potion(String name) {
        this.potion_name = name;
        setPotion_healing();
    }

    public int getPotion_healing(){return potion_healing;}

    public String getName(){return potion_name;}


    // Установка статов по имени зелья
    public void setPotion_healing() {
        switch (this.potion_name) {
            case "Обычное зелье" -> {
                this.potion_healing = 30; ;
            }
            case "Редкое зелье" -> {
                this.potion_healing = 60;
            }
            case "Мифические зелье" -> {
                this.potion_healing = 90;
            }
            case "Легендарное зелье" -> {
                this.potion_healing = 120;
            }
        }
    }

    //Резульат вызова этого метода будем пихать в sendMassage, когда пользователь запросит информацию
    public String get_Info(){
        switch (this.potion_name) {
            case "Обычное зелье" -> {return "1";}
            case "Редкое зелье" -> {return "2";}
            case "Мифическое зелье" -> {return "3";}
            case "Легендарное зелье" -> {return "4";}
            default -> {return "Такого зелья нет";}
        }
    }
}

