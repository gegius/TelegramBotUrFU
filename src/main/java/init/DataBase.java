package init;
import hero.Hero;
import java.sql.*;
import java.util.HashMap;


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
                            "', '" + "" + "', '" + 0 + "' ) ";
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

     String select(String ID,  String request) {
        var content = "";
        try {
            Statement statement = co.createStatement();
            String query =
                    "SELECT *" +
                            " FROM users " +
                            "WHERE id = " + ID;
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                content = rs.getString(request);
            }
            rs.close();
            statement.close();
            return content;
        }
        catch (Exception e) {
            return content;
        }
    }

    public HashMap<String,Object> getInfoFromSpells(String spellName){
        HashMap<String,Object> spell = new HashMap<>();
        try {
            Statement statement = co.createStatement();
            String query =
                    "SELECT * FROM spells Where id = " + spellName;
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                spell.put("spellDamage",rs.getInt("spellDamage"));
                spell.put("spellBlock",rs.getInt("spellBlock"));
                spell.put("spellManaCost",rs.getInt("spellManaCost"));
                spell.put("spellDescription",rs.getString("spellDescription"));
                spell.put("spellHeroName",rs.getString("spellHeroName"));
            }
        }
            catch (Exception e) {
                return null;}
        return spell;
    }

    public HashMap<String,Object> getInfoFromInventory(String itemName){
        HashMap<String,Object> item = new HashMap<>();
        try {
            Statement statement = co.createStatement();
            String query =
                    "SELECT * FROM inventory Where id = " + itemName;
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                item.put("itemDamage",rs.getInt("itemDamage"));
                item.put("itemBlock",rs.getInt("itemBlock"));
                item.put("itemBaffMana",rs.getInt("itemBaffMana"));
                item.put("itemBaffHp",rs.getInt("itemBaffHP"));
                item.put("spellDescription",rs.getString("itemDescription"));
            }
        }
        catch (Exception e) {
            return null;}
        return item;
    }


    //Открытие базы данных

    public void open(String database){
        try {
            Class.forName("org.sqlite.JDBC");
            co = DriverManager.getConnection(
                    "jdbc:sqlite:" + database+ ".db");
            System.out.println("Connected");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


}
