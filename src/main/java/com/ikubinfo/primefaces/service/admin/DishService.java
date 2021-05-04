package com.ikubinfo.primefaces.service.admin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ikubinfo.primefaces.model.admin.Dish;
import com.ikubinfo.primefaces.repository.admin.DishRepository;

@Service
public class DishService {
	
	private DishRepository dishRepository;	

	public DishService(DishRepository dishRepository) {
		super();
		this.dishRepository = dishRepository;
	}


	public boolean insertDish(Dish dish, int adminId,String picture) {
		return dishRepository.insertDish(dish,adminId,picture);
	}
	
	public void savePicture(InputStream inputStream,String fileName) throws IOException {
		File directory = new File("C:\\Users\\kanac\\git\\ProjectJavaAdvanced\\src\\main\\webapp\\resources\\image");
		File file = new File(directory,fileName);
		dishRepository.savePicture(inputStream, file);
	}
	
	public int getAdminId(String email) {
		return dishRepository.getAdminId(email);
	}

	public List<String> getCategories(Boolean availability) {
		return dishRepository.getCategories(availability);
	}
	
	public List<String> getDishNames(String category, Boolean availability) {
		
		return dishRepository.getDishNames(category,availability);
	}


	public List<Dish> getAllDishes(String category, String dishName, Boolean availability) {
		return dishRepository.getAllDishes(category, dishName, availability);
	}
	
	public void availability(int dishId, boolean availability) {
		dishRepository.availability(dishId, availability);
	}

	public boolean delete(int dishId) {
		return dishRepository.delete(dishId);
	}


	public boolean save(Dish dish,String picture) {
		return dishRepository.save(dish,picture);
	}
	
	public List<String> getImages() {
		return dishRepository.getImages();
	}
		
	public String generateRandomImageName() {
		return dishRepository.generateRandomImageName();
	}
}
