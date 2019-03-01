package pubsub_fanout;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class EmitLogs {

	private static final String EXCHANGE_NAME="logs";
	
	public static void main(String[] args) throws Exception {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		try(Connection connection = factory.newConnection();
				Channel channel = connection.createChannel();){
				channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
				String message = "log: logging";
				for (int i = 0; i < 5; i++) {
					message +=i;
					channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes("UTF-8"));	
				}
				
				System.out.println("[x] sent '"+message+"'");
		};
	}
}
