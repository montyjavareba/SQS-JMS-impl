package sqs_event;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

/**
amangupta
*/
public class SQSListiner {
	public static void main(String[] args) throws JMSException {
		
		new Consumer().setConsumere("testQueue.fifo",(Message message)->{
			try {
				
				System.out.println("Acknowledged message queue1 " + message.getJMSMessageID());
				System.out.println( ((TextMessage)message).getText());
				message.acknowledge();
				//timeOfLastMessage = System.nanoTime();
			} catch (JMSException e) {
				System.err.println("Error processing message: " + e.getMessage());
				e.printStackTrace();
			}
		
		});
		
		//new Consumer().setConsumere("testQueue2.fifo", new SqsListener2());
	}
}
