package com.ranjeet.journalApp.service;

import com.ranjeet.journalApp.entity.JournalEntry;
import com.ranjeet.journalApp.entity.User;
import com.ranjeet.journalApp.repositroy.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository JournalEntryRepository;

    @Autowired
    private UserService userService;

    public void saveEntry(JournalEntry JournalEntry, String userName){
        try {
            User user = userService.findByuserName(userName);
            JournalEntry.setDate(LocalDateTime.now());
            JournalEntry saved = JournalEntryRepository.save(JournalEntry);
            user.getJournalEntries().add(saved);
            userService.saveEntry(user);
        } catch (Exception e) {
            System.out.println(e.getMessage());;
        }
    }

    public void saveEntry(JournalEntry journalEntry){
        JournalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAll(){
        return JournalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id){
        return JournalEntryRepository.findById(id);
    }

    public void deleteById(ObjectId id, String userName){
        User user = userService.findByuserName(userName);
        user.getJournalEntries().removeIf(x -> x.getId().equals(id));
        userService.saveEntry(user);
        JournalEntryRepository.deleteById(id);
    }
}


// Controller ---> Service ---> Repository
