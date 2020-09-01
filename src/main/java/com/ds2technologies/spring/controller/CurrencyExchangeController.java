package com.ds2technologies.spring.controller;

import com.ds2technologies.spring.model.ExchangeValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyExchangeController
{
    @Autowired
    private Environment environment;
    @GetMapping("/currency-exchange/from/{from}/to/{to}") //where {from} and {to} are path variable
    public ResponseEntity<ExchangeValue> retrieveExchangeValue(@PathVariable String from, @PathVariable String to)  //from map to USD and to map to INR
    {
//taking the exchange value
        ExchangeValue exchangeValue= new ExchangeValue (1000L, from, to, BigDecimal.valueOf(65));
//picking port from the environment
        exchangeValue.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
        return new ResponseEntity<>(exchangeValue, HttpStatus.OK);
    }
}
