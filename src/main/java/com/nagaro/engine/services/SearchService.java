package com.nagaro.engine.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.nagaro.engine.model.Statement;
import com.nagaro.engine.repository.StatementRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchService {

    @Autowired
    StatementRepository statementRepository;

    public List<Statement> search(Date from , Date to , Double higher, Double lower){

        List<Predicate<Statement>> predicates = predicateBuilder(from , to , higher, lower);

        List<Statement> s = statementRepository.findAll().stream().filter(
            predicates.stream().reduce(x->true, Predicate::and))
            .collect(Collectors.toList());
       s.forEach(System.out::println);
       return s;
    }

    private List<Predicate<Statement>> predicateBuilder(Date from , Date to , Double higher, Double lower) {
        Predicate<Statement> pFrom = x -> x.getDatefield().after(from) ;
        Predicate<Statement> pTo = x -> x.getDatefield().before(to) ;
        Predicate<Statement> pHigher = x -> x.getAmount() >= higher ;
        Predicate<Statement> pLower = x -> x.getAmount() <= (lower) ;
        List<Predicate<Statement>> predicates = new ArrayList<Predicate<Statement>>();
        if(from != null)
        predicates.add(pFrom);
        if (to != null)
        predicates.add(pTo);
        if(higher != null)
        predicates.add(pHigher);
        if (lower != null)
        predicates.add(pLower);

        if (predicates.isEmpty())
        {
            Date threeMonths = new Date();
            threeMonths.setMonth(threeMonths.getMonth() -5);
            predicates.add(x -> x.getDatefield().after(threeMonths));
        }

        return predicates;
    }
    
}