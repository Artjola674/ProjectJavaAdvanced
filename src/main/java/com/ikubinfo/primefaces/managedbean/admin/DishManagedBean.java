package com.ikubinfo.primefaces.managedbean.admin;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.file.UploadedFile;

import com.ikubinfo.primefaces.model.admin.Dish;
import com.ikubinfo.primefaces.service.admin.DishService;
import com.ikubinfo.primefaces.util.Messages;

@ManagedBean
@ViewScoped
public class DishManagedBean implements Serializable {

	private static final long serialVersionUID = -7853456626370075836L;

	@ManagedProperty(value = "#{dishService}")
	private DishService dishService;

	@ManagedProperty(value = "#{messages}")
	private Messages messages;

	private List<Dish> dishes;
	private int show;
	private Dish dish;
	private String category;
	private String dishName;
	private List<String> categories;
	private List<String> names;
	private UploadedFile file;
	private String picture;
	private Boolean availability;
	private boolean showEnableColumn;
	private boolean showDisableColumn;
	private boolean showDeleteColumn;
	private boolean showEditColumn;

	@PostConstruct
	public void init() {
		String showString =FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("show");
		show = Integer.parseInt(showString);
		if(show == 1) {
			availability = null;
			showEnableColumn = true;
			showDisableColumn = true;
			showDeleteColumn = true;
			showEditColumn = true;
			dishes = dishService.getAllDishes(null, null, availability);
			categories = dishService.getCategories(availability);
			names = dishService.getDishNames(null, availability);
		}else if(show == 2) {
			availability = true;
			showEnableColumn = false;
			showDisableColumn = true;
			showDeleteColumn = false;
			showEditColumn = false;
			dishes = dishService.getAllDishes(null, null, availability);
			categories = dishService.getCategories(availability);
			names = dishService.getDishNames(null, availability);
		}else if(show == 3) {
			availability = false;
			showEnableColumn = true;
			showDisableColumn = false;
			showDeleteColumn = false;
			showEditColumn = false;
			dishes = dishService.getAllDishes(null, null, availability);
			categories = dishService.getCategories(availability);
			names = dishService.getDishNames(null, availability);
		}
		
	}

	public void getDishNames() {
		names = dishService.getDishNames(category, availability);
	}

	public void search() {
		dishes = dishService.getAllDishes(category, dishName, availability);
	}

	public void save() {
		
		if (file != null) {
			if(file.getFileName() != null) {
				try {
					String fileName = file.getFileName();
					picture = fileName;
					java.io.InputStream inputStream = file.getInputStream();
					dishService.savePicture(inputStream, fileName);
						
				} catch (IOException e) {
					messages.showInfoMessage("Something went wrong");
				}

			}else {
				picture = dish.getPicture();
			}
			
		}
		if (dishService.save(dish,picture)) {
			dishes = dishService.getAllDishes(category, dishName, availability);
			messages.showInfoMessage("Dish updated successfully");

		}
		dish = new Dish();

	}

	public void enable() {
		dishService.availability(dish.getDishId(), true);
		dishes = dishService.getAllDishes(category, dishName, availability);
		messages.showInfoMessage("Enabled");
	}

	public void disable() {
		dishService.availability(dish.getDishId(), false);
		dishes = dishService.getAllDishes(category, dishName, availability);
		messages.showInfoMessage("Disabled");
	}

	public void delete() {
		if (dishService.delete(dish.getDishId())) {
			dishes = dishService.getAllDishes(category, dishName, availability);
			messages.showInfoMessage("Deleted");

		} else {
			messages.showInfoMessage("Something went wrong");
		}

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

	public Dish getDish() {
		return dish;
	}

	public void setDish(Dish dish) {
		this.dish = dish;
	}

	public Boolean getAvailability() {
		return availability;
	}

	public void setAvailability(Boolean availability) {
		this.availability = availability;
	}

	public boolean isShowEnableColumn() {
		return showEnableColumn;
	}

	public void setShowEnableColumn(boolean showEnableColumn) {
		this.showEnableColumn = showEnableColumn;
	}

	public boolean isShowDisableColumn() {
		return showDisableColumn;
	}

	public void setShowDisableColumn(boolean showDisableColumn) {
		this.showDisableColumn = showDisableColumn;
	}

	public boolean isShowDeleteColumn() {
		return showDeleteColumn;
	}

	public void setShowDeleteColumn(boolean showDeleteColumn) {
		this.showDeleteColumn = showDeleteColumn;
	}

	public boolean isShowEditColumn() {
		return showEditColumn;
	}

	public void setShowEditColumn(boolean showEditColumn) {
		this.showEditColumn = showEditColumn;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public int getShow() {
		return show;
	}

	public void setShow(int show) {
		this.show = show;
	}
	
	

}
