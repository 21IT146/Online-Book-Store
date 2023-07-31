package com.book.store.Services;


import com.book.store.Entities.User;
import com.book.store.dao.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers()
    {
        List<User> list=this.userRepository.findAll();
        return list;
    }
    public User getUserById(int id){
        User user=null;
        try {

            user=this.userRepository.findById(id);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return user;
    }

    public User getUser(String name) {
        //System.out.println("hbvke");
        User user = userRepository.findByNameLike(name);
        //System.out.println("kvjdsnlj");
        return user;
    }

    //Adding the User
    public User adduser(User b){
        User result=userRepository.save(b);
        return result;
    }

    public void deleteUser(int bid){
//        list.stream().filter(book -> book.getId()!=bid).
//        collect(Collectors.toList());
        userRepository.deleteById(bid);
    }

    //update User
    public void updateUser(User user, int userId) {

        user.setId(userId);
        userRepository.save(user);
    }

}
