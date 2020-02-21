
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.TelegramBotAdapter;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ChatAction;
import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.request.SendChatAction;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.BaseResponse;
import com.pengrad.telegrambot.response.GetUpdatesResponse;
import com.pengrad.telegrambot.response.SendResponse;

import java.util.List;

public class MainClass {
    public static void main(String[] args) {
        TelegramBot bot = TelegramBotAdapter.build("903755722:AAEqtodZgPhjGPPCjHzgk9ftKxPY3Jo4s2g");
        GetUpdatesResponse updatesResponse;
        SendResponse sendResponse;
        BaseResponse baseResponse;

        int m=0;

        while(true){
            updatesResponse = bot.execute(new GetUpdates().limit(100).offset(m));
            List<Update> updates = updatesResponse.updates();
            EnviarEmail enviarEmail = new EnviarEmail();

           String  emailBot = "teste@hotmail.com";
           String  senhaBot = "12345678";

            if(updates != null) {
	            for(Update update : updates){
	                m=update.updateId()+1;
	                System.out.println("Recebendo Mensagem: "+update.message().text());
	                String emailUsuario = update.message().text();
	                if(update.message().text().contains("@")) {
	                	enviarEmail.sendEmail(emailUsuario, emailBot, senhaBot);
	                }

                    if(update.message().text().equals("ne")){
                        sendResponse = bot.execute(new SendMessage(update.message().chat().id(),"NMeu nome é Bot!!"));
                    }

	                baseResponse = bot.execute(new SendChatAction(update.message().chat().id(), ChatAction.typing.name()));
	                System.out.println("Resposta de Chat Action Enviada?" + baseResponse.isOk());

	                sendResponse = bot.execute(new SendMessage(update.message().chat().id(),"N�o entendi, por favor repita!"));
	                System.out.println("Mensagem Enviada?" +sendResponse.isOk());

	            }
           }
        }

    }
}