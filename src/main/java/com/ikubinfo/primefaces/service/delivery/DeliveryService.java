package com.ikubinfo.primefaces.service.delivery;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ikubinfo.primefaces.model.delivery.Delivery;
import com.ikubinfo.primefaces.repository.delivery.DeliveryRepository;

@Service
public class DeliveryService {

	private DeliveryRepository deliveryRepository;

	public DeliveryService(DeliveryRepository deliveryRepository) {
		super();
		this.deliveryRepository = deliveryRepository;
	}

	public Delivery getDelivery(String email) {
		return deliveryRepository.getDelivery(email);
	}

	public boolean save(Delivery delivery) {
		return deliveryRepository.save(delivery);
	}

	public void changeStatus(boolean status, String email) {
		deliveryRepository.changeStatus(status, email);
	}

	public boolean insert(Delivery delivery) {
		return deliveryRepository.insert(delivery);
	}

	public List<Delivery> getAllDeliveries(Boolean status, Boolean working) {
		return deliveryRepository.getAllDeliveries(status, working);
	}

	public boolean delete(int deliveryId) {
		return deliveryRepository.delete(deliveryId);
	}

	public void changeWorkingStatus(boolean working, String email) {
		deliveryRepository.changeWorkingStatus(working, email);
	}

	public List<String> getDeliveriesEmail() {
		return deliveryRepository.getDeliveriesEmail();
	}

}
