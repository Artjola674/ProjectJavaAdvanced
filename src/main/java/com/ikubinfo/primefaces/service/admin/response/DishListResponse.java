package com.ikubinfo.primefaces.service.admin.response;

import java.util.List;

import com.ikubinfo.primefaces.model.admin.Dish;

public class DishListResponse {
	private int totalRows;
	private List<Dish> dishes;
	
	public DishListResponse(int totalRows, List<Dish> dishes) {
		super();
		this.totalRows = totalRows;
		this.dishes = dishes;
	}

	public int getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}

	public List<Dish> getDishes() {
		return dishes;
	}

	public void setDishes(List<Dish> dishes) {
		this.dishes = dishes;
	}
	
	
}
