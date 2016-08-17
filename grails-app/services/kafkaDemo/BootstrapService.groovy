package kafkaDemo

import org.example.SecRole
import org.example.SecUser
import org.example.SecUserSecRole
import org.springframework.transaction.annotation.Transactional

class BootstrapService {

    @Transactional
    def bootstrap() {


 SecRole secRole=new SecRole()
        secRole.authority='role_user'

        secRole.save(flush: true)
        saveIfNotExists(
                new SecUser(
                        username: 'regadmin',
                        password: 'password',
                        firstName: 'Registry',
                        lastName: 'Admin',

                ), "role_user",
        )
        saveIfNotExists(
                new SecUser(
                        username: 'vijay',
                        password: 'password',
                        firstName: 'vijay',
                        lastName: 'prakash',

                ), "role_user"
        )


        saveIfNotExists(
                new SecUser(
                        username: 'chetan',
                        password: 'password',
                        firstName: 'chetan',
                        lastName: 'khare',

                ), 'role_user'

        )
    }

    @Transactional
    def saveIfNotExists(SecUser user, String authority) {
        def existingUser = SecUser.findByUsername(user.username)

        if (!existingUser) {
            user.validate()
            user.enabled=true
            user.save(flush: true)
            println user.errors
            existingUser = user
            SecUserSecRole.create(existingUser, SecRole.findByAuthority(authority))
        }
        println existingUser
        println authority

    }
}