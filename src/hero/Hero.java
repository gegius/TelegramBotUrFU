package hero;

public class Hero {
    private final int heroHeath;
    private final int heroMana;
    private int heroLvl;
    private final int heroDamage;
    private int heroExperience;


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

    public void setHeroLvl(int giveLvl){
        this.heroLvl += giveLvl;
    }


    // Этот опыт(exp) нам будут давать мобы или еще что-то
    public void setHeroExperience(int exp){
        int postFightExperience =  exp + getHeroExperience();
        if (postFightExperience >= 100){
            var giveLvl = exp / 100;
            setHeroLvl(giveLvl);
            this.heroExperience = postFightExperience % 100 + getHeroExperience();
        }
        else{ this.heroExperience = getHeroExperience() + exp;}
    }

    public String get_Stats(){
        return "Ваши статы:" +
                "\nЗдоровье = " +getHeroHeath() +
                "\nМана = " + getHeroMana() +
                "\nУрон = " + getHeroDamage() +
                "\nУровень = " + getHeroLvl() +
                "\nОпыт = " + getHeroExperience();
    }


}
