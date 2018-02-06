import java.net.URISyntaxException;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class ActivemqTopicPublisherPersistent {
    
    private Session session;
    private MessageProducer publisher;
    private Connection connection;
    private String clientId = "DEFAULT_CLIENT_ID";
    private String topicName = "DEFAULT_TOPIC_NAME";
    
    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public void initialize() throws URISyntaxException, Exception {
        /* 这部分注释是原始方式构建topic */
        ConnectionFactory connectFactory = new ActiveMQConnectionFactory("tcp://192.168.77.13:61616");
        connection = connectFactory.createConnection();
        connection.setClientID(clientId);
        session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
        // 创建主题
        Destination dest = session.createTopic(topicName);
        publisher = session.createProducer(dest);
        publisher.setDeliveryMode(DeliveryMode.PERSISTENT);
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