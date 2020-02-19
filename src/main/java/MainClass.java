
import org.apache.log4j.PropertyConfigurator;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class MainClass {
    public static void main(String[] args) {

        PropertyConfigurator.configure(System.getProperty("user.dir") + "/src/main/resources/log4j.properties");

        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new UnidescBot());

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }



    }
}
