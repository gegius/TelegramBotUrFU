package status;

import init.ButtonBuilder;
import init.DataBase;
import init.SendAnswer;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import java.util.Arrays;
import java.util.List;

public class InventoryCondition implements Condition{
    DataBase dataBase = new DataBase();
    SendMessage message = new SendMessage();
    SendAnswer answer = new SendAnswer();
    @Override
    public Condition getNextCondition(String lastStatus, String command) {
        if(command.equals(getNameOfCondition(command))){
            return null;
        }
        return new StatisticCondition();
    }

    @Override
    public void sendMessage(String chatId, String command) {
        dataBase.open("users");
        if(!dataBase.getClass(chatId).equals("")){
            changeDate(chatId, command);
        }
        else message.setText("У вас нет героя");
        answer.sendMsg(chatId, message);
        dataBase.close();

    }

    @Override
    public void changeDate(String chatId, String command) {
        var inventory = dataBase.getInventory(chatId);
        List<String> mainMenu = Arrays.asList(inventory);
        ButtonBuilder Buttons = new ButtonBuilder();
        Buttons.createHeroBut(message, mainMenu);
        message.setText("Ваш инвентарь");
    }

    @Override
    public String getNameOfCondition(String command) {
        return "Инвентарь";
    }

    @Override
    public void mainMenu(String chatId) {

    }
}
