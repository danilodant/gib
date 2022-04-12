package com.github.danilodant.gib.exceptions;

import javax.persistence.EntityNotFoundException;

public class StockNotFoundException extends EntityNotFoundException {

  public StockNotFoundException(String ticker) {
    super("Stock " + ticker + " not found.");
  }

}
