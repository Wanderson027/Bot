
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
import org.apache.log4j.PropertyConfigurator;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

public class MainClass {
    public static void main(String[] args) {
        //Cria o objeto bot
        TelegramBot bot = TelegramBotAdapter.build("903755722:AAEqtodZgPhjGPPCjHzgk9ftKxPY3Jo4s2g");

        //responsavel por receber as mensagens
        GetUpdatesResponse updatesResponse;

        //Responsavel por gerenciar o arquivo de respostas
        SendResponse sendResponse;

        //Responsavel por gerenciar o envio de açoes do chat
        BaseResponse baseResponse;

        // controle de off-set, a partir desse id sera feita a leitura das mensagens
        int m=0;

        while(true){

            //ex comando no telegram para obter mensagens pendentes
            updatesResponse = bot.execute(new GetUpdates().limit(100).offset(m));

            //ista de mensagens
            List<Update> updates = updatesResponse.updates();

            //analise de cada ação da mensagem
            for(Update update : updates){
                //atualizando o offset
                m=update.updateId()+1;

                System.out.println("Recebendo Mensagem: "+update.message().text());
                if(update.message().text().equals("ne")){
                    sendResponse = bot.execute(new SendMessage(update.message().chat().id(),"NMeu nome é Bot!!"));
                }

                //envio de "Escrevendo" antes de enviar a resposta
                baseResponse = bot.execute(new SendChatAction(update.message().chat().id(), ChatAction.typing.name()));

                //verificação de ação de chat foi enviada com sucesso
                System.out.println("Resposta de Chat Action Enviada?" + baseResponse.isOk());

                //envio da mensagem de resposta

                sendResponse = bot.execute(new SendMessage(update.message().chat().id(),"Não entendi, por favor repita!"));

                //verificação de mensagem enviada com sucesso
                System.out.println("Mensagem Enviada?" +sendResponse.isOk());

            }
        }


        //*****************************************outro projeto porem decidi manter ***************************************************************

       // ApiContextInitializer.init();
      //  TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
      // try {
         //   telegramBotsApi.registerBot(new UnidescBot());

       // } catch (TelegramApiException e) {
            //e.printStackTrace();
       // }



    }
}
