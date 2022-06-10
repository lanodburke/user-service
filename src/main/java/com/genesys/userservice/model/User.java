package com.genesys.userservice.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document("user")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {

    @Id
    private String id;
    @NonNull
    @Indexed(direction = IndexDirection.ASCENDING)
    private String name;
    @NonNull
    private String email;
    @NonNull
    private String password;
    private Date lastLogin;
    private boolean loggedIn;

    public User (String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
