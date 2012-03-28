package com.baselogic.service;

import java.util.Random;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.baselogic.dao.OrderDAO;
import com.baselogic.domain.Order;


/**
 * {@link ExampleService} with hard-coded input data.
 */
@Component("orderService")
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDAO orderDao;
	
	//@Autowired
	private String message;


	/**
	 * Reads next record from input
	 */
	public String getMessage() {
		return message;	
	}
	
	public Order placeOrder(Order order){
		
		return orderDao.placeOrder(order);
	}	

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}    
}
