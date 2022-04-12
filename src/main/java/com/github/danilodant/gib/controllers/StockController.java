package com.github.danilodant.gib.controllers;

import com.github.danilodant.gib.entities.Stock;
import com.github.danilodant.gib.services.StockService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/stock")
public class StockController {

  @Autowired
  StockService stockService;

  @RequestMapping(path = "/all", produces = "application/json;charset=UTF-8")
  @ResponseBody
  public Iterable<Stock> list() {
    return stockService.findAll();
  }

  @RequestMapping(path = "/inactive", produces = "application/json;charset=UTF-8")
  @ResponseBody
  public Iterable<Stock> inactive() {
    return stockService.findInactive();
  }

  @RequestMapping(path = "/active", produces = "application/json;charset=UTF-8")
  @ResponseBody
  public Iterable<Stock> active() {
    return stockService.findActive();
  }

  @GetMapping(path = "/{ticker}", produces = "application/json;charset=UTF-8")
  @ResponseBody
  public Stock findById(@PathVariable String ticker) {
    return stockService.findById(ticker);
  }

  @PostMapping(consumes = "application/json;charset=UTF-8", produces = "application/json;charset=UTF-8")
  @ResponseBody
  @ResponseStatus(value = HttpStatus.CREATED)
  public Stock save(@RequestBody Stock stock) {
    return stockService.save(stock);
  }

}
