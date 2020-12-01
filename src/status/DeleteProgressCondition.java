package status;

import init.ButtonBuilder;
import init.DataBase;
import init.SendAnswer;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.Collections;
import java.util.List;

public class DeleteProgressCondition implements Condition{
    DataBase dataBase = new DataBase();
    SendAnswer send = new SendAnswer();
    SendMessage message = new SendMessage();
    @Override
    public Condition getNextCondition(String lastStatus, String command) {
        if(command.equals(getNameOfCondition(command))){
            return null;
        }
        else {return new InventoryCondition();}
    }

    @Override
    public void sendMessage(String chatId, String command) {
        dataBase.open();
        if (!dataBase.getClass(chatId).equals("")) {
            changeDate(chatId, command);
            message.setText("Ваш прогресс и герой были жестоко уничтожены по вашей воле," +
                    " пожалуйста, начните новую игру");
        }
        else message.setText("У вас нет героя, чтобы его убить или он уже мертв ¯\\_(ツ)_/¯");
        send.sendMsg(chatId, message);
        dataBase.close();
    }

    @Override
    public void changeDate(String chatId, String command) {
        dataBase.delete(chatId);
        ButtonBuilder buttons = new ButtonBuilder();
        List<String> content = Collections.singletonList("/start");
        buttons.createHeroBut(message, content);
    }

    @Override
    public String getNameOfCondition(String command) {
        return "Удалить текущий прогресс";
    }

    @Override
    public void mainMenu(String chatId) {

    }
}
