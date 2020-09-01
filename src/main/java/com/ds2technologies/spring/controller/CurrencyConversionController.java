package com.ds2technologies.spring.controller;

import com.ds2technologies.spring.model.CurrencyConversionBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
@Slf4j
@RestController
public class CurrencyConversionController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
    public ResponseEntity<CurrencyConversionBean> convertCurrency(@PathVariable String from, @PathVariable String to,
                                                                  @PathVariable BigDecimal quantity) {

        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("from", from);
        uriVariables.put("to", to);

        ResponseEntity<CurrencyConversionBean> responseEntity = restTemplate.getForEntity(
                "http://localhost:8081/currency-exchange/from/{from}/to/{to}", CurrencyConversionBean.class,
                uriVariables);

        CurrencyConversionBean response = responseEntity.getBody();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
