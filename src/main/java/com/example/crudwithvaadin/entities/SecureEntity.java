package com.example.crudwithvaadin.entities;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class SecureEntity<T>{

    @Id
    @GeneratedValue
    private T id;

    private String username="startUser";

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public T getId() {
        return id;
    }



}
