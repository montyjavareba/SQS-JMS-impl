package sqs_event;

import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;

import com.amazon.sqs.javamessaging.SQSConnection;


/**
amangupta
*/
public class Consumer {
	public void setConsumere(String queueName, MessageListener listener) throws JMSException {
		SQSConnection connection=Connection.getSqsConnection();
		
		Session session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
		MessageConsumer consumer = session.createConsumer(session.createQueue(queueName));
        
		consumer.setMessageListener( listener );
        
        System.out.println("Start reciving msg");
        
        connection.start();
        
	}
}
