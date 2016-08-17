package KafkaConsumer

import kafka.consumer.Consumer
import kafka.consumer.ConsumerConfig
import kafka.consumer.ConsumerIterator
import kafka.consumer.KafkaStream
import kafka.javaapi.consumer.ConsumerConnector
import kafkaDemo.RecordTime
import org.codehaus.groovy.grails.plugins.springsecurity.RequestHolderAuthenticationFilter
import org.codehaus.groovy.grails.web.servlet.mvc.GrailsWebRequest
import org.codehaus.groovy.grails.web.util.WebUtils
import org.example.SecUser
import org.springframework.web.context.request.RequestContextHolder
      import org.codehaus.groovy.grails.commons.ApplicationHolder
import java.text.SimpleDateFormat


/**
 * Created by chetan on 16/8/16.
 */
class ConsumerDemo   implements Runnable {

    final static String clientId = "SimpleConsumerDemoClient";
    final static String TOPIC = "roo";
    ConsumerConnector consumerConnector;
     private volatile String str

    public ConsumerDemo(){
        Properties properties = new Properties();
        properties.put("zookeeper.connect","localhost:2181");
        properties.put("group.id","test-group");
        ConsumerConfig consumerConfig = new ConsumerConfig(properties);

        consumerConnector = Consumer.createJavaConsumerConnector(consumerConfig);
    }


    @Override
    public void run() {
        Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
        topicCountMap.put(TOPIC, new Integer(1));

        Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumerConnector.createMessageStreams(topicCountMap);

        KafkaStream<byte[], byte[]> stream = consumerMap.get(TOPIC).get(0);
        def companyService=ctx.companyService
        ConsumerIterator<byte[], byte[]> itrate = stream.iterator();
        while (itrate.hasNext())
        {
          str= new String(itrate.next().message())
            RecordTime recordTime= new RecordTime()

            println  (str.split(',')[0].split('::'))
            recordTime.currentUrl= (str.split(',')[3].split('::'))[1]
            recordTime.referrerUrl=(str.split(',')[4].split('::'))[1]
            recordTime.pageLeft=(str.split(',')[1].split('::'))[1]
            recordTime.pageVisited= (str.split(',')[0].split('::'))[1]
            recordTime.timeSpentOnPage=    (str.split(',')[2].split('::'))[1]
            recordTime.user= SecUser.findByUsername((str.split(',')[5].split('::'))[1])
           companyService.saveRecordTime(recordTime)
}


        }

    }






