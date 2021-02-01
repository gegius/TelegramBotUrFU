package Enemy;

import java.util.List;

public class Enemy {
    private int enemyHP;
    private int enemyDamage;
    private int enemyExp;
    private double enemyResist;
    private  List<String> pullEvent= List.of("Блок", "Удар","Секретный прием");

    public Enemy(int hp, int damage, int exp , double resist){
        this.enemyHP = hp;
        this.enemyDamage = damage;
        this.enemyExp = exp;
        this.enemyResist = resist;
    }

    public int getEnemyDamage() {
        return enemyDamage;
    }

    public int getEnemyExp() {
        return enemyExp;
    }

    //Перед ударом по врагу чекаем его,если больше,то просто вычитаем,если меньше,то его хп 0 и бой окончен
    public void setEnemyHP(int heroDamage) {
        if(getEnemyHP() > heroDamage){
            this.enemyHP = (int) (getEnemyExp() - heroDamage * enemyResist);
        }
        else {
            this.enemyHP = 0;
            //Здесь должен быть конец боя
        }
    }

    public int getEnemyHP() {
        return enemyHP;
    }

    public double getEnemyResist() {
        return enemyResist;
    }


    public void setComment() {

    }
}
