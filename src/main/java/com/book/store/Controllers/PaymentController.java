package com.book.store.Controllers;


import com.book.store.Entities.Payment;
import com.book.store.Services.PaymentService;
import com.book.store.dao.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PaymentController {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/payment")
    public ResponseEntity<Payment> addPayment(@RequestBody Payment payment){

        Payment b1=null;
        try {
            b1 = this.paymentService.addpayment(payment);
            b1.setTime(paymentService.getLatestTime());
            this.paymentRepository.save(b1);
            System.out.println(payment);
            return ResponseEntity.of(Optional.of(b1));
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("/payment")
    public ResponseEntity<List<Payment>> getpayment()
    {
        List<Payment> list= this.paymentService.getAllpayments();
        if(list.size()<=0)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(list);
    }
    @GetMapping("/payment/{id}")
    public ResponseEntity<Payment> getpayment(@PathVariable("id") int id)
    {

        Payment payment =paymentService.getPaymentById(id);

        if(payment ==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(payment));
    }
    //delete payment handler
    @DeleteMapping("/payments/{paymentId}")
    public ResponseEntity<Void> deletePayment(@PathVariable("paymentId") int paymentId){
        try {
            this.paymentService.deletePayment(paymentId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //update payment handler
    @PutMapping("/payments/{paymentId}")
    public ResponseEntity<Payment> updatePayment(@RequestBody Payment payment, @PathVariable("paymentId") int paymentId){
        try {
            this.paymentService.updatePayment(payment,paymentId);
            return ResponseEntity.ok().body(payment);
        }catch(Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }
}
