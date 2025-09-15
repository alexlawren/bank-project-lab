package com.bank.project;

import java.util.ArrayList; // импортируем
import java.util.List;      // импортируем
import java.util.UUID;

public class Client {
    private final String id;
    private String name;
    private int age;
    private final List<Account> accounts; // Новое поле

    public Client(String name, int age) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.age = age;
        this.accounts = new ArrayList<>(); // Инициализируем список
    }

    // Метод для добавления нового счета клиенту
    public void addAccount(Account account) {
        this.accounts.add(account);
    }

    // Геттеры, чтобы мы могли получать доступ к полям
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Account> getAccounts() {
        return accounts;
    }


    @Override
    public String toString() {
        return "Client{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", accounts=" + accounts.size() +
                '}';
    }
}