package com.ikubinfo.primefaces.service.admin;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ikubinfo.primefaces.model.admin.Order;
import com.ikubinfo.primefaces.repository.admin.OrderRepository;

@Service
public class OrderService {
	
	private OrderRepository orderRepository;

	public OrderService(OrderRepository orderRepository) {
		super();
		this.orderRepository = orderRepository;
	}

	public List<Order> getAllOrders(Boolean delivered,Date startDate, Date endDate) {
		return orderRepository.getAllOrders(delivered,startDate,endDate);
	}

	public boolean deliver(int orderId) {
		return orderRepository.deliver(orderId);
		
	}
	
	public int getTotalPrice(Date startDate,Date endDate) {
		return orderRepository.getTotalPrice(startDate, endDate);
	}
	
	
}
