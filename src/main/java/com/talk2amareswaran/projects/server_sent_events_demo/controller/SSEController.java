package com.talk2amareswaran.projects.server_sent_events_demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.talk2amareswaran.projects.server_sent_events_demo.model.StockPrice;
import com.talk2amareswaran.projects.server_sent_events_demo.service.StockPriceService;
import com.talk2amareswaran.projects.server_sent_events_demo.utils.Utils;

import reactor.core.publisher.Flux;

@RestController
public class SSEController {

	private List<StockPrice> stockPriceList = new ArrayList<>();
	@Autowired
	Utils utils;
	@Autowired
	StockPriceService stockPriceService;
	
	
	@PostConstruct
	public void initializeStockObjects() {
		
		StockPrice stock1 = new StockPrice("HDFC BANK", 
				utils.getRandomDoubleBetweenRange(1000, 5000), utils.getRandomDoubleBetweenRange(5, 15), utils.getRandomDoubleBetweenRange(1000, 5000), utils.getStatus());
		
		StockPrice stock2 = new StockPrice("RELIANCE", 
				utils.getRandomDoubleBetweenRange(1000, 5000), utils.getRandomDoubleBetweenRange(5, 15), utils.getRandomDoubleBetweenRange(1000, 5000), utils.getStatus());
		
		StockPrice stock3 = new StockPrice("KOTAK", 
				utils.getRandomDoubleBetweenRange(1000, 5000), utils.getRandomDoubleBetweenRange(5, 15), utils.getRandomDoubleBetweenRange(1000, 5000), utils.getStatus());
		
		stockPriceList.add(stock1);
		stockPriceList.add(stock2);
		stockPriceList.add(stock3);
	}
	
	@RequestMapping(value="/stockprice", method=RequestMethod.GET, produces=MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<List<StockPrice>> getStockPrice() {
		return stockPriceService.getStockPriceData(stockPriceList);
	}
	
}
