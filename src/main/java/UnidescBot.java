import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class UnidescBot extends TelegramLongPollingBot {

    public void onUpdateReceived(Update update) {
//       System.out.println(update.getMessage().getText());
//       System.out.println(update.getMessage().getFrom().getFirstName()  );

        SendMessage message = new SendMessage();
        String command=update.getMessage().getText();

        if(command.equals("/myname")){
            System.out.println(update.getMessage().getFrom().getFirstName());
            message.setText(update.getMessage().getFrom().getFirstName());
        }
        if(command.equals("/mylastname")){
            System.out.println(update.getMessage().getFrom().getLastName());
            message.setText(update.getMessage().getFrom().getLastName());
        }
        if (command.equals("/myfullname")){
            System.out.println(update.getMessage().getFrom().getFirstName() +" "+ update.getMessage().getFrom().getLastName());
            message.setText(update.getMessage().getFrom().getFirstName()+" "+update.getMessage().getFrom().getLastName());
        }

        message.setChatId(update.getMessage().getChatId());

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public String getBotUsername() {
        return "UnidescBot";
    }

    public String getBotToken() {
        return "903755722:AAEqtodZgPhjGPPCjHzgk9ftKxPY3Jo4s2g";
    }
}
