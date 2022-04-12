package com.github.danilodant.gib.exceptions;

import javax.persistence.EntityExistsException;

public class StockAlreadyExistsException extends EntityExistsException {

  public StockAlreadyExistsException(String ticker) {
    super("Stock " + ticker + " already exists.");
  }

}
