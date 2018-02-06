

import java.net.URISyntaxException;

import javax.jms.*;
//import javax.naming.InitialContext;

import org.apache.activemq.ActiveMQConnectionFactory;

public class ActivemqTopicPublisher {
    private Session session;
    private MessageProducer publisher;
    private Connection connection;

    public void initialize() throws URISyntaxException, Exception {
        /* 这部分注释是原始方式构建topic */
        ConnectionFactory connectFactory = new ActiveMQConnectionFactory("tcp://192.168.66.15:61616");
        connection = connectFactory.createConnection();
        session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createTopic("topic1");
        publisher = session.createProducer(destination);
        connection.start();
    }

    public void sendText(String Message) {
        try {
            TextMessage text = session.createTextMessage(Message);
            System.out.println("Sending message:" + text.getText()); //TODO
            publisher.send(text);

        } catch (JMSException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void submit() throws JMSException {
        session.commit();
    }

    // 关闭连接
    public void close() throws JMSException {
        System.out.println("Producer:->Closing connection");
        if (publisher != null)
            publisher.close();
        if (session != null)
            session.close();
        if (connection != null)
            connection.close();
    }
}
