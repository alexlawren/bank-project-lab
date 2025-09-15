package com.bank.project;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BankService {
    private final List<Client> clients = new ArrayList<>();
    private final List<Account> accounts = new ArrayList<>();
    private final List<Transaction> transactions = new ArrayList<>();

    public Client createClient(String name, int age) {
        Client client = new Client(name, age);
        clients.add(client);
        return client;
    }

    public Account createAccount(Client client) {
        Account account = new Account(client.getId());
        accounts.add(account);
        client.addAccount(account);
        return account;
    }

    public Optional<Transaction> transferMoney(String fromAccountId, String toAccountId, BigDecimal amount) {
        Optional<Account> fromOpt = findAccountById(fromAccountId);
        Optional<Account> toOpt = findAccountById(toAccountId);

        if (fromOpt.isPresent() && toOpt.isPresent()) {
            Account from = fromOpt.get();
            Account to = toOpt.get();

            if (from.getBalance().compareTo(amount) >= 0) {
                from.withdraw(amount);
                to.deposit(amount);
                Transaction transaction = new Transaction(fromAccountId, toAccountId, amount);
                transactions.add(transaction);
                return Optional.of(transaction);
            }
        }
        return Optional.empty(); // Транзакция не удалась
    }

    // Вспомогательный метод для поиска счета по ID
    private Optional<Account> findAccountById(String id) {
        return accounts.stream().filter(acc -> acc.getId().equals(id)).findFirst();
    }

    // --- Методы для выполнения требований лабы ---

    // 1. Поиск транзакций по счёту
    public List<Transaction> findTransactionsByAccountId(String accountId) {
        return transactions.stream()
                .filter(t -> t.getFromAccountId().equals(accountId) || t.getToAccountId().equals(accountId))
                .collect(Collectors.toList());
    }

    // 2. Фильтрация клиентов по сумме средств на всех счетах
    public List<Client> findClientsWithTotalBalanceGreaterThan(BigDecimal amount) {
        return clients.stream()
                .filter(client -> {
                    BigDecimal totalBalance = client.getAccounts().stream()
                            .map(Account::getBalance)
                            .reduce(BigDecimal.ZERO, BigDecimal::add);
                    return totalBalance.compareTo(amount) > 0;
                })
                .collect(Collectors.toList());
    }
}