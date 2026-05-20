package com.ranjeet.journalApp.service;

import com.ranjeet.journalApp.entity.JournalEntry;
import com.ranjeet.journalApp.repositroy.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository JournalEntryRepository;

    public void saveEntry(JournalEntry JournalEntry){
        JournalEntryRepository.save(JournalEntry);
    }

    public List<JournalEntry> getAll(){
        return JournalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id){
        return JournalEntryRepository.findById(id);
    }

    public void deleteById(ObjectId id){
        JournalEntryRepository.deleteById(id);
    }
}


// Controller ---> Service ---> Repository
