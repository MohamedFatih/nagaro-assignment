package com.nagaro.engine.repository;

import com.nagaro.engine.model.Statement;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

@Repository
public interface StatementRepository extends JpaRepository<Statement, Long>{

}