package status;

import init.ButtonBuilder;
import init.DataBase;
import init.SendAnswer;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import java.util.Arrays;
import java.util.List;

public class DeskorGatesCondition implements Condition {
    DataBase dataBase = new DataBase();
    SendMessage message = new SendMessage();
    SendAnswer answer = new SendAnswer();
    @Override
    public Condition getNextCondition(String lastStatus, String command) {
        if(command.equals(getNameOfCondition(command)) &&
                lastStatus.equals("Начать/продолжить игру")){
            return null;
        }
        else return new MenaceToGuardCondition();
    }

    @Override
    public void sendMessage(String chatId, String command) {
        dataBase.open("users");
        if(!dataBase.getClass(chatId).equals("")) {
            message.setText("Подходя ко входу вы видите двух стражников бурно" +
                    " о чем-то спорящих.\n\nСтражник 1: -Я тебе говорю, издревна мужчин" +
                    " привлекают они...То, о чем ты говоришь это просто перевернутое оно," +
                    " к тому же они обвисают со временем...\n\nСтражник 2: -Обвисают?!" +
                    " А по твоему...\n\nСтражник 1: -Эй, ты ещё кто?\n\n" +
                    "Источник: https://clck.ru/SDgfm");
            List<String> mainMenu = Arrays.asList("Открыть меню", "Я герой!",
                    "Уже ухожу, простите...", "Ты хоть знаешь с кем говоришь, животное?");
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
        return "Подойти к главным воротам";
    }

    @Override
    public void mainMenu(String chatId) {

    }
}
