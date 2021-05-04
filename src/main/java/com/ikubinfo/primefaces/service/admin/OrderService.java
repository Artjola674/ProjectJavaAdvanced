package com.ikubinfo.primefaces.service.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ikubinfo.primefaces.model.admin.Order;
import com.ikubinfo.primefaces.model.delivery.Delivery;
import com.ikubinfo.primefaces.repository.admin.OrderRepository;

@Service
public class OrderService {

	private OrderRepository orderRepository;

	public OrderService(OrderRepository orderRepository) {
		super();
		this.orderRepository = orderRepository;
	}

	@Transactional
	public List<Order> getAllOrders(Boolean delivered, Date startDate, Date endDate, Boolean sent, Boolean returned) {
		List<Order> orders = new ArrayList<>();
		orders = orderRepository.getAllOrders(delivered, startDate, endDate, sent, returned);
		for (Order order : orders) {
			orderRepository.getOrderQuantity(order);
			if (order.getSent() == true) {
				orderRepository.getOrderDelivery(order);
			}
		}
		return orders;
	}

	public boolean deliver(int orderId) {
		return orderRepository.deliver(orderId);

	}

	public double getTotalPrice(Date startDate, Date endDate) {
		return orderRepository.getTotalPrice(startDate, endDate);
	}

	public boolean returned(int orderId) {
		return orderRepository.returned(orderId);
	}

	@Transactional
	public boolean sendOrderToDelivery(Delivery delivery, int orderId) {
		if (orderRepository.sendOrderToDelivery(delivery, orderId) && orderRepository.updateSend(orderId)) {
			return true;
		}
		return false;
	}

	@Transactional
	public List<Order> getAllOrdersOfADelivery(Boolean delivered, Date startDate, Date endDate, Boolean returned,
			int deliveryId) {
		List<Order> orders = new ArrayList<>();
		orders = orderRepository.getAllOrdersOfADelivery(delivered, startDate, endDate, returned, deliveryId);
		for (Order order : orders) {
			orderRepository.getOrderQuantity(order);
			if (order.getSent() == true) {
				orderRepository.getOrderDelivery(order);
			}
		}

		return orders;
	}
}