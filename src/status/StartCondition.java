package status;

import init.DataBase;
import init.SendAnswer;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import init.ButtonBuilder;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class StartCondition implements Condition {
    DataBase dataBase = new DataBase();
    SendMessage message = new SendMessage();
    SendAnswer answer = new SendAnswer();

    @Override
    public Condition getNextCondition(String lastStatus, String command) {
        if(command.equals("/start")){
            return null;
        }
        else return new CreateHeroCondition();
    }

    @Override
    public void getMessage(String chatId, String command) {
        dataBase.open();
        if(dataBase.getStatus(chatId).equals("")){
            message.setText("Здравствуйте, это бот для РПГ игры, пожалуйста, " +
                    "продолжите свою игру или начните сначала");
            List<String> content = Collections.singletonList("Создать нового персонажа");
            ButtonBuilder Buttons = new ButtonBuilder();
            Buttons.createHeroBut(message, content);
        }
        else{
            message.setText("Нажмите на кнопку, чтобы снова получить последнее сообщение");
            List<String> content = Collections.singletonList(dataBase.getStatus(chatId));
            ButtonBuilder Buttons = new ButtonBuilder();
            Buttons.createHeroBut(message, content);
        }
        answer.sendMsg(chatId, message);
        dataBase.close();
    }

    @Override
    public void changeDate(String chatId, String command) {
    }

    @Override
    public String getNameOfCondition() {
        return "/start";
    }

    @Override
    public void mainMenu(String chatId) {

    }
}
