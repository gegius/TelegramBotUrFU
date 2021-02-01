package init;

import com.google.gson.Gson;

public class UsersDB {

    DataBase dataBase = new DataBase();

    private String getGameInform(String ID){
        dataBase.open("users");

        var json = new Gson();
        var gameInfo = dataBase.select(ID, "gameInfo");
        var jsonInform = json.toJson(gameInfo);
        var heroInfo = dataBase.select(ID, "hero");
        var userSpells = dataBase.select(ID, "spells");
        var userInvent = dataBase.select(ID, "inventory");
        return jsonInform;
    }

    public String getClass(String ID){

        try {
            return select(ID, "heroClass");
        }
        catch (Exception e){
            return "None";
        }
    }

    //Запись класса в БД

    public void setClass(String ID, String heroClass){
        update(ID, "heroClass", heroClass);
    }

    //Получение текущей позиции автомата из БД

    public String getStatus(String ID){
        try{
            return select(ID, "status");
        }
        catch (Exception e){
            return "";
        }
    }

    //Запись позиции автомата в БД

    public void setStatus(String ID, String status) {
        update(ID, "status", status);
    }

    //Получение части игры

    public String getGamePart(String ID){
        try{
            return select(ID, "gamePart");
        }
        catch (Exception e){
            return "";
        }
    }

    //Запись позиции автомата в БД

    public void setGamePart(String ID, String gamePart) {
        update(ID, "gamePart", gamePart);
    }

    //Получение здоровья из БД

    public int getHealth(String ID){
        try {
            return Integer.parseInt(select(ID, "heroHealth"));
        }
        catch (Exception e){
            return 0;
        }
    }

    //Запись здоровья в БД

    public void setHealth(String ID, int hp){
        update(ID, "heroHealth", String.valueOf(hp));
    }

    //Получение маны из БД

    public int getMana(String ID){
        try {
            return Integer.parseInt(select(ID, "heroMana"));
        }
        catch (Exception e){
            return 0;
        }
    }

    //Запись маны в БД

    public void setMana(String ID, int mana){
        update(ID, "heroMana", String.valueOf(mana));
    }

    //Получение текущего опыта из БД

    public int getLVL(String ID){
        try {
            return Integer.parseInt(select(ID, "heroLevel"));
        }
        catch (Exception e){
            return 0;
        }
    }

    //Запись текущего опыта в БД

    public void setLvl(String ID, int lvl){
        update(ID, "heroLevel", String.valueOf(lvl));
    }

    //Получение текущего значения атаки героя из БД

    public int getDamage(String ID){
        try {
            return Integer.parseInt(select(ID, "heroDamage"));
        }
        catch (Exception e){
            return 0;
        }
    }

    //Запись текущего урона в БД

    public void setDamage(String ID, int damage){
        update(ID, "heroDamage", String.valueOf(damage));
    }

    //Получение инвенторя из БД (Пока не используется)

    public String[] getInventory(String ID){
        var stringInv = select(ID, "heroInventory");
        return stringInv.split(",");


    }

    //Запись инвенторя в БД

    public void setInventory(String ID, String item){
        update(ID, "heroInventory", String.valueOf(item));
    }

    // Получение текущего уровня героя из БД

    public int getExperience(String ID){
        try{
            return Integer.parseInt(select(ID,"heroExperience"));
        }
        catch (Exception e){
            return 0;
        }
    }

    //Запись текущего опыта в БД

    public void setExperience(String ID, int experience) {
        var postFightExperience = experience + getExperience(ID);
        if (postFightExperience >= 100) {
            var giveLvl = experience / 100;
            setLvl(ID, giveLvl + getLVL(ID));
            update(ID, "heroExperience", String.valueOf(postFightExperience % 100 + getExperience(ID)));
        } else {
            update(ID, "heroExperience", String.valueOf(getExperience(ID) + experience));
        }
    }
}
