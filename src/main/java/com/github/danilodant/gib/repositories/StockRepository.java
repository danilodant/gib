package com.github.danilodant.gib.repositories;

import java.util.List;

import com.github.danilodant.gib.entities.Stock;
import com.github.danilodant.gib.entities.StockStatus;

import org.springframework.data.repository.CrudRepository;

public interface StockRepository extends CrudRepository<Stock, String> {

  List<Stock> findByStatus(StockStatus status);
  
}
