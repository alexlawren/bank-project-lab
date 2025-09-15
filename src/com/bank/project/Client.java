package com.bank.project;

import java.util.UUID;

public class Client {
    private final String id;
    private String name;
    private int age;

    public Client(String name, int age) {
        this.id = UUID.randomUUID().toString(); // Генерируем уникальный ID
        this.name = name;
        this.age = age;
    }

    // Позже мы добавим сюда геттеры, сеттеры и другие методы

    @Override
    public String toString() {
        return "Client{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}