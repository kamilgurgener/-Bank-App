package com.cydeo.service.impl;

import com.cydeo.enums.AccountType;
import com.cydeo.exception.AccountOwnershipException;
import com.cydeo.exception.BadRequestException;
import com.cydeo.exception.BalanceNotSufficientException;
import com.cydeo.exception.RecordNotFoundException;
import com.cydeo.model.Account;
import com.cydeo.model.Transaction;
import com.cydeo.repository.AccountRepository;
import com.cydeo.service.TransactionService;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class TransactionServiceImpl implements TransactionService {

    private final AccountRepository accountRepository;

    public TransactionServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Transaction makeTransfer(Account sender, Account receiver, BigDecimal amount, Date creationDate, String message) throws RecordNotFoundException {

        /*
            -if sender or receiver is null ?
            -if sender and receiver is the same account ?
            -if sender has enough balance ?
            -if both accounts are checking, if not, one of them saving, it needs to be same userId
         */
       validateAccount(sender, receiver);
       checkAccountOwnership(sender, receiver);



       return  null;
    }

    private void executeBalanceAndUpdateIfRequired(BigDecimal amount, Account sender, Account receiver) {
        if(checkSenderBalance(sender,amount)){
            //make balance transfer between sender and receiver
            sender.setBalance(sender.getBalance().subtract(amount));
            receiver.setBalance(receiver.getBalance().add(amount));
        }else{
            //throw BalanceNotSufficientException
            throw new BalanceNotSufficientException("Balance is nor enough for this transfer");
        }

    }

    private boolean checkSenderBalance(Account sender, BigDecimal amount) {

        return sender.getBalance().subtract(amount).compareTo(BigDecimal.ZERO) >= 0 ;


    }

    private void checkAccountOwnership(Account sender, Account receiver)  {

        if((sender.getAccountType().equals(AccountType.SAVING) || receiver.getAccountType().equals(AccountType.SAVING))
                && !sender.getUserId().equals(receiver.getUserId())){
            throw new AccountOwnershipException("Since you are using a savings account, the sender and receiver must be the same.");
        }
    }

    private void validateAccount(Account sender, Account receiver) throws RecordNotFoundException {

        /*
            -if any of the account is null
            -if account ids are the same(same account)
            -if the accounts exist in the database(repository)
         */

        if(sender == null || receiver == null){
            throw new BadRequestException("Sender or Receiver can not be null");
        }

        if(sender.getId().equals(receiver.getId())){
            throw new BadRequestException("Sender account needs to be different than receiver");
        }

        findAccountById(sender.getId());
        findAccountById(receiver.getId());

    }

    private void findAccountById(UUID id) throws RecordNotFoundException {
        accountRepository.findById(id);
    }

    @Override
    public List<Transaction> findAllTransaction() {
        return null;
    }
}
