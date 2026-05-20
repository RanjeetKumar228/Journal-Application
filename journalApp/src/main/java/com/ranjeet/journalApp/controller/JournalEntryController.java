package com.ranjeet.journalApp.controller;

import com.ranjeet.journalApp.entity.JournalEntry;
import com.ranjeet.journalApp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService JournalEntryService;

    @GetMapping
    public List<JournalEntry> getAll() {                         //localhost:8080/journal   GET
        return JournalEntryService.getAll();
    }

    @PostMapping
    public JournalEntry createEntry(@RequestBody JournalEntry myEntry) {   //localhost:8080/journal    POST
        myEntry.setDate(LocalDateTime.now());
        JournalEntryService.saveEntry(myEntry);
        return myEntry;
    }

    @GetMapping("id/{myId}")
    public JournalEntry getJournalEntryById(@PathVariable ObjectId myId) {
        return JournalEntryService.findById(myId).orElse(null);
    }

    @DeleteMapping("id/{myId}")
    public boolean deleteJournalEntryById(@PathVariable ObjectId myId) {
        JournalEntryService.deleteById(myId);
        return true;
    }

    @PutMapping("id/{id}")
    public JournalEntry updateJournalEntryById(@PathVariable ObjectId id, @RequestBody JournalEntry newEntry) {
        JournalEntry old = JournalEntryService.findById(id).orElse(null);
        if(old != null){
            old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : old.getTitle());
            old.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent() : old.getContent());
        }
        JournalEntryService.saveEntry(old);
        return old;
    }
}


//Controller --> Special class --> special component
