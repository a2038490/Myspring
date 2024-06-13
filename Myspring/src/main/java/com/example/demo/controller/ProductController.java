package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;


import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins="http://localhost:8081/")
public class ProductController {
	
	@Autowired
	private ProductRepository productrepository;
	
	@GetMapping
	public List<Product> getAll(@RequestParam Long userid){
		return productrepository.findByuserid(userid);
	}
	
	@PostMapping
	public Product addProduct(@RequestBody Product product) {
		return productrepository.save(product);
	}
	
	@PutMapping("/{no}")
	public Product updateProduct(@PathVariable Long no,@RequestBody Product newproduct) {
		return productrepository.findById(no)
				.map(product -> {
					product.setProductname(newproduct.getProductname());
					product.setPrice(newproduct.getPrice());
					product.setNum(newproduct.getNum());
					product.setFeerate(newproduct.getFeerate());
					return productrepository.save(product);
				})
				.orElseGet(() -> {
					newproduct.setNo(no);
					return productrepository.save(newproduct);
				});
	}
	
	@DeleteMapping("/{no}")
	public void deleteProduct(@PathVariable Long no) {
		productrepository.deleteById(no);
	}
	
	@GetMapping("/{no}")
	public Optional<Product> getproductByno(@PathVariable Long no) {
		return productrepository.findById(no);
	}

}
