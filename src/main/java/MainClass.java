
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
import java.util.Random;

public class MainClass {
   
	private static Integer token;
   
	public static void main(String[] args) {
        TelegramBot bot = TelegramBotAdapter.build("903755722:AAEqtodZgPhjGPPCjHzgk9ftKxPY3Jo4s2g");
        GetUpdatesResponse updatesResponse;
        SendResponse sendResponse;
        BaseResponse baseResponse;
        token = 0;

        int m=0;

        while(true){
            updatesResponse = bot.execute(new GetUpdates().limit(100).offset(m));
            List<Update> updates = updatesResponse.updates();
            EnviarEmail enviarEmail = new EnviarEmail();

            String  emailBot = "noreplybotunidesc@gmail.com";
            String  senhaBot = "projetob0t2020";
           
            if(!updates.isEmpty()) {
	            for(Update update : updates){
	                m=update.updateId()+1;
	                System.out.println("Recebendo Mensagem: "+update.message().text());
	                String emailUsuario = update.message().text();
	                if(update.message().text().contains("@")) {
	                	sendResponse = bot.execute(new SendMessage(update.message().chat().id(), "Um token de confirmacaoo sera enviado para seu email"));
	                	buildToken();
	                	enviarEmail.sendEmail(emailUsuario, emailBot, senhaBot, token);
	                	if(update.message().text() == token.toString()) {
	                	    sendResponse = bot.execute(new SendMessage(update.message().chat().id(),"Token Confirmado"));
	                	}else {
	                	    sendResponse = bot.execute(new SendMessage(update.message().chat().id(),"Token Incorreto!"));
	                	}
	                }
                    if(update.message().text().equals("ne")){
                        sendResponse = bot.execute(new SendMessage(update.message().chat().id(),"Meu nome e Bot!!"));
                    }
	                baseResponse = bot.execute(new SendChatAction(update.message().chat().id(), ChatAction.typing.name()));
	                System.out.println("Resposta de Chat Action Enviada?" + baseResponse.isOk());
	                sendResponse = bot.execute(new SendMessage(update.message().chat().id(),"Nao entendi, por favor repita!"));
	                System.out.println("Mensagem Enviada?" +sendResponse.isOk());

	            }
           }
        }

    }
	
	public static void buildToken() {
		Random aleatorio = new Random();
		token = aleatorio.nextInt(9999);
		System.out.println("Token =>" + token);
	}

	public static Integer getToken() {
		return token;
	}

	public static void setToken(Integer token) {
		MainClass.token = token;
	}
	
}