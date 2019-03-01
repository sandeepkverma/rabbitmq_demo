package exchange_direct;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class EmitLogsDirect {

	private static final String EXCHANGE_NAME = "logdirect";

	public static void main(String[] args) throws Exception {

		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");

		try (Connection connection = factory.newConnection(); Channel channel = connection.createChannel()) {
			
			channel.exchangeDeclare(EXCHANGE_NAME, "direct");
			
			String[] severities = {"info","info","error","log","log","log"};
			for (String string : severities) {
				String message = string+":logging";
				channel.basicPublish(EXCHANGE_NAME, string, null, message.getBytes("UTF-8"));	
				System.out.println("[x] sent '"+message+"'");
			}
			
			
		}
		;
	}

}
