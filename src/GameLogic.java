import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Arrays;
import java.util.List;

//конечный автомат

public class GameLogic extends Init{

    public static String HeroClass = "";

    public static String Status = "";

    public static String Statistic = "";

    public static Hero hero = new Hero(0 , 0 , 0 , 0);

    public void Game(String command, String chatId){
        sendMsg(chatId, command, Status);
    }

    /**
     * Метод для настройки сообщения и его отправки.
     * @param chatId id чата
     * @param command Строка, которую необходимот отправить в качестве сообщения.
     */

    public synchronized void sendMsg(String chatId, String command, String status) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);

        //смотрим что ответил наш чел и даем ему соответствующие кнопки

        ButtonBuilder Buttons = new ButtonBuilder();
        List<String> mainMenu = Arrays.asList("Inventory", "Статистика", "Leave", "Help", "asd", "asdsd", "183");

        if(command.equals("/start")) {
            if(status.equals("") || status.equals("/start")){
                List<String> buttons = Arrays.asList("Продолжить", "Создать нового персонажа");
                Buttons.createHeroBut(sendMessage, buttons);
                sendMessage.setText("Здравствуйте, это бот для РПГ игры, пожалуйста, " +
                        "продолжите свою игру или начните сначала");
            }
            else{
                command = Status;
            }
        }

        if(command.equals("Создать нового персонажа") || status.equals("Создать нового персонажа")) {
            List<String> buttons = Arrays.asList("Маг", "Воин");
            Buttons.createHeroBut(sendMessage, buttons);
            sendMessage.setText("Выберите класс!");
        }

        if(command.equals("Воин") && (status.equals("Создать нового персонажа") ||
                status.equals("Воин"))){
            Hero hero = new Warrior(200,50,1,20);
            Statistic = hero.get_Stats();
            Buttons.createHeroBut(sendMessage, mainMenu);
            HeroClass = "Warrior";
            sendMessage.setText("Ваш класс Воин!" + "\nИсточник: https://clck.ru/RxsJP\n" + Statistic);

        }
        if(command.equals("Маг") && (status.equals("Создать нового персонажа") ||
                status.equals("Маг"))){
            Hero hero = new Mage(100 , 200 , 1 , 15);
            Statistic = hero.get_Stats();
            Buttons.createHeroBut(sendMessage, mainMenu);
            HeroClass = "Mage";
            sendMessage.setText("Ваш класс Маг!" + "\nИсточник: https://clck.ru/RxsHE\n" + Statistic);

        }
        if(command.equals("Статистика") && (!HeroClass.equals("") )){
            sendMessage.setText(Statistic);
            Buttons.createHeroBut(sendMessage, mainMenu);
        }

        try {
            execute(sendMessage);
            if(!command.equals("/start")) {
                Status = command;
            }
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}