package com.genesys.userservice.groovy.unit

import com.genesys.userservice.model.User
import com.genesys.userservice.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@AutoConfigureDataMongo
@SpringBootTest
class UserServiceSpec extends Specification {

    @Autowired
    UserService userService

    def "Create a new user" () {
        given: "a user"
            def user = new User(name: "John", email: "john@gmail.com", password: "johnsPassword")

        when: "create user"
            userService.createUser(user)

        then: "user should be created"
            def createdUser = userService.getUser(user.getId()).get()
            createdUser.name == "John"
            createdUser.email == "john@gmail.com"
            createdUser.password == "johnsPassword"
    }

    def "Get all users" () {
        given: "a list of users"
            def userList = [new User(name: "John", email: "John@gmail.com", password: "JohnsPassword"),
                                        new User(name: "Bob", email: "Bob@gmail.com", password: "BobsPassword"),
                                        new User(name: "Bill", email: "Bill@gmail.com", password: "BillsPassword"),
                                        new User(name: "Dave", email: "Dave@gmail.com", password: "DavesPassword"),
                                        new User(name: "Ted", email: "Ted@gmail.com", password: "TedsPassword"),
                                        new User(name: "Jim", email: "Jim@gmail.com", password: "JimsPassword"),
                                        new User(name: "Larry", email: "Larry@gmail.com", password: "LarrysPassword"),
                                        new User(name: "Harry", email: "Harry@gmail.com", password: "HarrysPassword"),
                                        new User(name: "Barry", email: "Barry@gmail.com", password: "BarrysPassword"),
                                        new User(name: "Tom", email: "Tom@gmail.com", password: "TomsPassword")]

        and: "create users"
            userList.forEach({ user -> userService.createUser(user) })

        when: "retrieve list of users"
            def retrievedUsers = userService.getUsers()

        then: "users list should be returned"
            retrievedUsers.size() == 10

    }

    def "Get user by Id" () {
        given: "a user"
            def user = new User(name: "myUser", email: "myUser@gmail.com", password: "myPassword")

        and: "create user"
            def createdUser = userService.createUser(user)

        when: "retrieve user by Id"
            def retrievedUser = userService.getUser(createdUser.id)

        then: "user should be returned"
            retrievedUser.get().id == createdUser.id
            retrievedUser.get().name == createdUser.name
            retrievedUser.get().email == createdUser.email
            retrievedUser.get().password == createdUser.password
    }

    def "delete user by Id" () {
        given: "a user"
            def user = new User(name: "myUser", email: "myUser@gmail.com", password: "myPassword")

        and: "create user"
            def createdUser = userService.createUser(user)

        when: "delete user by Id"
            userService.deleteUser(createdUser.id)

        then: "user should not exist"
            def userOptional = userService.getUser(createdUser.id)
            !userOptional.isPresent()
    }

    def "Delete all users" () {
        given: "a list of users"
            def userList = [new User(name: "John", email: "John@gmail.com", password: "JohnsPassword"),
                            new User(name: "Bob", email: "Bob@gmail.com", password: "BobsPassword"),
                            new User(name: "Bill", email: "Bill@gmail.com", password: "BillsPassword"),
                            new User(name: "Dave", email: "Dave@gmail.com", password: "DavesPassword"),
                            new User(name: "Ted", email: "Ted@gmail.com", password: "TedsPassword"),
                            new User(name: "Jim", email: "Jim@gmail.com", password: "JimsPassword"),
                            new User(name: "Larry", email: "Larry@gmail.com", password: "LarrysPassword"),
                            new User(name: "Harry", email: "Harry@gmail.com", password: "HarrysPassword"),
                            new User(name: "Barry", email: "Barry@gmail.com", password: "BarrysPassword"),
                            new User(name: "Tom", email: "Tom@gmail.com", password: "TomsPassword")]

        and: "create users"
            userList.forEach({ user -> userService.createUser(user) })

        when: "retrieve list of users"
            def retrievedUsers = userService.getUsers()

        then: "users list should be returned"
            retrievedUsers.size() == 10

        when: "delete all user"
            userService.deleteAllUsers()

        then: "there should be no users in the database"
            userService.getUsers().size() == 0

    }

}
