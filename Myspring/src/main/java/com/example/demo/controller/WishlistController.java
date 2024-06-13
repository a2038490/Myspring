package com.example.demo.controller;



import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;


import com.example.demo.model.Wishlist;
import com.example.demo.model.User;
import com.example.demo.repository.WishlistRepository;
import com.example.demo.repository.UserRepository;

@RestController
@RequestMapping("/api/wishlists")
@CrossOrigin(origins="http://localhost:8081/")
public class WishlistController {
	
	@Autowired
	private WishlistRepository wishlistrepository;
	
	@Autowired
	private UserRepository userrepository;
	
	@GetMapping("/{userid}")
	public Wishlist getwishlistbyuser(@PathVariable Long userid) {
		Wishlist wishlist= wishlistrepository.findByuserid(userid);
		if(wishlist == null) {
			wishlist=new Wishlist();
			User user=userrepository.findById(userid).get();
			wishlist.setUserid(userid);
			wishlist.setAccount(user.getAccount());
			wishlist.setOrdernum(0);
			wishlist.setTotalfee(0.0);
			wishlist.setTotalamount(0.0);
			wishlistrepository.save(wishlist);
		}
		return wishlist;
	}
	
	@PostMapping
	public Wishlist addWishlist(@RequestBody Wishlist wishlist) {
		return wishlistrepository.save(wishlist);
	}
	
	@PutMapping("/{userid}")
	public Wishlist updateWishlist(@PathVariable Long userid,@RequestBody Wishlist newwishlist) {
		Wishlist wishlist=wishlistrepository.findByuserid(userid);
		wishlist.setOrdernum(newwishlist.getOrdernum());
		wishlist.setTotalfee(newwishlist.getTotalfee());
		wishlist.setTotalamount(newwishlist.getTotalamount());
		return wishlistrepository.save(wishlist);
				
	}
	
	@DeleteMapping
	public void deleteWishlist(@PathVariable Long id) {
		wishlistrepository.deleteById(id);
	}

}
