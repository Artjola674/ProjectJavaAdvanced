package com.ikubinfo.primefaces.service.admin.request;

public class DishListRequest {
	private String category;
	private String dishName;
	private Boolean availability;
	private int first;
	private int pageSize;
	
	public DishListRequest(Boolean availability,String category, String dishName,  int first, int pageSize) {
		super();
		this.category = category;
		this.dishName = dishName;
		this.availability = availability;
		this.first = first;
		this.pageSize = pageSize;
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

	public Boolean getAvailability() {
		return availability;
	}

	public void setAvailability(Boolean availability) {
		this.availability = availability;
	}

	public int getFirst() {
		return first;
	}

	public void setFirst(int first) {
		this.first = first;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	
}
