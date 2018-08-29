package team.redrock.shorturl.entity;

import lombok.Data;

@Data
public class User {

    private Long   userId;
    private String username;
    private String password;
    private int    isPrime;
}
