
import javax.jms.*;
import javax.naming.NamingException;

import org.apache.activemq.ActiveMQConnectionFactory;

public class ActivemqTopicSubscriberPersistent implements MessageListener {

    private String clientId;

    private Connection connection;

    private Session session;

    private MessageConsumer consumer;

    ActivemqTopicSubscriberPersistent(String clientId) {
        this.clientId = clientId;
    }

    public void initialize() throws JMSException, NamingException {
        /* 采用原始方式构建topic */
        ConnectionFactory connectFactory = new ActiveMQConnectionFactory("tcp://192.168.66.15:61616");
        Connection connection = connectFactory.createConnection();
        connection.setClientID(clientId);
        connection.start();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic("T_scfae");
        consumer = session.createDurableSubscriber(topic, clientId);

    }

    public void recive() throws NamingException {
        try {
            initialize();
            System.out.println("Consumer(" + clientId + "):->Begin listening...");
            // 开始监听
            consumer.setMessageListener(this);
        } catch (JMSException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void onMessage(Message arg0) {
        // TODO Auto-generated method stub
        try {
            if (arg0 instanceof TextMessage) {
                TextMessage txtMsg = (TextMessage) arg0;
                System.out.println("consumer(" + clientId + ") recive:\n" + txtMsg.getText());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void submit() throws JMSException {
        session.commit();
    }

    // 关闭连接
    public void close() throws JMSException {
        System.out.println("Consumer:->Closing connection");
        if (consumer != null)
            consumer.close();
        if (session != null)
            session.close();
        if (connection != null)
            connection.close();
    }

}