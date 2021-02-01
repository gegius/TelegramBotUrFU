package status;

import init.ButtonBuilder;
import init.DataBase;
import init.SendAnswer;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GameFirstPartOrContinue implements Condition{
    DataBase dataBase = new DataBase();
    SendMessage message = new SendMessage();
    SendAnswer answer = new SendAnswer();
    @Override
    public Condition getNextCondition(String lastStatus, String command) {
        if(command.equals(getNameOfCondition(command))){
            return null;
        }
        else return new DeskorGatesCondition();
    }

    @Override
    public void sendMessage(String chatId, String command) {
        dataBase.open("users");
        if(!dataBase.getClass(chatId).equals("")){
            if(dataBase.getGamePart(chatId).equals("")){
                message.setText("Лотос цветёт дважды, наш герой снова решил" + "\n" +
                        "испытать себя и отправиться на поиски приключения, начать" + "\n" +
                        "он решил с города Дескор, два года тренировок в родной деревне около " +
                        "этого большого города не пропали зря. Он покажет на что способен!" + "\n" +
                        "\n" + "Вы стоите у входа в город. Куда направитесь?");
                List<String> mainMenu = Arrays.asList("Открыть меню",
                        "Подойти к главным воротам", "Я пойду в лес...");
                ButtonBuilder Buttons = new ButtonBuilder();
                Buttons.createHeroBut(message, mainMenu);
                dataBase.setGamePart(chatId, getNameOfCondition(command));
            }
            else if(dataBase.getGamePart(chatId).equals(getNameOfCondition(command))){
                dataBase.setGamePart(chatId, "");
                sendMessage(chatId, command);
                return;
            }
            else{
                message.setText("Нажмите на кнопку, чтобы снова получить последнее сообщение");
                List<String> content = Collections.singletonList(dataBase.getGamePart(chatId));
                ButtonBuilder Buttons = new ButtonBuilder();
                Buttons.createHeroBut(message, content);
            }
        }
        dataBase.setStatus(chatId, getNameOfCondition(command));
        answer.sendMsg(chatId, message);
        dataBase.close();
    }

    @Override
    public void changeDate(String chatId, String command) {

    }

    @Override
    public String getNameOfCondition(String command) {
        return "Начать/продолжить игру";
    }

    @Override
    public void mainMenu(String chatId) {

    }
}
