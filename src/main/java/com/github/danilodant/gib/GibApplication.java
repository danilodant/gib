package com.github.danilodant.gib;

import com.github.danilodant.gib.entities.StockStatus;
import com.github.danilodant.gib.repositories.StockRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class GibApplication implements CommandLineRunner {

  @Autowired
  private StockRepository stockRepository;

	public static void main(String[] args) {
		SpringApplication.run(GibApplication.class, args);
	}

  @Override
  public void run(String... args) throws Exception {
    System.out.println(stockRepository.findByStatus(StockStatus.INACTIVE));
  }

}