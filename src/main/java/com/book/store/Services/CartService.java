package com.book.store.Services;


import com.book.store.Entities.Cart;
import com.book.store.dao.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    public List<Cart> getAllCarts()
    {
        List<Cart> list=(List<Cart>)this.cartRepository.findAll();
        return list;
    }
    //Adding the Cart
    public Cart addcart(Cart b){
        Cart result=cartRepository.save(b);
        return result;
    }
}
