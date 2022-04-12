package com.github.danilodant.gib.entities;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Entity
public class Stock {

  @Id
  @Column(nullable = false)
  private String ticker;
  private String name;
  private BigDecimal price;
  @Enumerated(EnumType.ORDINAL)
  private StockStatus status;

  public Stock() {}

  public Stock(String ticker, String name, BigDecimal price) {
    this.ticker = ticker;
    this.name = name;
    this.price = price;
    this.status = StockStatus.ACTIVE;
  }

  public String getTicker() {
    return ticker;
  }

  public void setTicker(String ticker) {
    this.ticker = ticker;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public StockStatus getStatus() {
    return status;
  }

  public void setStatus(StockStatus status) {
    this.status = status;
  }

  @Override
  public String toString() {
    return "\n>>Stock: "+
      "\n\tname=" + name + "\n\tprice=" + price + "\n\tstatus=" + status + "\n\tticker=" + ticker;
  }

}
