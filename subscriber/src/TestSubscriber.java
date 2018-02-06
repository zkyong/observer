import java.net.URISyntaxException;

public class TestSubscriber {
    public static void main(String[] args) throws URISyntaxException, Exception {
//        ActivemqTopicSubscriber consumer1 = new ActivemqTopicSubscriber("1");
//        ActivemqTopicSubscriber consumer2 = new ActivemqTopicSubscriber("2");
//        System.out.println("consumer1开始监听");
//        consumer1.recive();
//        System.out.println("consumer2开始监听");
//        consumer2.recive();
        
        ActivemqTopicSubscriberPersistent consumer1 = new ActivemqTopicSubscriberPersistent("client1");
        System.out.println("consumer1开始监听");
        consumer1.recive();
    }
}
