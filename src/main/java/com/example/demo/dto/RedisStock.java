package com.example.demo.dto;

import com.example.demo.entity.Stock;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;


@Data
public class RedisStock {

    private String symbol;
    private String date;
    private BigDecimal open;
    private BigDecimal high;
    private BigDecimal low;
    private BigDecimal close;
    private BigDecimal volume;
    private BigDecimal dividends;
    private BigDecimal splits;
    @JsonIgnore
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public RedisStock(Stock stock){
        this.symbol = stock.getSymbol();
        this.date = sdf.format(stock.getDate());
        this.open = stock.getOpen();
        this.high = stock.getHigh();
        this.low = stock.getLow();
        this.close = stock.getClose();
        this.volume = stock.getVolume();
        this.dividends = stock.getDividends();
        this.splits = stock.getSplits();
    }
}
