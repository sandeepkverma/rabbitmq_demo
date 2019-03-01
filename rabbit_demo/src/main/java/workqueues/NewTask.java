package workqueues;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

public class NewTask {

	private final static String QUEUE_NAME = "task_queue";

	public static void main(String[] argv) throws Exception {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		try (Connection connection = factory.newConnection(); Channel channel = connection.createChannel()) {
			boolean durable = true;
			channel.queueDeclare(QUEUE_NAME, durable, false, false, null);
			String message = "Hello World!";
			for (int i = 0; i < 5; i++) {
				channel.basicPublish("", QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes("UTF-8"));
				System.out.println(" [x] Sent '" + message + "'");	
			}
			
		}
	}
}
