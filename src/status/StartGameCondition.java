package status;

public class StartGameCondition implements Condition{
    @Override
    public Condition getNextCondition(String lastStatus, String command) {
        return null;
    }

    @Override
    public void getMessage(String chatId, String command) {

    }

    @Override
    public void changeDate(String chatId, String command) {

    }

    @Override
    public String getNameOfCondition() {
        return "Начать/продолжить игру";
    }

    @Override
    public void mainMenu(String chatId) {

    }
}
