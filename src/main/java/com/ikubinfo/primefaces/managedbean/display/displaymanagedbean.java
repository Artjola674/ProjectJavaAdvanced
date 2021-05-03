package com.ikubinfo.primefaces.managedbean.display;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.ikubinfo.primefaces.model.display.dish;
import com.ikubinfo.primefaces.service.display.dishService;

import com.ikubinfo.primefaces.util.Messages;

@ManagedBean
@SessionScoped
public class displaymanagedbean implements Serializable {
    
	private static final long serialVersionUID = -146883774165759901L;
	
	@ManagedProperty(value = "#{displayService}")
	private dishService dishService;

	@ManagedProperty(value = "#{messages}")
	private Messages messages;
	
	private List<dish> dishes;
	private List<String> categories;
	private int dish_id;
	private String dish_name;
	private int price;
	//private String picture;
	private String category;
	
	@PostConstruct
	public void init() {
		dishes = dishService.getAllDishes(category);
		categories=dishService.getAllCategories();
	}
	
	public void Search() {
		
	}
	
	public void getAll() {
		dishes=dishService.getAllDishes(category);
	}

	public dishService getDishService() {
		return dishService;
	}

	public void setDishService(dishService dishService) {
		this.dishService = dishService;
	}

	public Messages getMessages() {
		return messages;
	}

	public void setMessages(Messages messages) {
		this.messages = messages;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public List<dish> getDishes() {
		return dishes;
	}

	public void setDishes(List<dish> dishes) {
		this.dishes = dishes;
	}

	public int getDish_id() {
		return dish_id;
	}

	public void setDish_id(int dish_id) {
		this.dish_id = dish_id;
	}

	public String getDish_name() {
		return dish_name;
	}

	public void setDish_name(String dish_name) {
		this.dish_name = dish_name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		category = category;
	}

	public List<String> getCategories() {
		return categories;
	}

	public void setCategories(List<String> categories) {
		this.categories = categories;
	}
	
}
