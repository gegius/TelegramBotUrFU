package status;

import init.ButtonBuilder;
import init.DataBase;
import init.SendAnswer;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.Arrays;
import java.util.List;

public class RunToForestCondition implements Condition {
    DataBase dataBase = new DataBase();
    SendMessage message = new SendMessage();
    SendAnswer answer = new SendAnswer();
    @Override
    public Condition getNextCondition(String lastStatus, String command) {
        if(command.equals(getNameOfCondition(command)) &&
                lastStatus.equals("Начать/продолжить игру")){
            return null;
        }
        else return null;
    }

    @Override
    public void sendMessage(String chatId, String command) {
        dataBase.open();
        var gamePart = dataBase.getGamePart(chatId);
        if(!dataBase.getClass(chatId).equals("") &&
                (gamePart.equals("Подойти к главным воротам") ||
                        gamePart.equals("Ты хоть знаешь с кем говоришь, животное?") ||
                        gamePart.equals("Начать/продолжить игру") ||
                        gamePart.equals("Ладно. Я герой!") ||
                        gamePart.equals("Я герой!")) ||
                        gamePart.equals(getNameOfCondition(command))) {
            message.setText("Герой побежал в сторону леса и встал перед" +
                    " тяжелым выбором...\n\nИсточник: https://clck.ru/SDm27");
            List<String> mainMenu = Arrays.asList("Открыть меню", "Подойти к главным воротам",
                    "Идти назад в деревню");
            ButtonBuilder Buttons = new ButtonBuilder();
            Buttons.createHeroBut(message, mainMenu);
            dataBase.setGamePart(chatId, getNameOfCondition(command));
        }
        answer.sendMsg(chatId, message);
        dataBase.close();
    }

    @Override
    public void changeDate(String chatId, String command) {

    }

    @Override
    public String getNameOfCondition(String command) {
        if(command.equals("*Убежать*")) return "*Убежать*";
        if(command.equals("Уже ухожу, простите...")) return "Уже ухожу, простите...";
        if(command.equals("Больно надо! *Убежать*")) return "Больно надо! *Убежать*";
        return "Я пойду в лес...";
    }

    @Override
    public void mainMenu(String chatId) {

    }
}
