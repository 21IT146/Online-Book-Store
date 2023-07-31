package com.book.store.Controllers;


import com.book.store.Entities.Order;
import com.book.store.Services.OrderService;
import com.book.store.dao.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    @PostMapping("/order")
    public ResponseEntity<Order> addOrder(@RequestBody Order order){

        Order b1=null;
        try {
            b1 = this.orderService.addorder(order);
            b1.setTime(orderService.getLatestTime());
            this.orderRepository.save(b1);
            System.out.println(order);
            return ResponseEntity.of(Optional.of(b1));
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("/order")
    public ResponseEntity<List<Order>> getorder()
    {
        List<Order> list= this.orderService.getAllorders();
        if(list.size()<=0)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(list);
    }
    @GetMapping("/order/{id}")
    public ResponseEntity<Order> getorder(@PathVariable("id") int id)
    {

        Order order =orderService.getOrderById(id);

        if(order ==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(order));
    }
    //delete order handler
    @DeleteMapping("/orders/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable("orderId") int orderId){
        try {
            this.orderService.deleteOrder(orderId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //update order handler
    @PutMapping("/orders/{id}")
    public ResponseEntity<Order> updateUser(@RequestBody Order order, @PathVariable("id") int id){
        try {
            this.orderService.updateOrder(order,id);
            return ResponseEntity.ok().body(order);
        }catch(Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }
}
