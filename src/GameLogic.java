import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;


public class GameLogic extends Init{

    public static String HeroClass = "";

    public void Game(String command, String chatId){

        if(command.equals("/create_hero")) {
            sendMsg(chatId, "Выберите класс!", "/create_hero");
            HeroClass = "";
        }
        if(command.equals("/start")) {
            sendMsg(chatId, "Здравствуйте, это бот для РПГ игры, пожалуйста," +
                    "продолжите свою игру или начните сначала", "/start");
        }
        if(command.equals("Воин")) {
            if(HeroClass.equals("")){
                sendMsg(chatId, "Ваш класс Воин!", "Воин");
            }
        }
        if(command.equals("Маг")) {
            if(HeroClass.equals("")){
                sendMsg(chatId, "Ваш класс Маг!", "Маг");
            }
        }
    }

    /**
     * Метод для настройки сообщения и его отправки.
     * @param chatId id чата
     * @param s Строка, которую необходимот отправить в качестве сообщения.
     */

    public synchronized void sendMsg(String chatId, String s, String command) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setText(s);

        //смотрим что ответил наш чел и даем ему соответствующие кнопки

        ButtonBuilder Buttons = new ButtonBuilder();
        List<String> mainMenu = Arrays.asList("Inventory", "Stats", "Leave", "Help");


        if(command.equals("/create_hero")) {
            List<String> buttons = Arrays.asList("Маг", "Воин");
            Buttons.createHeroBut(sendMessage, buttons);
        }
        if(command.equals("/start")) {
            List<String> buttons = Arrays.asList("Продолжить", "/create_hero");
            Buttons.createHeroBut(sendMessage, buttons);
        }
        if(command.equals("Воин")){
            Buttons.createHeroBut(sendMessage, mainMenu);
            HeroClass = "Warrior";
        }
        if(command.equals("Маг")){
            Buttons.createHeroBut(sendMessage, mainMenu);
            HeroClass = "Mage";
        }
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
