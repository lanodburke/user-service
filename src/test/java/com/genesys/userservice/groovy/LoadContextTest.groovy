package com.genesys.userservice.groovy

import com.genesys.userservice.controller.LoginController
import com.genesys.userservice.controller.UserController
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class LoadContextTest extends Specification {

    @Autowired (required = false)
    private UserController userController

    @Autowired (required = false)
    private LoginController loginController

    def "when context is loaded then all expected beans are created"() {
        expect: "the userController and loginController are created"
        userController
        loginController
    }


}
