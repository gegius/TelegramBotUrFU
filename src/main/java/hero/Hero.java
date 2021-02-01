package hero;
import init.SendAnswer;
import java.util.ArrayList;

public abstract class Hero {
    private int heroHealth;
    private int heroMaxHealth;
    private int heroMana;
    private int heroMaxMana;
    private int heroLvl;
    private int heroDamage;
    private int heroExperience;
    private int heroMaxExperience;


    public Hero(int heath, int mana, int lvl, int damage, int experience) {
        this.heroHealth = heath;
        this.heroMaxHealth = heath;
        this.heroMana = mana;
        this.heroMaxMana = mana;
        this.heroDamage = damage;
        this.heroLvl = lvl;
        this.heroExperience = experience;
    }
    public int getHeroHealth(){
        return heroHealth;
    }

    public void setHeroHealth(int heroHealth) {
        this.heroHealth = heroHealth;
    }

    public int getHeroMaxHealth(){
        return heroMaxHealth;
    }

    public void setHeroMaxHealth(int heroMaxHealth){
        this.heroMaxHealth = heroMaxHealth;
    }

    public int getHeroMana(){
        return heroMana;
    }

    public void setHeroMana(int heroMana) {
        this.heroMana = heroMana;
    }

    public int getHeroMaxMana(){
        return heroMaxMana;
    }

    public void setHeroMaxMana(int heroMaxMana){
        this.heroMaxMana = heroMaxMana;
    }

    public int getHeroLvl() {
        return heroLvl;
    }

    public void setHeroLvl(int heroLvl) {
        this.heroLvl = heroLvl;
    }

    public int getHeroDamage() {
        return heroDamage;
    }

    public void setHeroDamage(int heroDamage) {
        this.heroDamage = heroDamage;
    }

    public int getHeroExperience() {
        return heroExperience;
    }

    public int getHeroMaxExperience(){
        return heroMaxExperience;
    }

    public void addHeroExperience(int exp){
        var currentExp = getHeroExperience() + exp;
        if (currentExp >= getHeroMaxExperience()) {
            this.heroExperience = currentExp - getHeroMaxExperience();
            this.heroMaxExperience = getHeroMaxExperience() * 2;
            setHeroLvl(getHeroLvl() + 1);
            upgradeStats(getHeroLvl());

        }
        else{
            this.heroExperience = currentExp;
        }
    }

    public abstract String getHeroClass();

    protected abstract void upgradeStats(int lvl);

    public String get_Stats(String chatId) {
        return "Ваши статы:" +
                "\nЗдоровье = " + heroMaxHealth +
                "\nМана = " + heroMaxMana +
                "\nУрон = " + getHeroDamage() +
                "\nУровень = " + getHeroLvl() +
                "\nОпыт = " + getHeroExperience() + " \\ 100 ";

    }
}