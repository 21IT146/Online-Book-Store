package com.book.store.Controllers;


import com.book.store.Entities.Form;
import com.book.store.dao.FormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Timestamp;
import java.util.Date;

@RestController
public class FormController {

    @Autowired
    private FormRepository formRepository;

//    @PostMapping("/form")
//    public String greetingSubmit(@RequestBody Form form, Model model) {
//
//
//        //System.out.println("password : "+form.getPassword());
//        model.addAttribute("form",form);
//        return "new";
//    }
//    @GetMapping("/form")
//    public String greetingForm(Model model) {
//        model.addAttribute("form", new Form());
//        return "index";
//    }


    @GetMapping("/form")
    public ModelAndView greetingForm(Model model) {
        //System.out.println("password : "+form.getPassword()+new Date().getHours()+new Date().getMinutes()+new Date().getSeconds());
        model.addAttribute("form", new Form());
        ModelAndView m = new ModelAndView();
        m.setViewName("index.html");
        return m;
    }

    @PostMapping("/form1")
    public String greetingSubmit(@RequestParam("password") String password,@RequestParam("username") String username) {
        //System.out.println("password : "+form.getPassword()+new Date().getHours()+new Date().getMinutes()+new Date().getSeconds());
        //model.addAttribute("form", form);
//        ModelAndView m = new ModelAndView();
//        m.setViewName("new.html")
        if(formRepository.existsByUsername(username)){
            return "false";
        }
        else {
            Form f=new Form();

            String newpassword=password;
            f.setUsername(username);
            f.setPassword(newpassword);
            formRepository.save(f);
            return newpassword;
        }

    }

    @PostMapping("/check")
    public String CheckPassword(@RequestParam("password") String password,@RequestParam("username") String username){
        Form f=new Form();
        Date date = new Date();
        Timestamp ts=new Timestamp(date.getTime());
        String find=formRepository.findByUsername(username).getPassword();
        if((find+ts.toString()).equals(password+ts.toString()))
        {
            return "true";
        }
        else {
            return "false";
        }

    }


}
