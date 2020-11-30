package status;

public interface Condition {
    Condition getNextCondition(String lastStatus, String command);
    void getMessage(String chatId, String command);
    void changeDate(String chatId, String command);
    String getNameOfCondition();
    void mainMenu(String chatId);
}
