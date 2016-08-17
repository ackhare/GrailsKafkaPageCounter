package kafkaDemo

import org.example.SecUser

class RecordTime implements Serializable {

    String pageVisited
    String pageLeft
    String timeSpentOnPage
    String currentUrl
    String referrerUrl
    SecUser user
    static constraints = {

    }
}
