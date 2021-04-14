package com.example.demo.controller;

import com.example.demo.dto.Period;
import com.example.demo.dto.RedisStock;
import com.example.demo.entity.Stock;
import com.example.demo.service.StockService;
import com.example.demo.util.Result;
import com.example.demo.util.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "stock management api")
@RequestMapping("/stock")
public class StockController {

    @Autowired
    private StockService stockService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ZSetOperations<String, Object> zSetOperations;

    @PostMapping("/add")
    @ApiOperation("add stock api")
    public Result addStocks(@RequestBody List<Stock> stocks){
        for (Stock stock : stocks) {
            zSetOperations.add(stock.getSymbol(), new RedisStock(stock),stock.getDate().getTime());
        }
        for (Stock stock : stocks) {
            if (!stockService.addStock(stock)) {
                return ResultUtil.error(406,"invalid parameter");
            }
        }
        return ResultUtil.success("added successfully");
    }

    @GetMapping("/get_prices/{symbol}")
    @ApiOperation("get stock price api")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "symbol", value = "stock symbol", required = true)
    })
    public Result getHistoricalPrice(@PathVariable("symbol") String symbol, @RequestBody Period period){
        if (redisTemplate.hasKey(symbol)){

            return ResultUtil.success(zSetOperations.rangeByScore(symbol, period.getStartDate().getTime(),
                    period.getEndDate().getTime()));
        }
        List<Stock> stockPrices = stockService.getHistoricalPrice(symbol, period.getStartDate(), period.getEndDate());
        return ResultUtil.success(stockPrices);
    }

    @DeleteMapping("/delete/{symbol}")
    @ApiOperation("delete stock api")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "symbol", value = "stock symbol", required = true)
    })
    public Result deleteStock(@PathVariable String symbol){
        if (redisTemplate.hasKey(symbol)) {
            zSetOperations.zCard(symbol);
        }
        return stockService.deleteStock(symbol)? ResultUtil.success("deleted"):ResultUtil.error(406,"failed to delete");
    }

}
