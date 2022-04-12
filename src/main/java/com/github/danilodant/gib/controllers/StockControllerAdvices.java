package com.github.danilodant.gib.controllers;

import com.github.danilodant.gib.exceptions.StockAlreadyExistsException;
import com.github.danilodant.gib.exceptions.StockNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class StockControllerAdvices {

  @ResponseBody
  @ExceptionHandler(StockAlreadyExistsException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  String stockAlreadyExistis(StockAlreadyExistsException exception) {
    return exception.getMessage();
  }

  @ResponseBody
  @ExceptionHandler(StockNotFoundException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  String stockNotFound(StockNotFoundException exception) {
    return exception.getMessage();
  }
  
}
