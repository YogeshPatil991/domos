package com.jwt.token.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users" , catalog = "jwt_token")
public class Users {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private int id;

    private String userName;

    private String password;

    public Users() {
    }

    public Users(int id, String userName, String password) {
        this.id = id;
        this.userName = userName;
        this.password = password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public int getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
