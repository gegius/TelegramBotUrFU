import java.sql.*;
import java.util.Arrays;
import java.util.List;

public class DataBase {
    Connection co;

    //Добавление пользователя в базу данных

    void insert(String id, String heroClass,
                String status){
        try {
            String query =
                    "INSERT INTO users (id, heroClass, status, heroHealth," +
                            " heroMana, heroLevel, heroDamage, heroInventory) " +
                            "VALUES ('" + id + "', '" + heroClass + "', '" +
                            status + "', '" + 1 + "', '" + 1
                            + "', '" + 1 + "', '" + 1 +
                            "', '" + "" + "') ";
            Statement statement = co.createStatement();
            statement.executeUpdate(query);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    //Закрытие базы данных

    void close(){
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

    void delete(String ID){
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

    List<String> select(String ID){
        var content = Arrays.asList("False");
        try{
            Statement statement = co.createStatement();
            String query =
                    "SELECT * " +
                            " FROM users " +
                            "WHERE id = " + ID;
            ResultSet rs = statement.executeQuery(query) ;
            while (rs.next()){
                String complete = "True";
                String id = rs.getString("id");
                String heroClass = rs.getString("heroClass");
                String status = rs.getString("status");
                String heroHealth = rs.getString("heroHealth");
                String heroMana = rs.getString("heroMana");
                String heroLevel = rs.getString("heroLevel");
                String heroDamage = rs.getString("heroDamage");
                String heroInventory = rs.getString("heroInventory");
                content = Arrays.asList(complete, id, heroClass, status, heroHealth,
                        heroMana, heroLevel, heroDamage, heroInventory);
            }
            rs.close();
            statement.close();
            return content;
        }
        catch (Exception e){
            return content;
        }
    }

    //Открытие базы данныхf

    void open(){
        try {
            Class.forName("org.sqlite.JDBC");
            co = DriverManager.getConnection(
                    "jdbc:sqlite:users.db");
            System.out.println("Connected");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
