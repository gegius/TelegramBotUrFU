package hero;
import java.util.Random;

public class Archer extends Hero implements Spells {
    public Archer(int heath, int mana, int lvl, int damage, int experience) {
        super(heath, mana, lvl, damage, experience);
    }

    @Override
    public boolean checkMana(int costMana) {
        return getHeroMana() > costMana;
    }

    @Override
    public int theFirstSpell(int costMana) {
        if (checkMana(costMana)) {
            setHeroMana(getHeroMana() - costMana);
            return (int) (getHeroLvl() * 1.5 * getHeroDamage());
        } else {
            // Пишем,что не хватает маны
            return -1;
        }
    }

    public void theFirstSpell() {
        theFirstSpell(30);
    }

    @Override
    public double theSecondSpell(int costMana) {
        if (checkMana(costMana)) {
            setHeroMana(getHeroMana() - costMana);
            Random random = new Random();
            if (random.nextInt(5) < 2) {
                return 0;
            } else {
                return 1;
            }
        }
        //Пишем,что не хватает маны
        return -1;
    }

    public void theSecondSpell() {
        theSecondSpell(20);
    }

    @Override
    public int theUltimate(int costMana) {
        theUltimate(100);
        if (checkMana(costMana)) {
            setHeroMana(getHeroMana() - costMana);
            return getHeroDamage() * 3;
        } else {
            //Не хватает маны
            return -1;
        }
    }

    @Override
    public String theFirstSpellDescription() {
        return "Выстрел из лука";
    }

    @Override
    public String theSecondSpellDescription() {
        return "Уворот";
    }

    @Override
    public String theUltimateDescription() {
        return "Град стрел";
    }
}