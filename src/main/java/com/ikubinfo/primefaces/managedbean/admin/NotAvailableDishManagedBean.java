package com.ikubinfo.primefaces.managedbean.admin;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.ikubinfo.primefaces.model.admin.Dish;
import com.ikubinfo.primefaces.service.admin.DishService;
import com.ikubinfo.primefaces.util.Messages;

@ManagedBean
@ViewScoped
public class NotAvailableDishManagedBean {
	@ManagedProperty(value = "#{dishService}")
	private DishService dishService;
	
	@ManagedProperty(value = "#{messages}")
	private Messages messages;
	
	private List<Dish> dishes;
	private int dishId;
	private String category;
	private String dishName;
	private List<String> categories;
	private List<String> names;
	
	@PostConstruct
	public void init() {
		dishes = dishService.getAllDishes(null,null,false);
		categories = dishService.getCategories(false);
		names = dishService.getDishNames(null,false);
	}
	
	public void getDishNames() {
		names = dishService.getDishNames(category,false);
	}
	
	public void search() {
		dishes = dishService.getAllDishes(category,dishName,false);
	}
	
	public void enable() {
		dishService.availability(dishId, true);
		dishes = dishService.getAllDishes(category,dishName,false);
	}

	public DishService getDishService() {
		return dishService;
	}

	public void setDishService(DishService dishService) {
		this.dishService = dishService;
	}

	public Messages getMessages() {
		return messages;
	}

	public void setMessages(Messages messages) {
		this.messages = messages;
	}

	public List<Dish> getDishes() {
		return dishes;
	}

	public void setDishes(List<Dish> dishes) {
		this.dishes = dishes;
	}

	public int getDishId() {
		return dishId;
	}

	public void setDishId(int dishId) {
		this.dishId = dishId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDishName() {
		return dishName;
	}

	public void setDishName(String dishName) {
		this.dishName = dishName;
	}

	public List<String> getCategories() {
		return categories;
	}

	public void setCategories(List<String> categories) {
		this.categories = categories;
	}

	public List<String> getNames() {
		return names;
	}

	public void setNames(List<String> names) {
		this.names = names;
	}
	
	
}
