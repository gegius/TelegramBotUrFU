public class Warrior extends Hero  {


    public Warrior(int heath, int mana, int lvl, int damage, int mana1, int mana2, int mana3) {
        super(heath, mana, lvl, damage, mana1, mana2, mana3);
    }

    @Override
    public int Do_The_First_Spell(int lvl, int damage,int mana) {
        return damage;
    }

    @Override
    public int Do_The_Second_Spell(int lvl,int mana) {
        return 0;
    }

    @Override
    public int Ultimate(int lvl, int damage,int mana) {
        var full_damage = damage * damage;
        return full_damage;
    }

}
