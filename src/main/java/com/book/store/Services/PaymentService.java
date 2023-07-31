package com.book.store.Services;

import com.book.store.Entities.Payment;
import com.book.store.dao.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Component
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public List<Payment> getAllpayments()
    {
        List<Payment> list=this.paymentRepository.findAll();
        return list;
    }
    public Payment getPaymentById(int id){
        Payment payment=null;
        try {

            payment=this.paymentRepository.findById(id);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return payment;
    }

    //Adding the Payment
    public Payment addpayment(Payment b){
        Payment result=paymentRepository.save(b);
        return result;
    }

    public void deletePayment(int bid){

        paymentRepository.deleteById(bid);
    }

    //update Payment
    public void updatePayment(Payment payment, int paymentId) {

        payment.setId(paymentId);
        paymentRepository.save(payment);
    }

    public String getLatestTime()
    {
        Payment p1=new Payment();
        Date time=new Date();
        Timestamp ts=new Timestamp(time.getTime());
        return ts.toString();
    }

}
