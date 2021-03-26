package com.nagaro.engine.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.nagaro.engine.model.Account;
import com.nagaro.engine.model.Statement;
import com.nagaro.engine.repository.StatementRepository;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


public class SearchServiceTest {
    private List<Statement> statements;

    @InjectMocks
    SearchService searchService;

    private Account account1;
    private Account account2;

    private Statement s1;
    private Statement s2;
    private Statement s3;
    private Statement s4;

    @BeforeAll
    public void init() {
         account1 = new Account();
         account2 = new Account();

         account1.setId(new Long(1));
         account1.setAccountType("current");
         account1.setAccountNumber("12250016001");

         s1.setId(new Long(78));
         s1.setDatefield(new java.sql.Date(2019, 11, 14));
         s1.setAmount(14.9269187221028);
         s1.setAccountId(account1);

         s2.setId(new Long(78));
         s2.setDatefield(new java.sql.Date(2018, 02, 13));
         s2.setAmount(45.2525260642709);
         s2.setAccountId(account1);

         account2.setId(new Long(1));
         account2.setAccountType("savings");
         account2.setAccountNumber("12250016002");

         s3.setId(new Long(78));
         s3.setDatefield(new java.sql.Date(2017, 11, 20));
         s3.setAmount(203.4226187436590);
         s3.setAccountId(account2);

         s4.setId(new Long(78));
         s4.setDatefield(new java.sql.Date(2020, 05, 17));
         s4.setAmount(103.4226154736590);
         s4.setAccountId(account2);

         statements.add(s1);
         statements.add(s2);
         statements.add(s3);
         statements.add(s4);
    }

    @Test
    public void whenCallSearchWithParameters(){

    }

}