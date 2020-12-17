package init;
import java.sql.*;

public class DataBase {
    Connection co;

    //Добавление пользователя в базу данных

    public void insert(String id, String status){
        try {
            String query =
                    "INSERT INTO users (id, heroClass, status, gamePart, heroHealth," +
                            " heroMana, heroLevel, heroDamage, heroInventory, heroExperience) " +
                            "VALUES ('" + id + "', '" + "" + "', '" +
                            status + "', '" + "" + "', '" + 1 + "', '" + 1
                            + "', '" + 1 + "', '" + 1 +
                            "', '" + "" + "', '" + 1 + "' ) ";
            Statement statement = co.createStatement();
            statement.executeUpdate(query);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    //Закрытие базы данных

    public void close(){
        try{
        co.close();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    //Обновление значения в базе данных

    void update(String ID, String name, String newValue){
        try {
            String query =
                    "UPDATE users SET " +
                            name + " = " +
                            "\""+ newValue + "\""
                            + " WHERE " + "id = " + ID;
            Statement statement = co.createStatement();
            statement.executeUpdate(query);

        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    //Удаление базы данных

    public void delete(String ID){
        try {
            String query =
                    "DELETE FROM users WHERE id = " + ID;
            Statement statement = co.createStatement();
            statement.executeUpdate(query);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    //Получение данных из базы

    String select(String ID, String request){
        var content = "";
        try{
            Statement statement = co.createStatement();
            String query =
                    "SELECT *" +
                            " FROM users " +
                            "WHERE id = " + ID;
            ResultSet rs = statement.executeQuery(query) ;
            while (rs.next()){
                content = rs.getString(request);
            }
            rs.close();
            statement.close();
            return content;
        }
        catch (Exception e){
            return content;
        }
    }

    //Открытие базы данных

    public void open(){
        try {
            Class.forName("org.sqlite.JDBC");
            co = DriverManager.getConnection(
                    "jdbc:sqlite:users.db");
            System.out.println("Connected");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
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

    public void setExperience(String ID, int experience0){ update(ID, "heroExperience", String.valueOf(experience0));}
}
