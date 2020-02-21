import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.SimpleEmail;

public class EnviarEmail{
	
	private String email;
	private String emailBot;
	private String senhaBot;
	
	public void sendEmail(String emailSend, String emailBot , String senhaBot) {
    	SimpleEmail email = new SimpleEmail();
    	email.setHostName("smtp.gmail.com");
    	email.setSmtpPort(587);
    	email.setAuthenticator(new DefaultAuthenticator(emailBot, senhaBot));
    	email.setSSLOnConnect(true);
    	
    	try {
    		
    		email.setFrom(emailBot);
    		email.setSubject("BOT CAA - UNIDESC");
    		email.setMsg("Email teste");
    		email.addTo(emailSend);
    		email.send();
    		System.out.println("Email Enviado!");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmailBot() {
		return emailBot;
	}
	public void setEmailBot(String emailBot) {
		this.emailBot = emailBot;
	}
	public String getSenhaBot() {
		return senhaBot;
	}
	public void setSenhaBot(String senhaBot) {
		this.senhaBot = senhaBot;
	}
	
	
}