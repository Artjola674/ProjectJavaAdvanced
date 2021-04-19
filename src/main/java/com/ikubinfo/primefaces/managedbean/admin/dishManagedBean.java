package com.ikubinfo.primefaces.managedbean.admin;

import java.io.Serializable;
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
public class dishManagedBean implements Serializable{
	
	private static final long serialVersionUID = -7853456626370075836L;

	@ManagedProperty(value = "#{dishService}")
	private DishService dishService;
	
	@ManagedProperty(value = "#{messages}")
	private Messages messages;
	
	private List<Dish> dishes;
	private String category;
	private String dishName;
	private List<String> categories;
	private List<String> names;
	
	@PostConstruct
	public void init() {
		dishes = dishService.getAllDishes(null,null);
		categories = dishService.getCategories();
		names = dishService.getDishNames(null);
	}
	
	public void getDishNames() {
		names = dishService.getDishNames(category);
	}
	public void search() {
		System.out.println(category+ dishName);
		dishes = dishService.getAllDishes(category, dishName);
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
