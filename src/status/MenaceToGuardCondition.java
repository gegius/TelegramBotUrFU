package status;

import init.ButtonBuilder;
import init.DataBase;
import init.SendAnswer;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.Arrays;
import java.util.List;

public class MenaceToGuardCondition implements Condition {
    DataBase dataBase = new DataBase();
    SendMessage message = new SendMessage();
    SendAnswer answer = new SendAnswer();
    @Override
    public Condition getNextCondition(String lastStatus, String command) {
        if(command.equals(getNameOfCondition(command)) &&
                lastStatus.equals("Начать/продолжить игру")){
            return null;
        }
        else return new TalkGuardCondition();
    }

    @Override
    public void sendMessage(String chatId, String command) {
        dataBase.open();
        if(!dataBase.getClass(chatId).equals("") &&
                (dataBase.getGamePart(chatId).equals("Подойти к главным воротам") ||
                dataBase.getGamePart(chatId).equals(getNameOfCondition(command)))) {
            message.setText("Стражник 1: -Ты нарываешься.\n\n" +
                    "Стражник 2: -Поверь, пощады не будет...\n\n" +
                    "Источник: https://clck.ru/SDgfm");
            List<String> mainMenu = Arrays.asList("Открыть меню", "Ладно. Я герой!",
                    "*Убежать*", "Я приду на ваши похороны! *Напасть*");
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
        return "Ты хоть знаешь с кем говоришь, животное?";
    }

    @Override
    public void mainMenu(String chatId) {

    }
}
