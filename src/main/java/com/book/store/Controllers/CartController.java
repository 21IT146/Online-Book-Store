package com.book.store.Controllers;

import com.book.store.Entities.Cart;
import com.book.store.Services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/cart")
    public ResponseEntity<Cart> addCart(@RequestBody Cart cart){

        Cart b1=null;
        try {
            b1 = this.cartService.addcart(cart);
            System.out.println(cart);
            return ResponseEntity.of(Optional.of(b1));
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("/cart")
    public ResponseEntity<List<Cart>> getcart()
    {
        List<Cart> list= this.cartService.getAllCarts();
        if(list.size()<=0)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(list);
    }

}
