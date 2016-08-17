package kafkaDemo

import kafka.javaapi.producer.Producer
import kafka.producer.KeyedMessage
import kafka.producer.ProducerConfig
import org.springframework.transaction.annotation.Transactional

class CompanyService {
    def springSecurityService

    def grailsApplication
    def getAllCompany() {

        def companies
//        pagination demo
        companies = Company.createCriteria().list(max: 5, offset: 0) {

//            ne('name','infosys')
        }
//        println companies

        //And or demo
//
//        companies=Company.createCriteria().list{
//
//            or {
//                and {
//                    eq('name', 'nike')
//
//                }
//                and {
//                    eq('name', 'infosys')
//
//                }
//            }
//        }

        //order demo
        companies = Company.createCriteria().list {
            order('name', 'asc')
            firstResult(1)
            maxResults(5)
        }
//      println companies


        return companies

    }

    def sendToProducer(RecordTime recordTime) {
        Properties properties = new Properties();
        properties.put("metadata.broker.list", "localhost:9092");
        properties.put("serializer.class", "kafka.serializer.StringEncoder");
        ProducerConfig producerConfig = new ProducerConfig(properties)
        Producer<String, String> producer = new Producer<String, String>(producerConfig);

        String str = ''
        String topic = grailsApplication.config.kafka.producer.topic
        if (springSecurityService.isLoggedIn())
            str = "inTime::${recordTime.pageVisited},outTime::${recordTime.pageLeft},timeSpent::${recordTime.timeSpentOnPage.toString()},current_url::${recordTime.currentUrl},referral_url::${recordTime.referrerUrl},current_user::${recordTime.user.username}"
        KeyedMessage<String, String> message = new KeyedMessage<String, String>(topic, str);
        producer.send(message);
        producer.close();
    }

    @Transactional
    def saveRecordTime(RecordTime recordTime) {

        recordTime.save()
    }

    def projectionsDemo() {

        List companies = []
        companies = Company.createCriteria().list {
            projections {
                property('name')
                property('location')
            }
        }
        return companies


    }


}
