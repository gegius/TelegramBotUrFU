import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class Init extends TelegramLongPollingBot {

    public static void main(String[] args) {
        // Initialize Api Context
        ApiContextInitializer.init();

        // Instantiate Telegram Bots API
        TelegramBotsApi botsApi = new TelegramBotsApi();

        // Register our bot
        try {
            botsApi.registerBot(new Init());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return "RPGtask_bot";
        //возвращаем юзера
    }

    @Override
    public String getBotToken() {
        return "1175262977:AAH9nkBQNOASQQ-V7dP_TaOs7iiBIef0Qo8";
        //Токен бота
    }

    @Override
    public void onUpdateReceived(Update update) {
        String message = update.getMessage().getText();
        if(message.equals("/create_hero")) {
            sendMsg(update.getMessage().getChatId().toString(), "Выберите класс!", "/create_hero");
        }
        if(message.equals("/start")) {
            sendMsg(update.getMessage().getChatId().toString(), "Здравствуйте, это бот для РПГ игры, пожалуйста," +
                    "продолжите свою игру или начните сначала", "/start");
        }
        if(message.equals("Воин")) {
            sendMsg(update.getMessage().getChatId().toString(), "Ваш класс Воин!", "Воин");
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

        if(command.equals("/create_hero")) {
          createHeroBut(sendMessage, "Маг", "Воин");
        }
        if(command.equals("/start")) {
            createHeroBut(sendMessage, "Продолжить", "/create_hero");
        }
        if(command.equals("Воин")){
            createHeroBut(sendMessage, "Задать статы", "Задать описание");
        }
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


    public synchronized void createHeroBut(SendMessage sendMessage, String butName1, String butName2) {

// Создаем клавиуатуру

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);


// Создаем список строк клавиатуры

        List<KeyboardRow> keyboard = new ArrayList<>();


// Первая строчка клавиатуры

        KeyboardRow keyboardFirstRow = new KeyboardRow();

// Добавляем кнопки в первую строчку клавиатуры и задаем название

        keyboardFirstRow.add(new KeyboardButton(butName1));


// Вторая строчка клавиатуры

        KeyboardRow keyboardSecondRow = new KeyboardRow();

// Добавляем кнопки во вторую строчку клавиатуры и зажаем название

        keyboardSecondRow.add(new KeyboardButton(butName2));


// Добавляем все строчки клавиатуры в список

        keyboard.add(keyboardFirstRow);
        keyboard.add(keyboardSecondRow);

// и устанваливаем этот список нашей клавиатуре

        replyKeyboardMarkup.setKeyboard(keyboard);
    }

}