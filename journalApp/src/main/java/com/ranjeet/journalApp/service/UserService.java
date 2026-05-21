package com.ranjeet.journalApp.service;

import com.ranjeet.journalApp.entity.JournalEntry;
import com.ranjeet.journalApp.entity.User;
import com.ranjeet.journalApp.repositroy.JournalEntryRepository;
import com.ranjeet.journalApp.repositroy.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void saveEntry(User user){
        userRepository.save(user);
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public Optional<User> findById(ObjectId id){
        return userRepository.findById(id);
    }

    public void deleteById(ObjectId id){
        userRepository.deleteById(id);
    }

    public User findByuserName(String userName){
        return userRepository.findByuserName(userName);
    }
}


// Controller ---> Service ---> Repository
