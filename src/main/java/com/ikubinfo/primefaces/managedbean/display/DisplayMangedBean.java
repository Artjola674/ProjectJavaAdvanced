package com.ikubinfo.primefaces.managedbean.display;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.ikubinfo.primefaces.model.display.dish;
import com.ikubinfo.primefaces.service.display.DisplayService;
import com.ikubinfo.primefaces.util.Messages;

@ManagedBean
@SessionScoped
public class DisplayMangedBean implements Serializable {
    
	private static final long serialVersionUID = -146883774165759901L;
	
	@ManagedProperty(value = "#{displayService}")
	private DisplayService displayService;

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
		dishes = displayService.getAllDishes(category);
		categories=displayService.getAllCategories();
	}
	
	public void Search() {
		
	}
	
	public void getAll() {
		dishes=displayService.getAllDishes(category);
	}

	public DisplayService getDisplayService() {
		return displayService;
	}

	public void setDisplayService(DisplayService displayService) {
		this.displayService = displayService;
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
