public class Mage extends Hero {


    public Mage(int heath, int mana, int lvl, int damage, int mana1, int mana2, int mana3) {
        super(heath, mana, lvl, damage, mana1, mana2, mana3);
    }



    @Override
    public void set_Mana(int mana, int exchange) {
        super.set_Mana(mana, exchange);
    }

    public int Do_The_First_Spell() {
        var new_damage = 0;
        if (getMana_cost1() > get_Mana()){
             set_Mana(get_Mana(), 10);
             new_damage = get_Lvl() * get_Damage();

        }
        else{
            System.out.println("Вам не хватает маны"); // Здесь должно быть сообщение юзеру в телеге(Сделаем метод в гейм лоджике,чтобы выдавало сообщение)

        }
        return new_damage;

    }

    public int Do_The_Second_Spell() {

        if (getMana_cost2() > get_Mana()){
            set_Mana(get_Mana(), 2);;
    }
        return 0;}

    public int Ultimate() {
        var sunray = 0;
        if (getMana_cost3() > get_Mana()){
            sunray = (get_Mana() % 10) * (get_Mana() % 10);
            set_Mana(get_Mana(), 2);
            }

        else {
            System.out.println("Вам не хватает маны"); // Здесь должно быть сообщение юзеру в телеге(Сделаем метод в гейм лоджике,чтобы выдавало сообщение)
        }
        return sunray;
    }

}
