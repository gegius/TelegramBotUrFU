package hero;
import init.DataBase;
import init.SendAnswer;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class Hero {
    private int heroHeath;
    private int heroMana;
    private int heroLvl;
    private  int heroDamage;
    private int heroExperience;

    DataBase dataBase = new DataBase();
    SendMessage message = new SendMessage();
    SendAnswer answer = new SendAnswer();


    public Hero(int heath , int mana , int lvl , int damage, int experience){
        this.heroHeath = heath;
        this.heroMana = mana;
        this.heroDamage = damage;
        this.heroLvl = lvl;
        this.heroExperience = experience;

    }


    public int getHeroDamage() {
        return heroDamage;
    }

    public int getHeroExperience() {
        return heroExperience;
    }

    public int getHeroHeath() {
        return heroHeath;
    }

    public int getHeroMana() {
        return heroMana;
    }

    public int getHeroLvl() {
        return heroLvl;
    }

    public void setHeroMana(int mana){this.heroMana = mana;}



    public String get_Stats(String chatId){
        return "Ваши статы:" +
                "\nЗдоровье = " +getHeroHeath() +
                "\nМана = " + getHeroMana() +
                "\nУрон = " + getHeroDamage() +
                "\nУровень = " + getHeroLvl() +
                "\nОпыт = " + getHeroExperience() + " \\ 100 ";
    }


}
