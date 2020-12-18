package hero;

public class Mage extends Hero implements Spells{
    public Mage(int heath, int mana, int lvl, int damage, int experience) {
        super(heath, mana, lvl, damage, experience);
    }

    @Override
    public boolean checkMana(int costMana) {
        return getHeroMana() > costMana;
    }

    @Override
    public int theFirstSpell(int costMana) {
        if (checkMana(costMana)){
            setHeroMana(getHeroMana() - costMana);
            return (int) (getHeroDamage() + 1.5 * getHeroLvl());
        }
        //Пишем,что нет маны
        else {
            return -1;}
        }


    public void theFirstSpell(){
        theFirstSpell(30);
    }

    @Override
    public double theSecondSpell(int costMana) {
        if(checkMana(costMana)){
            setHeroMana(getHeroMana() - costMana);
            return 1.0 / getHeroLvl();
        }
        //Пишем,что нет маны
        else {
            return -1;
        }
    }

    public void theSecondSpell(){
        theFirstSpell(40);
    }

    @Override
    public int theUltimate(int costMana) {
        if(checkMana(costMana)){
            int mana = getHeroMana();
            setHeroMana(getHeroMana() - costMana);
            return mana * getHeroLvl();
        }
        // пишем,что нет маны
        else {
            return -1;
        }
    }

    @Override
    public String theFirstSpellDescription() {
        return "Огненный шар";
    }

    @Override
    public String theSecondSpellDescription() {
        return "Магическая броня";
    }

    @Override
    public String theUltimateDescription() {
        return "Солнечный луч";
    }

    public void theUltimate(){
        theUltimate(100);
    }


}