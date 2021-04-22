package com.ikubinfo.primefaces.managedbean.admin;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.omg.CORBA.portable.InputStream;
import org.primefaces.component.fileupload.FileUpload;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;

import com.ikubinfo.primefaces.model.admin.Dish;
import com.ikubinfo.primefaces.service.admin.DishService;
import com.ikubinfo.primefaces.util.Messages;

@ManagedBean
@ViewScoped
public class AddDishManagedBean implements Serializable{

	private static final long serialVersionUID = 8945920383830009571L;

	@ManagedProperty(value = "#{dishService}")
	private DishService dishService;
	
	@ManagedProperty(value = "#{messages}")
	private Messages messages;
	
	private Dish dish;
	private int adminId;
	private FileUpload file;
	
	
	@PostConstruct
	public void init() {
		this.dish = new Dish();
		adminId = dishService.getAdminId("artjola.kotorri@gmail.com");
	}
	
	public String insertDish() {
		if (dishService.insertDish(dish,adminId)) {
			messages.showInfoMessage("Dish was added successfully");
			dish = new Dish();
			return "dish.xhtml";
		}else {
			messages.showInfoMessage("Something went wrong");
			return "addDish.xhtml";
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

	public FileUpload getFile() {
		return file;
	}

	public void setFile(FileUpload file) {
		this.file = file;
	}

	
	
		
}	

