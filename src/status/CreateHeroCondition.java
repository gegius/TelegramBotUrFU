package status;

import init.ButtonBuilder;
import init.DataBase;
import init.SendAnswer;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CreateHeroCondition implements Condition{
    DataBase dataBase = new DataBase();
    SendMessage message = new SendMessage();
    SendAnswer answer = new SendAnswer();
    @Override
    public Condition getNextCondition(String lastStatus, String command) {
        if(command.equals(getNameOfCondition())){
            return null;
        }
        else return new ArcherCondition();
    }

    @Override
    public void getMessage(String chatId, String command) {
        dataBase.open();
        if(!dataBase.getClass(chatId).equals("")){
            message.setText("В базе уже есть ваш персонаж, удалите старого или напишите \"/start\"");
            List<String> content = Collections.singletonList("/start");
            ButtonBuilder buttons = new ButtonBuilder();
            buttons.createHeroBut(message, content);
        }
        else{
            changeDate(chatId, command);
            dataBase.setStatus(chatId, getNameOfCondition());
        }
        answer.sendMsg(chatId, message);
        dataBase.close();
    }

    @Override
    public void changeDate(String chatId, String command){
        ButtonBuilder buttons = new ButtonBuilder();
        dataBase.insert(chatId, command);
        List<String> content = Arrays.asList("Маг\uD83D\uDD2E", "Воин🗡", "Лучник🏹");
        message.setText("Выберите класс!");
        buttons.createHeroBut(message, content);
    }

    @Override
    public String getNameOfCondition() {
        return "Создать нового персонажа";
    }

    @Override
    public void mainMenu(String chatId) {

    }
}
