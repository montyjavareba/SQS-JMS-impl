package sqs_event;
/**
amangupta
*/

import javax.jms.JMSException;

import com.amazon.sqs.javamessaging.ProviderConfiguration;
import com.amazon.sqs.javamessaging.SQSConnection;
import com.amazon.sqs.javamessaging.SQSConnectionFactory;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;

public class Connection {
	private Connection() {};
	
	private static SQSConnection sqsConnection=null;
	@SuppressWarnings("unused")
	public static SQSConnection getSqsConnection() throws JMSException {
		  if(sqsConnection==null) {
			  SQSConnectionFactory connectionFactory = new SQSConnectionFactory(new ProviderConfiguration(),
						AmazonSQSClientBuilder.standard()
						.withRegion(Regions.US_EAST_2)
						);
				// Create the connection.
				sqsConnection = connectionFactory.createConnection("AKIAJDAX3RN5B2FEVJ3Q", "rpn7rEjzpKqJHBOZ5D5AQ5dFV5Tx0syZCvzSqZEw");
		  }
		  return sqsConnection;
	  }
}
