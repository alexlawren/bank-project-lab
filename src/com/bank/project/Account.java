package com.bank.project;

import java.util.UUID;
import java.math.BigDecimal; // Используем BigDecimal для денег, это лучшая практика

public class Account {
    private final String id;
    private final String clientId; // ID клиента, которому принадлежит счет
    private BigDecimal balance;

    public Account(String clientId) {
        this.id = UUID.randomUUID().toString();
        this.clientId = clientId;
        this.balance = BigDecimal.ZERO; // Начальный баланс - ноль
    }

    public String getId() {
        return id;
    }

    public String getClientId() {
        return clientId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    // Метод для внесения денег на счет
    public void deposit(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) > 0) {
            this.balance = this.balance.add(amount);
        } else {
            System.out.println("Сумма для пополнения должна быть положительной.");
        }
    }

    // Метод для снятия денег со счета
    public void withdraw(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) > 0 && this.balance.compareTo(amount) >= 0) {
            this.balance = this.balance.subtract(amount);
        } else {
            System.out.println("Некорректная сумма для снятия или недостаточно средств.");
        }
    }

    @Override
    public String toString() {
        return "Account{" +
                "id='" + id + '\'' +
                ", clientId='" + clientId + '\'' +
                ", balance=" + balance +
                '}';
    }
}