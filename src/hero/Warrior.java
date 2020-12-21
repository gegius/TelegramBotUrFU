package hero;

import java.util.ArrayList;

public class Warrior extends Hero implements Spells {

    public Warrior(int heath, int mana, int lvl, int damage, int experience) {
        super(heath, mana, lvl, damage, experience);
    }

    @Override
    public boolean checkMana(int costMana) {
        return getHeroMana() > costMana;
    }

    @Override
    public int theFirstSpell(int costMana) {
        if(checkMana(costMana)){
            setHeroMana(getHeroMana() - costMana);
            return (int) (getHeroLvl() * 1.25 * getHeroDamage());
        }
        //Пишем,что не хватает маны
        else{
            return -1;
        }
    }

    public void theFirstSpell(){
        theFirstSpell(30);
    }

    @Override
    public double theSecondSpell(int costMana) {
        if(checkMana(costMana)){
            setHeroMana(getHeroMana() - costMana);
            return  (1.0 / getHeroLvl());
        }
        else {
            //пишем,что не хватает маны
            return -1;
        }
    }

    public void theSecondSpell(){
        theSecondSpell(40);
    }

    @Override
    public int theUltimate(int costMana) {
        if(checkMana(costMana)) {
            setHeroMana((getHeroMana() - costMana));
            return getHeroLvl() * 2 * getHeroDamage();
        }
        else{
            return -1;
        }
    }
    public void theUltimate(){
        theUltimate(80);
    }

    @Override
    public ArrayList<Object> getSpellByName(String commandUser, String database) {
        return super.getSpellByName(commandUser, database);
    }
}

