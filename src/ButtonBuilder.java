import org.checkerframework.checker.units.qual.Length;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


public class ButtonBuilder extends Init {

    public synchronized void createHeroBut(SendMessage sendMessage, List<String> content, int count) {

// Создаем клавиуатуру

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);


// Создаем список строк клавиатуры

        List<KeyboardRow> keyboard = new ArrayList<>();


        if(count > 0) {

            // Первая строчка клавиатуры

            KeyboardRow keyboardFirstRow = new KeyboardRow();

// Добавляем кнопки в первую строчку клавиатуры и задаем название

            keyboardFirstRow.add(new KeyboardButton(content.get(0)));

            if(count > 1){
                keyboardFirstRow.add(new KeyboardButton(content.get(1)));
            }

            if(count > 2){
                keyboardFirstRow.add(new KeyboardButton(content.get(2)));
            }
            keyboard.add(keyboardFirstRow);
        }

// Вторая строчка клавиатуры

        //KeyboardRow keyboardSecondRow = new KeyboardRow();

// Добавляем кнопки во вторую строчку клавиатуры и зажаем название

        //keyboardSecondRow.add(new KeyboardButton(butName2));

// Вторая строчка клавиатуры

        //KeyboardRow keyboardThirdRow = new KeyboardRow();

// Добавляем кнопки во вторую строчку клавиатуры и зажаем название

        //keyboardThirdRow.add(new KeyboardButton(butName3));

// Добавляем все строчки клавиатуры в список

        //keyboard.add(keyboardFirstRow);
        //keyboard.add(keyboardSecondRow);
        //keyboard.add(keyboardThirdRow);

// и устанваливаем этот список нашей клавиатуре

        replyKeyboardMarkup.setKeyboard(keyboard);
    }

}
