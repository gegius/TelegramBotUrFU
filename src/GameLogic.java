import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;


public class GameLogic extends Init{

    public void Game(String command, String chatId){

        if(command.equals("/create_hero")) {
            sendMsg(chatId, "Выберите класс!", "/create_hero");
        }
        if(command.equals("/start")) {
            sendMsg(chatId, "Здравствуйте, это бот для РПГ игры, пожалуйста," +
                    "продолжите свою игру или начните сначала", "/start");
        }
        if(command.equals("Воин")) {
            sendMsg(chatId, "Ваш класс Воин!", "Воин");
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


        if(command.equals("/create_hero")) {
            List<String> buttons = Arrays.asList("Маг", "Воин");
            Buttons.createHeroBut(sendMessage, buttons);
        }
        if(command.equals("/start")) {
            List<String> buttons = Arrays.asList("Продолжить", "/create_hero");
            Buttons.createHeroBut(sendMessage, buttons);
        }
        if(command.equals("Воин")){
            List<String> buttons = Arrays.asList("Stats", "Info");
            Buttons.createHeroBut(sendMessage, buttons);
        }
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
