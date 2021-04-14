package com.example.demo.service;


import com.example.demo.entity.Stock;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface StockService {
    boolean addStock(Stock stock);

    List<Stock> getHistoricalPrice(String symbol, Date startDate, Date endDate);

    boolean deleteStock(String symbol);
}
