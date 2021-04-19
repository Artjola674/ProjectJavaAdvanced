package com.ikubinfo.primefaces.service.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.ikubinfo.primefaces.model.admin.Dish;
import com.ikubinfo.primefaces.repository.admin.DishRepository;

@Service
public class DishService {
	
	private DishRepository dishRepository;	

	public DishService(DishRepository dishRepository) {
		super();
		this.dishRepository = dishRepository;
	}


	public boolean insertDish(Dish dish, int adminId) {
		return dishRepository.insertDish(dish,adminId);
	}


	public int getAdminId(String email) {
		return dishRepository.getAdminId(email);
	}
	
	public List<String> getCategories() {
		return dishRepository.getCategories();
	}
	
	public List<String> getDishNames(String category) {
		
		return dishRepository.getDishNames(category);
	}


	public List<Dish> getAllDishes(String category, String dishName) {
		return dishRepository.getAllDishes(category, dishName);
	}

}
