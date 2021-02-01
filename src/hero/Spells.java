package hero;

public interface Spells {
    boolean checkMana(int costMana);
    int theFirstSpell( int costMana);
    double theSecondSpell( int costMana);
    int theUltimate(int costMana);
}
