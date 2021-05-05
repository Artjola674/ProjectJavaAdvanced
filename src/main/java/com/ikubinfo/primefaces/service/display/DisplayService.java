package com.ikubinfo.primefaces.service.display;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ikubinfo.primefaces.model.display.dish;
import com.ikubinfo.primefaces.repository.display.DisplayRepository;

@Service
public class DisplayService {
private DisplayRepository displayrepository;
	

	public DisplayService(DisplayRepository displayrepository) {
		super();
		this.displayrepository = displayrepository;
	}

	public List<dish> getAllDishes(String category) {
		List<dish> dishes=new ArrayList<>();
		dishes = displayrepository.getAllDishes(category);
		return dishes;
	}
	public List<String> getAllCategories(){
		return displayrepository.getAllCategories();
	}
}