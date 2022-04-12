package com.github.danilodant.gib.services;

import com.github.danilodant.gib.entities.Stock;
import com.github.danilodant.gib.entities.StockStatus;
import com.github.danilodant.gib.exceptions.StockAlreadyExistsException;
import com.github.danilodant.gib.exceptions.StockNotFoundException;
import com.github.danilodant.gib.repositories.StockRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockService {

  @Autowired
  StockRepository stockRepository;

  public Iterable<Stock> findAll() {
    return stockRepository.findAll();
  }

  public Iterable<Stock> findInactive() {
    return stockRepository.findByStatus(StockStatus.INACTIVE);
  }

  public Iterable<Stock> findActive() {
    return stockRepository.findByStatus(StockStatus.ACTIVE);
  }

  public Stock findById(String ticker) {
    if (stockRepository.findById(ticker).isEmpty()) {
      throw new StockNotFoundException(ticker);
    }
    return stockRepository.findById(ticker).get();
  }

  public Stock save(Stock stock) {
    stockRepository.findById(stock.getTicker()).ifPresent(s -> {
      throw new StockAlreadyExistsException(stock.getTicker());
    });
    return stockRepository.save(stock);
  }
  
}
