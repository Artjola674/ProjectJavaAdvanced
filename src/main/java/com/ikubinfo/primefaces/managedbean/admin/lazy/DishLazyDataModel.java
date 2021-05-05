package com.ikubinfo.primefaces.managedbean.admin.lazy;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.component.datatable.DataTable;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;

import com.ikubinfo.primefaces.model.admin.Dish;
import com.ikubinfo.primefaces.service.admin.DishService;
import com.ikubinfo.primefaces.service.admin.request.DishListRequest;
import com.ikubinfo.primefaces.service.admin.response.DishListResponse;

@ManagedBean
@ViewScoped
public class DishLazyDataModel extends LazyDataModel<Dish>{
	
	private static final long serialVersionUID = 6577854912082548822L;

	@ManagedProperty(value = "#{dishService}")
	private DishService dishService;
	
	private String category;
	private String dishName;
	private Boolean availability;

	private DataTable dataTable;

	private int first;
	private int pageSize;
	private int show;
	
	@PostConstruct
	public void init() {
		String showString =FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("show");
		show = Integer.parseInt(showString);
		if(show == 1) {
			this.availability = null;
		}else if(show == 2) {
			this.availability = true;
		}else if(show == 3) {
			this.availability = false;
		}

	}
	
	@Override
	public List<Dish> load(int first, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {
		this.first = first;
		this.pageSize = pageSize;
		
		DishListRequest request = new DishListRequest(availability,category, dishName,first, pageSize);

		DishListResponse response = dishService.getDishes(request);

		this.setRowCount(response.getTotalRows());

		return response.getDishes();
	}

	public void filter() {

		DishListRequest request = new DishListRequest(availability,category, dishName, first, pageSize);

		DishListResponse response = dishService.getDishes(request);

		this.setRowCount(response.getTotalRows());
		this.setWrappedData(response.getDishes());

	}

	public DishService getDishService() {
		return dishService;
	}

	public void setDishService(DishService dishService) {
		this.dishService = dishService;
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

	public DataTable getDataTable() {
		return dataTable;
	}

	public void setDataTable(DataTable dataTable) {
		this.dataTable = dataTable;
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

	public int getShow() {
		return show;
	}

	public void setShow(int show) {
		this.show = show;
	}
	
	
}
