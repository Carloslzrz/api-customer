package com.customer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/helloworld")
public class CtrlHelloWorld {

	@GetMapping
	public String helloWorld() {
		return "Hello World!";
	}

	@GetMapping("/category")
	public List<Category> getCategories() {
		List<Category> categories = new ArrayList();
		categories.add(new Category(1,"Electrónica"));
		categories.add(new Category(2,"Línea Blanca"));
		return categories;
	}
	
	private class Category{
		private Integer id;
		private String category;
		
		public Category(Integer id, String category) {
			super();
			this.id = id;
			this.category = category;
		}
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public String getCategory() {
			return category;
		}
		public void setCategory(String category) {
			this.category = category;
		}
		
		
	}
}


