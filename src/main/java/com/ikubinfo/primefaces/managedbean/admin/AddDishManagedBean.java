package com.ikubinfo.primefaces.managedbean.admin;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.file.UploadedFile;

import com.ikubinfo.primefaces.model.admin.Dish;
import com.ikubinfo.primefaces.service.admin.AdminService;
import com.ikubinfo.primefaces.service.admin.DishService;
import com.ikubinfo.primefaces.util.Messages;

@ManagedBean
@ViewScoped
public class AddDishManagedBean implements Serializable {

	private static final long serialVersionUID = 8945920383830009571L;

	@ManagedProperty(value = "#{dishService}")
	private DishService dishService;

	@ManagedProperty(value = "#{adminService}")
	private AdminService adminService;

	@ManagedProperty(value = "#{messages}")
	private Messages messages;

	@ManagedProperty(value = "#{welcomeManagedBean}")
	private WelcomeManagedBean welcomeManagedBean;

	private Dish dish;
	private int adminId;
	private UploadedFile file;
	private String imageName;

	@PostConstruct
	public void init() {
		dish = new Dish();
		adminId = adminService.getAdminId(welcomeManagedBean.getEmail());
	}

	public List<String> getSuggestions() {

		return dishService.getCategories(null);
	}

	public String insertDish() {

		if (file != null) {
			if (file.getFileName() != null) {
				try {
					String fileName = file.getFileName();
					List<String> images = dishService.getImages();
					for (String image : images) {
						if (fileName.equals(image)) {
							String randomString = dishService.generateRandomImageName();
							imageName = randomString.concat(fileName);
							break;
						} else {
							imageName = fileName;
						}
					}
					java.io.InputStream inputStream = file.getInputStream();
					dishService.savePicture(inputStream, imageName);

					if (dishService.insertDish(dish, adminId, imageName)) {
						messages.showInfoMessage("Dish was added successfully");
						dish = new Dish();
						return "dish.xhtml?show=1faces-redirect=true";
					} else {
						messages.showInfoMessage("Something went wrong");
						return "addDish.xhtml";
					}
				} catch (IOException e) {
					messages.showInfoMessage("Something went wrong");

				}
			} else {
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

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public WelcomeManagedBean getWelcomeManagedBean() {
		return welcomeManagedBean;
	}

	public void setWelcomeManagedBean(WelcomeManagedBean welcomeManagedBean) {
		this.welcomeManagedBean = welcomeManagedBean;
	}

	public AdminService getAdminService() {
		return adminService;
	}

	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}

}
