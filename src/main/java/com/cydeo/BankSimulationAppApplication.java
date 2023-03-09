package com.cydeo;

import com.cydeo.enums.AccountType;
import com.cydeo.model.Account;
import com.cydeo.service.AccountService;
import com.cydeo.service.TransactionService;
import com.cydeo.service.impl.AccountServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.awt.geom.AffineTransform;
import java.math.BigDecimal;
import java.util.Date;

@SpringBootApplication
public class BankSimulationAppApplication {

	public static void main(String[] args) {

		ApplicationContext container = SpringApplication.run(BankSimulationAppApplication.class, args);

		AccountService accountService = container.getBean(AccountService.class);
		TransactionService transactionService = container.getBean(TransactionService.class);

		Account sender = accountService.createNewAccount(BigDecimal.valueOf(70), new Date(), AccountType.CHECKING, 1L);
		Account receiver = accountService.createNewAccount(BigDecimal.valueOf(50), new Date(), AccountType.SAVING, 1L);
		Account receiver2= null;

		accountService.listAllAccount().forEach(System.out::println);

		transactionService.makeTransfer(sender, receiver, new BigDecimal(35), new Date(), "Transaction 1");

		transactionService.findAllTransaction().get(0);

		accountService.listAllAccount().forEach(System.out::println);


//		System.out.println("============ BalanceNotSufficientException ============");
//		transactionService.makeTransfer(sender, receiver, new BigDecimal(100), new Date(), "Transaction 2");
//
//		transactionService.findAllTransaction().get(1);
//
//		accountService.listAllAccount().forEach(System.out::println);

		System.out.println("============ BadRequestException ============");
		transactionService.makeTransfer(sender, receiver2, new BigDecimal(100), new Date(), "Transaction 2");

		transactionService.findAllTransaction().get(1);

		accountService.listAllAccount().forEach(System.out::println);

	}



}
