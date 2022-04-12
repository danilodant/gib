package com.github.danilodant.gib.controllers;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


import org.springframework.http.MediaType;

import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import com.github.danilodant.gib.entities.Stock;
import com.github.danilodant.gib.services.StockService;
import com.jayway.jsonpath.spi.json.JacksonJsonProvider;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
public class StockControllerTest {

  private final String BASE_URL = "/stock";
  private MockMvc mockMvc;

  @InjectMocks  StockController         stockController;
  @Autowired    StockControllerAdvices  advices;
  @Mock         StockService            stockService;

  @BeforeAll
  void setup() {
    MockitoAnnotations.openMocks(this);
    this.mockMvc = MockMvcBuilders
      .standaloneSetup(stockController)
      .setControllerAdvice(advices)
      .defaultResponseCharacterEncoding(Charset.forName("UTF-8"))
      .build();
  }

  @Test
  void shouldReturnInactiveStocks() throws Exception {
    
    List<Stock> stocks = new ArrayList<>();
    stocks.add( new Stock() );
    stocks.add( new Stock() );

    doReturn(stocks)
      .when(stockService)
      .findInactive();
    
    this.mockMvc
      .perform(get(BASE_URL+"/inactive"))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$", hasSize(2)));
  }

  @Test
  void shouldReturnActiveStocks() throws Exception {
    
    List<Stock> stocks = new ArrayList<>();
    stocks.add( new Stock() );
    stocks.add( new Stock() );
    stocks.add( new Stock() );

    doReturn(stocks)
      .when(stockService)
      .findActive();
    
    this.mockMvc
      .perform(get(BASE_URL+"/active"))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$", hasSize(3)));
  }

  @Test
  void shouldReturnAllStocks() throws Exception {
    
    List<Stock> stocks = new ArrayList<>();
    stocks.add( new Stock() );
    stocks.add( new Stock() );
    stocks.add( new Stock() );
    stocks.add( new Stock() );
    stocks.add( new Stock() );

    doReturn(stocks)
      .when(stockService)
      .findAll();
    
    this.mockMvc
      .perform(get(BASE_URL+"/all"))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$", hasSize(5)));
  }

  @Test
  void shouldReturnEspecificStock() throws Exception {

    Stock stock = new Stock("MGLU3", "Magazine Luiza", BigDecimal.valueOf(20.22));

    doReturn(stock)
      .when(stockService)
      .findById("MGLU3");
    
    this.mockMvc
      .perform(get(BASE_URL+"/MGLU3"))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.ticker", is("MGLU3")));
  }

  @Test
  void shouldReturnSavedStock() throws Exception {

    Stock stock = new Stock("MGLU3", "Magazine Luiza", BigDecimal.valueOf(20.22));

    doReturn(stock)
      .when(stockService)
      .save(stock);
    
    this.mockMvc
      .perform(
        post(BASE_URL)
          .contentType("application/json")
          .content(
            new JacksonJsonProvider().toJson(stock)
          ).contentType(MediaType.APPLICATION_JSON_UTF8))
      .andDo(print())
      .andExpect(status().isCreated())
      .andDo(print())
      .andDo(((result) -> {
        System.out.println(result.getResponse().getContentAsString());
        System.out.println(result.getResponse().getContentLength());
        System.out.println("********************************");
        String temp = result.getResponse().getContentAsString();
        System.out.println(temp);
        System.out.println("********************************");
        }));
      // .andExpect(jsonPath("$.ticker", is("MGLU3")));
  }

}

  