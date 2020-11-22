import java.lang.reflect.Method;

public class Hero implements Spells{
    private int h_Heath;
    private int h_Mana;
    private int h_Lvl;
    private int h_damage;
    private int mana_cost1;
    private int mana_cost2;
    private int mana_cost3;


    public Hero(int heath , int mana , int lvl , int damage, int mana1, int mana2 , int mana3){
        this.h_Heath = heath;
        this.h_Mana = mana;
        this.h_damage = damage;
        this.h_Lvl = lvl;
        this.mana_cost1 = mana1;
        this.mana_cost3 = mana3;
        this.mana_cost2 = mana2;

    }


    public int get_Heath(){return h_Heath;}
    public int get_Mana(){return h_Mana;}
    public int get_Damage(){return h_damage;}
    public int get_Lvl(){return h_Lvl;}
    public int getMana_cost1(){return mana_cost1;}
    public int getMana_cost2(){ return mana_cost2;}
    public int getMana_cost3(){ return mana_cost3;}


    public void set_Mana(int mana, int exchange){
        this.h_Mana = mana;
    }

    public String get_Stats(){
        return "Ваши статы:" +
                "\nЗдоровье = " +
                get_Heath() + "\nМана = " +
                get_Mana() + "\nУрон = " +
                get_Damage() + "\nУровень = " +
                get_Lvl() + "\n Стоимость ваших способностей:" + "\n Первая сбособность - " +
                getMana_cost1() + " маны \n Вторая спобность - " +
                getMana_cost2() + " маны \n Третья спобоность - " +
                getMana_cost3() + " маны";


    }


    @Override
    public int Do_The_First_Spell(int lvl, int damage, int mana) {
        return 0;
    }

    @Override
    public int Do_The_Second_Spell(int lvl, int mana) {
        return 0;
    }

    @Override
    public int Ultimate(int lvl, int damage, int mana) {
        return 0;
    }
}
