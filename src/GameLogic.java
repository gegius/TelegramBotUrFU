import org.telegram.telegrambots.meta.api.methods.send.SendAnimation;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Arrays;
import java.util.List;

//конечный автомат

public class GameLogic extends Init{

    public static String HeroClass = "";

    public static String Status = "";

    public static String Statistic = "";

    public static Hero hero = new Hero(0 , 0 , 0 , 0, 0 , 0, 0);

    public static int Mana = 0;

    public static int HP = 0;

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
        boolean next = false;
        System.out.println(command);




        //смотрим что ответил наш чел и даем ему соответствующие кнопки

        ButtonBuilder Buttons = new ButtonBuilder();

        if(command.equals("/start") || Status.equals("Удалить текущий прогресс и начать новую игру")) {
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
            List<String> buttons = Arrays.asList("Маг\uD83D\uDD2E", "Воин\uD83D\uDDE1", "Лучник\uD83C\uDFF9");
            Buttons.createHeroBut(sendMessage, buttons);
            sendMessage.setText("Выберите класс!");
            next = true;
        }

        if(command.equals("Воин\uD83D\uDDE1") && (status.equals("Создать нового персонажа") ||
                status.equals("Воин\uD83D\uDDE1"))){
            Hero hero = new Warrior(200,50,1,20, 20 , 30 , 60);
            Statistic = hero.get_Stats();
            Mana = hero.get_Mana();
            HP = hero.get_Heath();
            mainMenu(sendMessage);
            HeroClass = "Warrior";
            sendMessage.setText("Ваш класс Воин!\uD83D\uDDE1" + "\n\n\nИсточник: https://clck.ru/RxsJP");
            next = true;

        }
        if(command.equals("Маг\uD83D\uDD2E") && (status.equals("Создать нового персонажа") ||
                status.equals("Маг\uD83D\uDD2E"))){
            Hero hero = new Mage(100 , 200 , 1 , 10, 20 , 30 , 100);
            Statistic = hero.get_Stats();
            Mana = hero.get_Mana();
            HP = hero.get_Heath();
            mainMenu(sendMessage);
            HeroClass = "Mage";
            sendMessage.setText("Ваш класс Маг!\uD83D\uDD2E" + "\n\n\nИсточник: https://clck.ru/RxsHE");
            next = true;

        }
        if(command.equals("Лучник\uD83C\uDFF9") && (status.equals("Создать нового персонажа") ||
                status.equals("Лучник\uD83C\uDFF9"))){
            Hero hero = new Archor(100 , 100 , 1 , 15, 40 , 30 , 80);
            Statistic = hero.get_Stats();
            Mana = hero.get_Mana();
            HP = hero.get_Heath();
            mainMenu(sendMessage);
            HeroClass = "Archer";
            sendMessage.setText("Ваш класс Лучник!\uD83C\uDFF9" +"\n\n\nИсточник: https://clck.ru/RykAm");
            next = true;

        }
        if(command.equals("Статистика") && (!HeroClass.equals("") )){
            sendMessage.setText(Statistic);
            mainMenu(sendMessage);
            next = true;
        }

        if(command.equals("Удалить текущий прогресс и начать новую игру") && (!HeroClass.equals(""))) {
            HeroClass = "";
            Status = "";
            hero = new Hero(0 , 0 , 0 , 0, 0, 0 ,0);
            Mana = 0;
            HP = 0;
            Statistic = "";
            sendMessage.setText("Ваш прогресс и герой были жестоко уничтожены по вашей воле," +
                    " пожалуйста, начните новую игру");
            next = false;
        }

        if(command.equals("Описание способностей") && (!HeroClass.equals(""))){
            sendMessage.setText("Пока недоступно");
            List<String> buttons = Arrays.asList("Название первой способности", "второй", "третьей");
            Buttons.createHeroBut(sendMessage, buttons);
            mainMenu(sendMessage);
        }

        //заменить на инвентарь потом
        //использовать для теста способностей
        if(command.equals("Тест урона (60)") && (!HeroClass.equals(""))){
            sendMessage.setText("Вы получили 60 урона! \uD83D\uDCA5");
            HP -= 60;
            mainMenu(sendMessage);
        }

        if(command.equals("Начать/продолжить игру") && (!HeroClass.equals(""))){
            sendMessage.setText("Пока недоступно");
            mainMenu(sendMessage);
        }
        if(HP <= 0 && !HeroClass.equals("")){
            HeroClass = "";
            Status = "";
            hero = new Hero(0 , 0 , 0 , 0, 0 , 0 ,0);
            Mana = 0;
            HP = 0;
            Statistic = "";
            sendMessage.setText("Ваш прогресс и герой были жестоко уничтожены не по вашей воле," +
                    " пожалуйста, начните новую игру");
            next = false;
        }

        try {
            execute(sendMessage);
            if(next) {
                Status = command;
            }
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    //заменить на инвентарь "тест урона (60)"
    public void mainMenu(SendMessage message){
        List<String> mainMenu = Arrays.asList("Здоровье " + HP + "❤", "Мана " + Mana + "\uD83C\uDF0A",
                                              "Статистика", "Описание способностей", "Начать/продолжить игру",
                                              "Тест способности: /param/", "Удалить текущий прогресс и начать новую игру");
        ButtonBuilder Buttons = new ButtonBuilder();
        Buttons.createHeroBut(message, mainMenu);
    }
}