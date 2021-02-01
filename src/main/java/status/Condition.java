package status;

import java.util.List;

public interface Condition {
    Condition getNextCondition(String lastStatus, String command);
    void sendMessage(String chatId, String command);
    void changeDate(String chatId, String command);
    String getNameOfCondition(String command);
    void mainMenu(String chatId);
}
