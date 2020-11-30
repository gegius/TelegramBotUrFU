package status;

import hero.Hero;
import hero.Warrior;
import init.ButtonBuilder;
import init.DataBase;
import init.SendAnswer;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.Arrays;
import java.util.List;

public class WarriorCondition implements Condition{
    DataBase dataBase = new DataBase();
    SendMessage message = new SendMessage();
    SendAnswer answer = new SendAnswer();
    @Override
    public Condition getNextCondition(String lastStatus, String command) {
        if(command.equals(getNameOfCondition()) &&
                (lastStatus.equals("Создать нового персонажа") ||
                lastStatus.equals(command))){
            return null;
        }
        else return new MageCondition();
    }

    @Override
    public void getMessage(String chatId, String command) {
        dataBase.open();
        if(!dataBase.getStatus(chatId).equals(getNameOfCondition())) {
            changeDate(chatId, command);
        }
        message.setText("Ваш класс Воин🗡" + "\n\n\nИсточник: https://clck.ru/RxsJP");
        mainMenu(chatId);
        answer.sendMsg(chatId, message);
        dataBase.close();
    }

    @Override
    public void changeDate(String chatId, String command) {
        Hero hero = new Warrior(200,50,1,20);
        dataBase.setMana(chatId, hero.get_Mana());
        dataBase.setHealth(chatId, hero.get_Heath());
        dataBase.setXP(chatId, hero.get_Lvl());
        dataBase.setDamage(chatId, hero.get_Damage());
        dataBase.setClass(chatId, "Warrior");
        dataBase.setInventory(chatId, "Меч земляка, Щит новобранца");
        dataBase.setStatus(chatId, command);
    }

    @Override
    public String getNameOfCondition() { return "Воин🗡"; }

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
