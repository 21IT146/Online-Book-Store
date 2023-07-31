package com.book.store.Services;


import com.book.store.Entities.Order;
import com.book.store.dao.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Component
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getAllorders()
    {
        List<Order> list=this.orderRepository.findAll();
        return list;
    }
    public Order getOrderById(int id){
        Order order=null;
        try {

            order=this.orderRepository.findById(id);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return order;
    }

    //Adding the Order
    public Order addorder(Order b){
        Order result=orderRepository.save(b);
        return result;
    }

    public void deleteOrder(int bid){

        orderRepository.deleteById(bid);
    }

    //update Order
    public void updateOrder(Order order, int id) {

        order.setId(id);
        orderRepository.save(order);
    }

    public String getLatestTime()
    {
        Order d1=new Order();
        Date time=new Date();
        Timestamp ts=new Timestamp(time.getTime());
        return ts.toString();
    }

}
