package com.example.demo.dao;


import com.example.demo.entity.Stock;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface StockDao extends CrudRepository<Stock, Long> {


    List<Stock> findBySymbolAndDateBetweenOrderByDate(String symbol, Date startDate, Date endDate);

    void deleteBySymbol(String symbol);
}
