package kafkademo

import org.example.SecUser

class KafkaTagLib {


    static namespace = "kf"
    def springSecurityService

        def currentStatus = { attr ->
            if (springSecurityService.isLoggedIn())
                out << "The current user is " + (springSecurityService.currentUser as SecUser).username
            else {
                out << "You are not logged in"
            }
        }
            def currentUser = { attr ->
                if (springSecurityService.isLoggedIn())
                    out << (springSecurityService.currentUser as SecUser).username


            }

    def isLoggedIn = { attr,body ->
        if (springSecurityService.isLoggedIn())
            out << body()


    }


    def isNotLoggedIn = { attr,body ->
        if (!springSecurityService.isLoggedIn())
            out << body()


    }


}
