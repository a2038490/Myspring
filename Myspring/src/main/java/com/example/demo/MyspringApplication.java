package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.repository.UserRepository;
import com.example.demo.model.User;

@SpringBootApplication
public class MyspringApplication implements CommandLineRunner{
	
	@Autowired
	UserRepository userrepository;

	public static void main(String[] args) {
		SpringApplication.run(MyspringApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception{
		
		User user1 = new User();
		user1.setUsername("aa123");
		user1.setPassword("bb123");
		user1.setName("Ted");
		user1.setEmail("a111@gmail.com");
		user1.setAccount("123456789");
		
		User user2 = new User();
		user2.setUsername("aa124");
		user2.setPassword("bb124");
		user2.setName("Tony");
		user2.setEmail("b111@gmail.com");
		user2.setAccount("987654321");
		
		//userrepository.deleteAll();
		//userrepository.save(user1);
		//userrepository.save(user2);
		
	}
	
	

}
