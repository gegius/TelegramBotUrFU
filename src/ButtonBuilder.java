import org.checkerframework.checker.units.qual.Length;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


public class ButtonBuilder extends Init {

    public synchronized void createHeroBut(SendMessage sendMessage, List<String> content) {

// Создаем клавиуатуру

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);


// Создаем список строк клавиатуры

        List<KeyboardRow> keyboard = new ArrayList<>();


        if(content.size() > 0) {

            // Первая строчка клавиатуры

            KeyboardRow keyboardFirstRow = new KeyboardRow();

// Добавляем кнопки в первую строчку клавиатуры и задаем название

            keyboardFirstRow.add(new KeyboardButton(content.get(0)));

            if(content.size() > 1){
                keyboardFirstRow.add(new KeyboardButton(content.get(1)));
            }

            if(content.size() > 2){
                keyboardFirstRow.add(new KeyboardButton(content.get(2)));
            }
            keyboard.add(keyboardFirstRow);
        }

// Вторая строчка клавиатуры
        if(content.size() > 3) {
            KeyboardRow keyboardSecondRow = new KeyboardRow();

// Добавляем кнопки во вторую строчку клавиатуры и зажаем название
            keyboardSecondRow.add(new KeyboardButton(content.get(3)));

            if(content.size() > 4){
                keyboardSecondRow.add(new KeyboardButton(content.get(4)));
            }

            if(content.size() > 5){
                keyboardSecondRow.add(new KeyboardButton(content.get(5)));
            }
            keyboard.add(keyboardSecondRow);
        }

// Вторая строчка клавиатуры
        if(content.size() > 6) {
            KeyboardRow keyboardThirdRow = new KeyboardRow();

// Добавляем кнопки во вторую строчку клавиатуры и зажаем название
            keyboardThirdRow.add(new KeyboardButton(content.get(6)));

            if(content.size() > 7){
                keyboardThirdRow.add(new KeyboardButton(content.get(7)));
            }

            if(content.size() > 8){
                keyboardThirdRow.add(new KeyboardButton(content.get(8)));
            }
            keyboard.add(keyboardThirdRow);
        }

// и устанваливаем этот список нашей клавиатуре

        replyKeyboardMarkup.setKeyboard(keyboard);
    }

}
