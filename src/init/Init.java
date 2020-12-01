package init;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import status.Condition;

import java.util.HashMap;
import java.util.Map;


public class Init extends TelegramLongPollingBot {

    Map<String, Condition> cacheOfConditions = new HashMap<>();

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
        //возвращаем юзер.
    }

    @Override
    public String getBotToken() {
        return "1175262977:AAH9nkBQNOASQQ-V7dP_TaOs7iiBIef0Qo8";
        //Токен бота
    }

    @Override
    public void onUpdateReceived(Update update) {
        GameLogic game = new GameLogic();
        System.out.println(update.getMessage().getChat().getFirstName());
        String message = update.getMessage().getText();
        String chatId = update.getMessage().getChatId().toString();
        game.game(message, chatId, cacheOfConditions);
    }





}