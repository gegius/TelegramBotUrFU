package status;
import hero.Archer;
import hero.Hero;
import init.DataBase;
import init.SendAnswer;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import init.ButtonBuilder;

import java.util.Arrays;
import java.util.List;

public class SetClassArcherCondition implements Condition {
    DataBase dataBase = new DataBase();
    SendMessage message = new SendMessage();
    SendAnswer answer = new SendAnswer();
    public Condition getNextCondition(String lastStatus, String command){
        if(command.equals(getNameOfCondition(command
        )) &&
                (lastStatus.equals("Создать нового персонажа") ||
                lastStatus.equals(command))) {
            return null;
        }
        else return new SetClassWarriorCondition();
    }


    @Override
    public void sendMessage(String chatId, String command) {
        dataBase.open();
        if(!dataBase.getStatus(chatId).equals(getNameOfCondition(command))) {
            changeDate(chatId, command);
        }
        message.setText("Ваш класс Лучник!🏹" + "\n\n\nИсточник: https://clck.ru/RykAm");
        mainMenu(chatId);
        answer.sendMsg(chatId, message);
        dataBase.close();
    }

    @Override
    public void changeDate(String chatId, String command) {
        Hero hero = new Archer(150 , 100 , 1 , 15, 0);
        dataBase.setMana(chatId, hero.getHeroMana());
        dataBase.setHealth(chatId, hero.getHeroHeath());
        dataBase.setLvl(chatId, hero.getHeroLvl());
        dataBase.setDamage(chatId, hero.getHeroDamage());
        dataBase.setClass(chatId, "Archer");
        dataBase.setInventory(chatId, "Лук охотника");
        dataBase.setStatus(chatId, command);
    }

    @Override
    public String getNameOfCondition(String command) {
        return "Лучник🏹";
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

