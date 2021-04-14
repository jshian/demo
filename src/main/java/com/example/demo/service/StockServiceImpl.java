package com.example.demo.service;

import com.example.demo.dao.StockDao;
import com.example.demo.entity.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class StockServiceImpl implements StockService{

    @Autowired
    private StockDao stockDao;

    @Transactional
    @Override
    public boolean addStock(Stock stock) {
        try {
            stockDao.save(stock);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public List<Stock> getHistoricalPrice(String symbol, Date startDate, Date endDate) {
        List<Stock> priceList;
        try {
            priceList = stockDao.findBySymbolAndDateBetweenOrderByDate(symbol,startDate,endDate);
        } catch (Exception e) {
            return null;
        }
        return priceList;
    }

    @Transactional
    @Override
    public boolean deleteStock(String symbol) {
        try {
            stockDao.deleteBySymbol(symbol);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
