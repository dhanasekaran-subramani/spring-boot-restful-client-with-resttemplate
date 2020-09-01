package com.ds2technologies.spring.model;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class ExchangeValue
{
    private Long id;
    private String from;
    private String to;
    private BigDecimal conversionMultiple;
    private int port;
    public ExchangeValue()
    {
    }
    //generating constructor using fields
    public ExchangeValue(Long id, String from, String to, BigDecimal conversionMultiple) {
        super();
        this.id = id;
        this.from = from;
        this.to = to;
        this.conversionMultiple = conversionMultiple;
    }

}