# user-service

This is my implemenation of a user service application. 

### Pre-requisites
* Docker Desktop
* Java 11
* Maven

To run this application first you will need to build the jar.
``` bash 
mvn clean install
```

Then build the application and bring up the database with  docker-compose
``` bash 
docker-compose up --build
```

The application can be accessed from ```localhost:8080```

## Service Endpoints


| Method   | URL                                      | Description                              |
| -------- | ---------------------------------------- | ---------------------------------------- |
| `GET`    | `/api/v1/user`                             | Get all users.                    |
| `POST`   | `/api/v1/user`                             | Create a new user.                      |
| `GET`    | `/api/v1/user/{id}`                          | Retrieve user with given `{id}`.                    |
| `DELETE` | `/api/v1/user/{id}`                          | Delete user with given `{id}`.                    |
| `DELETE` | `/api/v1/user`                               | Delete all users                    |
| `PUT` | `/api/v1/user{id}`                                 | Updated user with given `{id}`.                   |
| `POST`   | `/api/v1/login`                             | Login user                      |
| `POST`   | `/api/v1/logout`                             | Logout user                    |
