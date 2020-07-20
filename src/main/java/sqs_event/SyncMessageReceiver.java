package sqs_event;

import java.util.concurrent.TimeUnit;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

import com.amazon.sqs.javamessaging.ProviderConfiguration;
import com.amazon.sqs.javamessaging.SQSConnection;
import com.amazon.sqs.javamessaging.SQSConnectionFactory;
import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.auth.PropertiesFileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;

/**
 * amangupta
 */

public class SyncMessageReceiver {

	public static void main(String args[]) throws JMSException, InterruptedException {
		// Create a new connection factory with all defaults (credentials and region)
		// set automatically
		SQSConnectionFactory connectionFactory = new SQSConnectionFactory(new ProviderConfiguration(),
				AmazonSQSClientBuilder.standard()
				.withRegion(Regions.US_EAST_2)
				);
		// Create the connection.
		SQSConnection connection = connectionFactory.createConnection("AKIAJDAX3RN5B2FEVJ3Q", "rpn7rEjzpKqJHBOZ5D5AQ5dFV5Tx0syZCvzSqZEw");
		
		System.out.println("Connection success");
		
		Session session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
		MessageConsumer consumer = session.createConsumer(session.createQueue("testQueue.fifo"));

		MessageListener callback = new ReceiverCallback();
        consumer.setMessageListener( callback );
        
        connection.start();
        
     
        // Close the connection. This closes the session automatically
      //  connection.close();
        //System.out.println( "Connection closed" );
        

	}
}
 class ReceiverCallback implements MessageListener {
	// Used to listen for message silence
	private volatile long timeOfLastMessage = System.nanoTime();

	public void onMessage(Message message) {
		try {
			message.acknowledge();
			System.out.println("Acknowledged message " + message.getJMSMessageID());
			System.out.println( ((TextMessage)message).getText());
			timeOfLastMessage = System.nanoTime();
		} catch (JMSException e) {
			System.err.println("Error processing message: " + e.getMessage());
			e.printStackTrace();
		}
	}

}