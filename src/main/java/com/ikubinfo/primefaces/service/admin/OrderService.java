package com.ikubinfo.primefaces.service.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ikubinfo.primefaces.model.admin.Order;
import com.ikubinfo.primefaces.repository.admin.OrderRepository;

@Service
public class OrderService {
	
	private OrderRepository orderRepository;

	public OrderService(OrderRepository orderRepository) {
		super();
		this.orderRepository = orderRepository;
	}
	
	@Transactional
	public List<Order> getAllOrders(Boolean delivered,Date startDate, Date endDate) {
		List<Order> orders = new ArrayList<>();
		orders = orderRepository.getAllOrders(delivered,startDate,endDate);
		for (Order order : orders) {
			orderRepository.getOrderQuantity(order);
		}

		return orders;

	}

	public boolean deliver(int orderId) {
		return orderRepository.deliver(orderId);
		
	}
	
	public double getTotalPrice(Date startDate,Date endDate) {
		return orderRepository.getTotalPrice(startDate, endDate);
	}
	
	
}