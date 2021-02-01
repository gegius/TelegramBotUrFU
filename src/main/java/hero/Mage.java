package hero;

import java.util.ArrayList;

public class Mage extends Hero{
    public Mage(int heath, int mana, int lvl, int damage, int experience) {
        super(heath, mana, lvl, damage, experience);
    }

    @Override
    public String getHeroClass() {
        return "Mage";
    }

    @Override
    protected void upgradeStats(int lvl) {
        var currentXP = getHeroMaxHealth() + 10 + lvl * 2;
        var currentMana = getHeroMaxMana() + 20 + lvl * 2;
        setHeroMaxHealth(currentXP);
        setHeroHealth(currentXP);
        setHeroMaxMana(currentMana);
        setHeroMana(currentMana);
    }

}