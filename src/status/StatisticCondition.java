package status;

import hero.Hero;
import init.ButtonBuilder;
import init.DataBase;
import init.SendAnswer;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class StatisticCondition implements Condition{
    DataBase dataBase = new DataBase();
    SendMessage message = new SendMessage();
    SendAnswer answer = new SendAnswer();
    @Override
    public Condition getNextCondition(String lastStatus, String command) {
        if(command.equals(getNameOfCondition(command))){
            return null;
        }
        else return new MenuCondition();
    }

    @Override
    public void sendMessage(String chatId, String command) {
        dataBase.open("users");
        if(!dataBase.getClass(chatId).equals("")){
            changeDate(chatId, command);
            mainMenu(chatId);
        }
        else{
            message.setText("У вас нет героя");
            List<String> mainMenu = Collections.singletonList("/start");
            ButtonBuilder Buttons = new ButtonBuilder();
            Buttons.createHeroBut(message, mainMenu);
        }
        answer.sendMsg(chatId, message);
        dataBase.close();
    }

    @Override
    public void changeDate(String chatId, String command) {
        Hero hero = new Hero(dataBase.getHealth(chatId),
                dataBase.getMana(chatId), dataBase.getLVL(chatId),
                dataBase.getDamage(chatId), dataBase.getExperience(chatId));
        message.setText(hero.get_Stats(chatId));

    }

    @Override
    public String getNameOfCondition(String command) {
        return "Статистика";
    }

    @Override
    public void mainMenu(String chatId) {
        List<String> mainMenu = Arrays.asList("Здоровье " + dataBase.getHealth(chatId) +
                        "❤", "Мана " + dataBase.getMana(chatId) + "🌊",
                "Статистика", "Тест урона (60)", "Начать/продолжить игру",
                "Инвентарь", "Удалить текущий прогресс");
        ButtonBuilder Buttons = new ButtonBuilder();
        Buttons.createHeroBut(message, mainMenu);
    }
}
