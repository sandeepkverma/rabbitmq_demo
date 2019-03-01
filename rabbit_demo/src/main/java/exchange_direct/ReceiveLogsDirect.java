package exchange_direct;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class ReceiveLogsDirect {

	private static final String EXCHANGE_NAME = "logdirect";

	public static void main(String[] args) throws Exception {

		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		channel.exchangeDeclare(EXCHANGE_NAME, "direct");
		
		String queueName = channel.queueDeclare().getQueue();
		
		String[] severities = {"info","error","log"};
//		String[] severities = {"error"};
		for (String string : severities) {
			channel.queueBind(queueName, EXCHANGE_NAME, string);	
		}
		
		System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
		
		DeliverCallback deliverCallback = (consumerTag,deliver)->{
			String message = new String(deliver.getBody(),"UTF-8");
			System.out.println("[x] Received '"+message+"'");
		};
		
		channel.basicConsume(queueName, true,deliverCallback,consumerTag->{});
		
		
		
	}

}
