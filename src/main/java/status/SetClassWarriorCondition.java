package status;

import hero.Hero;
import hero.Warrior;
import init.ButtonBuilder;
import init.DataBase;
import init.SendAnswer;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.Arrays;
import java.util.List;

public class SetClassWarriorCondition implements Condition{
    DataBase dataBase = new DataBase();
    SendMessage message = new SendMessage();
    SendAnswer answer = new SendAnswer();
    @Override
    public Condition getNextCondition(String lastStatus, String command) {
        if(command.equals(getNameOfCondition(command)) &&
                (lastStatus.equals("Создать нового персонажа") ||
                lastStatus.equals(command))){
            return null;
        }
        else return new SetClassMageCondition();
    }

    @Override
    public void sendMessage(String chatId, String command) {
        dataBase.open("users");
        if(!dataBase.getStatus(chatId).equals(getNameOfCondition(command))) {
            changeDate(chatId, command);
        }
        message.setText("Ваш класс Воин🗡" + "\n\n\nИсточник: https://clck.ru/RxsJP");
        mainMenu(chatId);
        answer.sendMsg(chatId, message);
        dataBase.close();
    }

    @Override
    public void changeDate(String chatId, String command) {
        Hero hero = new Warrior(200,50,1,20, 0);
        dataBase.setMana(chatId, hero.getHeroMana());
        dataBase.setHealth(chatId, hero.getHeroHeath());
        dataBase.setLvl(chatId, hero.getHeroLvl());
        dataBase.setDamage(chatId, hero.getHeroDamage());
        dataBase.setClass(chatId, "Warrior");
        dataBase.setInventory(chatId, "Меч земляка, Щит новобранца");
        dataBase.setStatus(chatId, command);
        dataBase.setExperience(chatId, hero.getHeroExperience());
    }

    @Override
    public String getNameOfCondition(String command) { return "Воин🗡"; }

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
