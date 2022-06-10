package com.genesys.userservice.groovy.unit

import com.genesys.userservice.model.User
import com.genesys.userservice.service.LoginService
import com.genesys.userservice.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@AutoConfigureDataMongo
@SpringBootTest
class LoginServiceSpec extends Specification {

    @Autowired
    LoginService loginService

    @Autowired
    UserService userService

    def "login user" () {
        given: "a user"
            def user = new User(name: "myUser", email: "myUser@gmail.com", password: "myPassword")

        and: "create user"
            def createdUser = userService.createUser(user)

        when: "login user"
            def loggedInUser = loginService.loginUser(createdUser)

        then: "user should be logged in"
            loggedInUser.id == createdUser.id
            loggedInUser.isLoggedIn()
    }

    def "logout user" () {
        given: "a user"
            def user = new User(name: "myUser", email: "myUser@gmail.com", password: "myPassword")

        and: "create user"
            def createdUser = userService.createUser(user)

        when: "user is logged in"
            def loggedInUser = loginService.loginUser(createdUser)

        then: "user should be logged in"
            loggedInUser.id == createdUser.id
            loggedInUser.isLoggedIn()

        and: "logout user"
            def loggedOutUser = loginService.logoutUser(loggedInUser)

        then: "user should be logged out"
            loggedOutUser.id == createdUser.id
            !loggedOutUser.isLoggedIn()
    }

}
