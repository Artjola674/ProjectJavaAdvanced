package com.ikubinfo.primefaces.managedbean.admin;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.file.UploadedFile;

import com.ikubinfo.primefaces.model.admin.Dish;
import com.ikubinfo.primefaces.service.admin.DishService;
import com.ikubinfo.primefaces.util.Messages;

@ManagedBean
@ViewScoped
public class AddDishManagedBean implements Serializable {

	private static final long serialVersionUID = 8945920383830009571L;

	@ManagedProperty(value = "#{dishService}")
	private DishService dishService;

	@ManagedProperty(value = "#{messages}")
	private Messages messages;

	private Dish dish;
	private int adminId;
	private UploadedFile file;

	@PostConstruct
	public void init() {
		dish = new Dish();
		adminId = dishService.getAdminId("artjola.kotorri@gmail.com");
	}

	public String insertDish() {
		
		if (file != null) {
			if(file.getFileName() != null) {
				try {
					String fileName = file.getFileName();
					this.dish.setPicture(fileName);
					java.io.InputStream inputStream = file.getInputStream();
					dishService.savePicture(inputStream, fileName);
					
					if (dishService.insertDish(dish, adminId,fileName)) {
						messages.showInfoMessage("Dish was added successfully");
						dish = new Dish();
						return "dish.xhtml";
					} else {
						messages.showInfoMessage("Something went wrong");
						return "addDish.xhtml";
					}
				} catch (IOException e) {
					messages.showInfoMessage("Something went wrong");
					
				}
			}else {
				messages.showInfoMessage("File should not be empty");
			}
		}	
		
		return "addDish.xhtml";
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

	public Dish getDish() {
		return dish;
	}

	public void setDish(Dish dish) {
		this.dish = dish;
	}

	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

}
