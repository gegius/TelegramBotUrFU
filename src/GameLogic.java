import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

//конечный автомат




public class GameLogic extends Init{

    //Инициализация базы данных и обработка сообщений

    public void Game(String command, String chatId){
        DataBase dataBase = new DataBase();
        dataBase.open();
        sendMsg(chatId, command, dataBase);
        dataBase.close();
    }

    /**
     * Метод для настройки сообщения и его отправки.
     * @param chatId id чата
     * @param command Строка, которую необходимот отправить в качестве сообщения.
     * @param dataBase База данных.
     */
    public synchronized void sendMsg(String chatId, String command, DataBase dataBase) {
        var status = getStatus(dataBase, chatId);
        System.out.println("\"" + status + "\"");
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        boolean next = false;
        System.out.println(command);



        //смотрим что ответил наш чел и даем ему соответствующие кнопки

        ButtonBuilder Buttons = new ButtonBuilder();

        if(command.equals("/start") || status.equals("Удалить текущий прогресс и начать новую игру")) {
            if(status.equals("") || status.equals("/start")){
                sendMessage.setText("Здравствуйте, это бот для РПГ игры, пожалуйста, " +
                        "продолжите свою игру или начните сначала");
                List<String> buttons = Arrays.asList("Продолжить", "Создать нового персонажа");
                Buttons.createHeroBut(sendMessage, buttons);
            }
            else{
                setStatus(dataBase, chatId, status);
                sendMsg(chatId, getStatus(dataBase, chatId), dataBase);
            }
        }

        if(command.equals("Продолжить")){
            var data = dataBase.select(chatId);
            if(data.get(0).equals("False")){
                sendMessage.setText("В базе нет вашего персонажа, вам придется создать нового");
                List<String> buttons = Collections.singletonList("Создать нового персонажа");
                Buttons.createHeroBut(sendMessage, buttons);
            }
            else{
                sendMsg(chatId, getStatus(dataBase, chatId), dataBase);
            }
        }

        if(command.equals("Создать нового персонажа")){
            try {
                dataBase.insert(chatId, getClass(dataBase, chatId), command
                );
                List<String> buttons = Arrays.asList("Маг\uD83D\uDD2E", "Воин\uD83D\uDDE1", "Лучник\uD83C\uDFF9");
                Buttons.createHeroBut(sendMessage, buttons);
                sendMessage.setText("Выберите класс!");
                next = true;
            }
            catch (Exception e) {
                sendMessage.setText("В базе уже есть ваш персонаж, удалите старого или напишите \"Продолжить\"");
                sendMsg(chatId, getStatus(dataBase, chatId), dataBase);
            }
        }

        if(command.equals("Воин\uD83D\uDDE1") && (status.equals("Создать нового персонажа") ||
                status.equals("Воин\uD83D\uDDE1"))){
            Hero hero = new Warrior(200,50,1,20);
            setMana(dataBase, chatId, hero.get_Mana());
            setHealth(dataBase, chatId, hero.get_Heath());
            setXP(dataBase, chatId, hero.get_Lvl());
            setDamage(dataBase, chatId, hero.get_Damage());
            mainMenu(sendMessage, dataBase, chatId);
            setClass(dataBase, chatId, "Warrior");
            setInventory(dataBase, chatId, "Меч земляка, Щит новобранца");
            sendMessage.setText("Ваш класс Воин!\uD83D\uDDE1" + "\n\n\nИсточник: https://clck.ru/RxsJP");
            next = true;
        }
        if(command.equals("Маг\uD83D\uDD2E") && (status.equals("Создать нового персонажа") ||
                status.equals("Маг\uD83D\uDD2E"))){
            Hero hero = new Mage(100 , 200 , 1 , 10);
            setMana(dataBase, chatId, hero.get_Mana());
            setHealth(dataBase, chatId, hero.get_Heath());
            setXP(dataBase, chatId, hero.get_Lvl());
            setDamage(dataBase, chatId, hero.get_Damage());
            mainMenu(sendMessage, dataBase, chatId);
            setClass(dataBase, chatId, "Mage");
            setInventory(dataBase, chatId, "Посох ученика");
            sendMessage.setText("Ваш класс Маг!\uD83D\uDD2E" + "\n\n\nИсточник: https://clck.ru/RxsHE");
            next = true;

        }
        if(command.equals("Лучник\uD83C\uDFF9") && (status.equals("Создать нового персонажа") ||
                status.equals("Лучник\uD83C\uDFF9"))){
            Hero hero = new Archer(100 , 100 , 1 , 15);
            setMana(dataBase, chatId, hero.get_Mana());
            setHealth(dataBase, chatId, hero.get_Heath());
            setXP(dataBase, chatId, hero.get_Lvl());
            setDamage(dataBase, chatId, hero.get_Damage());
            mainMenu(sendMessage, dataBase, chatId);
            setClass(dataBase, chatId, "Archer");
            setInventory(dataBase, chatId, "Лук охотника");
            sendMessage.setText("Ваш класс Лучник!\uD83C\uDFF9" +"\n\n\nИсточник: https://clck.ru/RykAm");
            next = true;

        }
        if(command.equals("Статистика") && !getClass(dataBase, chatId).equals("None")){
            Hero hero = new Hero(getHealth(dataBase, chatId),
                    getMana(dataBase, chatId), getXP(dataBase, chatId),
                    getDamage(dataBase, chatId));
            sendMessage.setText(hero.get_Stats());
            mainMenu(sendMessage, dataBase, chatId);
            next = true;
        }

        if(command.equals("Удалить текущий прогресс и начать новую игру") &&
                !getClass(dataBase, chatId).equals("None")) {
            dataBase.delete(chatId);
            sendMessage.setText("Ваш прогресс и герой были жестоко уничтожены по вашей воле," +
                    " пожалуйста, начните новую игру");
            next = true;
        }

        if(command.equals("Описание способностей") && (!getClass(dataBase, chatId).equals("None"))){
            sendMessage.setText("Пока недоступно");
            List<String> buttons = Arrays.asList("Название первой способности", "второй", "третьей");
            Buttons.createHeroBut(sendMessage, buttons);
            mainMenu(sendMessage, dataBase, chatId);
        }

         //заменить на инвентарь потом
        //использовать для теста способностей
        if(!(getClass(dataBase, chatId).equals("None")) && command.equals("Тест урона (60)")){
            sendMessage.setText("Вы получили 60 урона! \uD83D\uDCA5");
            setHealth(dataBase, chatId,getHealth(dataBase, chatId) - 60);
            mainMenu(sendMessage, dataBase, chatId);
        }

        if(command.equals("Начать/продолжить игру") && (!getClass(dataBase, chatId).equals("None"))){
            sendMessage.setText("Пока недоступно");
            mainMenu(sendMessage, dataBase, chatId);
        }
        if(getHealth(dataBase, chatId) <= 0 && !getClass(dataBase, chatId).equals("None")){
             dataBase.delete(chatId);
             sendMessage.setText("Ваш прогресс и герой были жестоко уничтожены не по вашей воле," +
                     " пожалуйста, начните новую игру");
             next = false;
        }

        try {
            execute(sendMessage);
            if(next) {
                setStatus(dataBase, chatId, command);
            }
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


    //заменить на инвентарь "тест урона (60)"
    //Создание главного меню
    public void mainMenu(SendMessage message, DataBase dataBase, String ID){
        List<String> mainMenu = Arrays.asList("Здоровье " + getHealth(dataBase, ID) +
                        "❤", "Мана " + getMana(dataBase, ID) + "\uD83C\uDF0A",
                "Статистика", "Описание способностей", "Начать/продолжить игру",
                "Тест урона (60)", "Удалить текущий прогресс и начать новую игру");
        ButtonBuilder Buttons = new ButtonBuilder();
        Buttons.createHeroBut(message, mainMenu);
    }

    //Получение класса из БД

    public String getClass(DataBase dataBase, String ID){
        try {
            return dataBase.select(ID).get(2);
        }
        catch (Exception e){
            return "None";
        }
    }

    //Запись класса в БД

    public void setClass(DataBase dataBase, String ID, String heroClass){
        dataBase.update(ID, "heroClass", heroClass);
    }

    //Получение текущей позиции автомата из БД

    public String getStatus(DataBase dataBase, String ID){
        try{
        return dataBase.select(ID).get(3);
        }
        catch (Exception e){
            return "";
        }
    }

    //Запись позиции автомата в БД

    private void setStatus(DataBase dataBase, String ID, String status) {
        dataBase.update(ID, "status", status);
    }

    //Получение здоровья из БД

    public int getHealth(DataBase dataBase, String ID){
        try {
            return Integer.parseInt(dataBase.select(ID).get(4));
        }
        catch (Exception e){
            return 0;
        }
    }

    //Запись здоровья в БД

    public void setHealth(DataBase dataBase, String ID, int hp){
        dataBase.update(ID, "heroHealth", String.valueOf(hp));
    }

    //Получение маны из БД

    public int getMana(DataBase dataBase, String ID){
        try {
            return Integer.parseInt(dataBase.select(ID).get(5));
        }
        catch (Exception e){
            return 0;
        }
    }

    //Запись маны в БД

    public void setMana(DataBase dataBase, String ID, int mana){
        dataBase.update(ID, "heroMana", String.valueOf(mana));
    }

    //Получение текущего опыта из БД

    public int getXP(DataBase dataBase, String ID){
        try {
            return Integer.parseInt(dataBase.select(ID).get(6));
        }
        catch (Exception e){
            return 0;
        }
    }

    //Запись текущего опыта в БД

    public void setXP(DataBase dataBase, String ID, int lvl){
        dataBase.update(ID, "heroLevel", String.valueOf(lvl));
    }

    //Получение текущего значения атаки героя из БД

    public int getDamage(DataBase dataBase, String ID){
        try {
            return Integer.parseInt(dataBase.select(ID).get(7));
        }
        catch (Exception e){
            return 0;
        }
    }

    //Запись текущего урона в БД

    public void setDamage(DataBase dataBase, String ID, int damage){
        dataBase.update(ID, "heroDamage", String.valueOf(damage));
    }

    //Получение инвенторя из БД (Пока не используется)

    //public String getInventory(DataBase dataBase, String ID){
    //    return dataBase.select(ID).get(8);
    //}

    //Запись инвенторя в БД

    public void setInventory(DataBase dataBase, String ID, String item){
        dataBase.update(ID, "heroInventory", String.valueOf(item));
    }

}