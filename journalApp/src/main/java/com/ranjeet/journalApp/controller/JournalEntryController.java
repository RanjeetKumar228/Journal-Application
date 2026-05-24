package com.ranjeet.journalApp.controller;

import com.ranjeet.journalApp.entity.JournalEntry;
import com.ranjeet.journalApp.entity.User;
import com.ranjeet.journalApp.service.JournalEntryService;
import com.ranjeet.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService JournalEntryService;

    @Autowired
    private UserService userService;

    @GetMapping("{userName}")
    public ResponseEntity<?> getAllJournalEntryOfUser(@PathVariable String userName) {       //localhost// :8080/journal// GET
        User user = userService.findByuserName(userName);
        List<JournalEntry> all = user.getJournalEntries();
        if (all != null && !all.isEmpty()) {
            return new ResponseEntity<>(all,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("{userName}")
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry,@PathVariable String userName) {   //localhost:8080/journa// POST
        try {
            User user = userService.findByuserName(userName);
            JournalEntryService.saveEntry(myEntry,userName);
            return new ResponseEntity<JournalEntry>(myEntry,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<JournalEntry>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("id/{myId}")
    public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable ObjectId myId) {
        Optional<JournalEntry> journalEntry = JournalEntryService.findById(myId);
        if (journalEntry.isPresent()) {
            return new ResponseEntity<JournalEntry>(journalEntry.get(), HttpStatus.OK);
        }
        return new ResponseEntity<JournalEntry>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("id/{userName}/{myId}")
    public ResponseEntity<?> deleteJournalEntryById(@PathVariable ObjectId myId, @PathVariable String userName) {
        JournalEntryService.deleteById(myId,userName);
        return new ResponseEntity<JournalEntry>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("id/{userName}/{id}")
    public ResponseEntity<?> updateJournalEntryById(
            @PathVariable ObjectId id,
            @RequestBody JournalEntry newEntry,
            @PathVariable String userName
    ) {
        JournalEntry old = JournalEntryService.findById(id).orElse(null);
        if(old != null){
            old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : old.getTitle());
            old.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent() : old.getContent());
            JournalEntryService.saveEntry(old);
            return new ResponseEntity<JournalEntry>(old,HttpStatus.OK);
        }
        return new ResponseEntity<JournalEntry>(HttpStatus.NOT_FOUND);
    }

}


//Controller --> Special class --> special component
