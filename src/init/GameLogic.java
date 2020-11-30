package init;
import status.Condition;
import status.StartCondition;

import java.util.*;
//конечный автомат




public class GameLogic extends Init {

    DataBase dataBase = new DataBase();
    //Инициализация базы данных и обработка сообщений

    public void game(String command, String chatId, Map<String, Condition> cacheOfConditions){
        dataBase.open();
        var status = dataBase.getStatus(chatId);
        Condition condition= new StartCondition();
        if(cacheOfConditions.containsKey(command)) {
            condition = cacheOfConditions.get(command);
        }
        else {
            while(!condition.getNameOfCondition().equals(command)){
                cacheOfConditions.put(condition.getNameOfCondition(), condition);
                condition = condition.getNextCondition(status, command);
            }
        }
        if(condition.getNextCondition(status, command) == null) {
            condition.getMessage(chatId, command);
        }
        dataBase.close();
    }
}