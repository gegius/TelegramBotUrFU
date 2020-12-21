package hero;
import init.DataBase;
import init.SendAnswer;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.ArrayList;

public class Hero {
    private int heroHeath;
    private int heroMana;
    private int heroLvl;
    private int heroDamage;
    private int heroExperience;

    DataBase dataBase = new DataBase();
    SendMessage message = new SendMessage();
    SendAnswer answer = new SendAnswer();


    public Hero(int heath, int mana, int lvl, int damage, int experience) {
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

    public void setHeroMana(int mana) {
        this.heroMana = mana;
    }


    public String get_Stats(String chatId) {
        return "Ваши статы:" +
                "\nЗдоровье = " + getHeroHeath() +
                "\nМана = " + getHeroMana() +
                "\nУрон = " + getHeroDamage() +
                "\nУровень = " + getHeroLvl() +
                "\nОпыт = " + getHeroExperience() + " \\ 100 ";
    }

    public boolean intToBoolean(int input) {
        if ((input == 0) || (input == 1)) {
            return input != 0;
        } else {
            throw new IllegalArgumentException("Входное значение может быть равно только 0 или 1 !");
        }
    }

    public int getHeroSpellDamage(String commandUser, String database) {
        return Integer.parseInt(dataBase.select(commandUser, "spellDamage", database));
    }

    public int getHeroSpellBlock(String commandUser, String database) {
        return Integer.parseInt(dataBase.select(commandUser, "spellBlock", database));
    }

    public int getHeroSpellManaCost(String commandUser, String database) {
        return Integer.parseInt(dataBase.select(commandUser, "spellManaCost", database));
    }

    public boolean getHeroSpellBoolDamage(String commandUser, String database) {
        return intToBoolean(Integer.parseInt(dataBase.select(commandUser, "spellBoolDamage", database)));

    }

    public boolean getHeroSpellBoolBlock(String commandUser, String database) {
        return intToBoolean(Integer.parseInt(dataBase.select(commandUser, "spellBoolBlock", database)));
    }

    public String getHeroSpellName(String commandUser, String database) {
        return dataBase.select(commandUser, "spellHeroName", database);
    }

    public String getHeroSpellDescription(String commandUser, String database) {
        return dataBase.select(commandUser, "spellDescription", database);
    }

    public boolean isThatSpell(String commandUser, String database) {
        return dataBase.select(commandUser, "id", database).equals(commandUser) && (dataBase.select(commandUser, "heroName", database).equals(Hero.class.getName()));
    }

    public ArrayList<Object> getSpellArray(String commandUser, String database) {
        ArrayList<java.lang.Object> spellList = new ArrayList<>();
        spellList.add(getHeroSpellDamage(commandUser, database));
        spellList.add(getHeroSpellBlock(commandUser, database));
        spellList.add(getHeroSpellDescription(commandUser, database));
        spellList.add(getHeroSpellManaCost(commandUser, database));
        spellList.add(getHeroSpellBoolDamage(commandUser, database));
        spellList.add(getHeroSpellBoolBlock(commandUser, database));
        spellList.add(getHeroSpellName(commandUser, database));
        return spellList;
    }

    public ArrayList<Object> getSpellByName(String commandUser, String database) {
        dataBase.open("spells");
        if (isThatSpell(commandUser, database)) {
            return getSpellArray(commandUser, database);
        }
        else{
            return null;
        }
    }
}
