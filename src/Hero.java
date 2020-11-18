public class Hero {
    private int h_Heath;
    private int h_Mana;
    private int h_Lvl;
    private int h_damage;


    public Hero(int heath , int mana , int lvl , int damage){
        this.h_Heath = heath;
        this.h_Mana = mana;
        this.h_damage = damage;
        this.h_Lvl = lvl;

    }
    public int get_Heath(){return h_Heath;};
    public int get_Mana(){return h_Mana;};
    public int get_Damage(){return h_damage;};
    public int get_Lvl(){return h_Lvl;};

    public String get_Stats(){
        return " Ваши статы" + "\nЗдоровье = " + get_Heath() + "\nМана = " + get_Mana() + "\nУрон = " + get_Damage() + "\nУровень = " + get_Lvl();

    }


}
