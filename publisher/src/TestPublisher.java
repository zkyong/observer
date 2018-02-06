import java.net.URISyntaxException;
import java.util.Date;


public class TestPublisher {
    public static void main(String[] args) throws URISyntaxException, Exception {
//        ActivemqTopicPublisher producer = new ActivemqTopicPublisher();
//        producer.initialize();
//
//        Thread.sleep(500);
//        for (int i = 0; i < 10; i++) {
//            producer.sendText("Hello, world!" + i);
//        }
//        producer.submit();
//        producer.close();

        ActivemqTopicPublisherPersistent producer = new ActivemqTopicPublisherPersistent();
        producer.setClientId("scfae_test");
        producer.setTopicName("T_scfae");
        producer.initialize();
        producer.sendText("<Monitmsg>"
                    + "  <Trdcode>58100501</Trdcode>"   
                    + "  <Trdname>个人会员登录</Trdname>"   
                    + "  <Starttime>2016-08-02 19:30:00.000 CST</Starttime>"   
                    + "  <Meacct>0103000001</Meacct>"   
                    + "  <Name>个人会员测试帐号</Name>" 
                    + "  <Mainsn>" + DateUtil.date2StringUnsignedYMDHMS(new Date()) + "</Mainsn>" 
                    
                + "</Monitmsg>");
        producer.submit();
        producer.close();
    }

}
