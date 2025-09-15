package com.bank.project;

import java.math.BigDecimal;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        BankService bankService = new BankService();

        // --- Этап 1: Создаем клиентов и счета ---
        System.out.println("--- Создание клиентов и счетов ---");
        Client client1 = bankService.createClient("Иван Петров", 30);
        Client client2 = bankService.createClient("Мария Сидорова", 25);

        Account account1 = bankService.createAccount(client1);
        Account account2 = bankService.createAccount(client1); // Второй счет у Ивана
        Account account3 = bankService.createAccount(client2);

        System.out.println("Создан клиент: " + client1);
        System.out.println("Создан клиент: " + client2);
        System.out.println("Созданы счета: " + account1 + ", " + account2 + ", " + account3);
        System.out.println();

        // --- Этап 2: Пополняем счета ---
        System.out.println("--- Пополнение счетов ---");
        account1.deposit(new BigDecimal("10000.00"));
        account2.deposit(new BigDecimal("5000.00"));
        account3.deposit(new BigDecimal("12000.00"));

        System.out.println("Баланс счета " + account1.getId() + ": " + account1.getBalance());
        System.out.println("Баланс счета " + account2.getId() + ": " + account2.getBalance());
        System.out.println("Баланс счета " + account3.getId() + ": " + account3.getBalance());
        System.out.println();

        // --- Этап 3: Переводим деньги ---
        System.out.println("--- Перевод денег ---");
        bankService.transferMoney(account1.getId(), account3.getId(), new BigDecimal("2000.00"));
        System.out.println("Перевод 2000.00 с account1 на account3...");
        System.out.println("Новый баланс счета " + account1.getId() + ": " + account1.getBalance());
        System.out.println("Новый баланс счета " + account3.getId() + ": " + account3.getBalance());
        System.out.println();

        // --- Этап 4: Выполняем операции из задания ---
        System.out.println("--- Операции по заданию ---");

        // 1. Поиск транзакций по счёту
        List<Transaction> client1Transactions = bankService.findTransactionsByAccountId(account1.getId());
        System.out.println("Транзакции по счету " + account1.getId() + ": " + client1Transactions);

        // 2. Фильтрация клиентов по сумме средств
        BigDecimal filterAmount = new BigDecimal("14000.00");
        List<Client> richClients = bankService.findClientsWithTotalBalanceGreaterThan(filterAmount);
        System.out.println("Клиенты с общим балансом больше " + filterAmount + ": " + richClients);
    }
}