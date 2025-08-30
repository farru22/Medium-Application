package com.farfor.journalApp.Controller;

//controller ---> Service ----> Repository This will be our calling pattern and
// best practice for software development
//Journal entry ab ek user ke according banegi puri

import com.farfor.journalApp.entity.JournalEntry;
import com.farfor.journalApp.entity.UserEntry;
import com.farfor.journalApp.services.JournalEntryService;
import com.farfor.journalApp.services.UserEntryService;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // rest api creation plus give mapping like get post put delete
@RequestMapping("/journal")
public class JournalEntryControllerV2 {

    @Autowired // Dependencies injection
    private JournalEntryService journalEntryService;

    @Autowired
    private UserEntryService userEntryService;
    //Authentication authentication= (Authentication) SecurityContextHolder.getContext().getAuthentication();
    //String username=((Principal) authentication).getName();
    //These line above the mapping make sure the user is loged in and authenticated

    // Getting all the Journalentries of the User
    @GetMapping("/all")
    public ResponseEntity<?> getAllEntriesByUSer() {
        Authentication authentication= (Authentication) SecurityContextHolder.getContext().getAuthentication();
        String username=((Principal) authentication).getName();
        UserEntry user = userEntryService.findByUsername(username);
        List<JournalEntry> all = user.getJournalEntries();
        if (all != null && !all.isEmpty()) {
            return new ResponseEntity<>(all, HttpStatus.OK);// Response Entity for https status
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //Adding journal through the user
    @Transactional // Atomiticy and Isolation achieve for this method
    @PostMapping
    public ResponseEntity<?> createEntry(@RequestBody JournalEntry myEntry) {
        Authentication authentication= (Authentication) SecurityContextHolder.getContext().getAuthentication();
        String username=((Principal) authentication).getName();
        UserEntry user = userEntryService.findByUsername(username);
        myEntry.setDate(LocalDateTime.now());
        JournalEntry saved = journalEntryService.saveEntry(myEntry);
        user.getJournalEntries().add(saved);
        userEntryService.saveEntry(user);

        return new ResponseEntity<>("Entry added successfully", HttpStatus.CREATED);
    }

    @GetMapping("/id/{myId}")
    public ResponseEntity<?> getJournalById(@PathVariable ObjectId myId) {
        Authentication authentication= (Authentication) SecurityContextHolder.getContext().getAuthentication();
        String username=((Principal) authentication).getName();
        UserEntry user = userEntryService.findByUsername(username);
        for (JournalEntry entry : user.getJournalEntries()) {
            if (entry.getId().equals(myId)) {
                return new ResponseEntity<>(entry, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Transactional
    @DeleteMapping("/id/{myId}")
    public ResponseEntity<?> delById(@PathVariable ObjectId myId) {
        Authentication authentication= (Authentication) SecurityContextHolder.getContext().getAuthentication();
        String username=((Principal) authentication).getName();
        UserEntry user = userEntryService.findByUsername(username);
        for (JournalEntry entry : user.getJournalEntries()) {
            if (entry.getId().equals(myId)) {
                journalEntryService.deleteById(myId);
                user.getJournalEntries().remove(entry);
                userEntryService.saveEntry(user);
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/id/{myId}")
    public ResponseEntity<?> updateById(@PathVariable ObjectId myId,
            @RequestBody JournalEntry newEntry) {
                Authentication authentication= (Authentication) SecurityContextHolder.getContext().getAuthentication();
        String username=((Principal) authentication).getName();
        UserEntry user = userEntryService.findByUsername(username);
        for (JournalEntry oldEntry : user.getJournalEntries()) {
            if (oldEntry.getId().equals(myId)) {
                // Update only non-null fields
                if (newEntry.getContent() != null)
                    oldEntry.setContent(newEntry.getContent());
                if (newEntry.getTitle() != null)
                    oldEntry.setTitle(newEntry.getTitle());

                journalEntryService.saveEntry(oldEntry);
                userEntryService.saveEntry(user); // Save updated user data
                return new ResponseEntity<>("Entry updated successfully", HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("Journal entry not found", HttpStatus.NOT_FOUND);
    }

}

// package com.farfor.journalApp.Controller;

// import com.farfor.journalApp.entity.JournalEntry;

// import java.util.ArrayList;
// import java.util.HashMap;
// import java.util.Map;

// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// @RestController
// @RequestMapping("/_journal")
// public class JournalEntryController {

// private Map<Long, JournalEntry> journalEntries = new HashMap<>();

// @GetMapping
// public ArrayList<JournalEntry> getAll() {
// return new ArrayList<>(journalEntries.values());
// }

// @PostMapping
// public boolean createEntry(@RequestBody JournalEntry myEntry) {
// journalEntries.put(myEntry.getId(), myEntry);
// return true;
// }

// @GetMapping("/id/{myid}")
// public JournalEntry getEntrybyID(@PathVariable Long myid) {
// return journalEntries.get(myid);
// }

// @DeleteMapping("/deleteID/{myId}")
// public boolean deleteByID(@PathVariable Long myId) {
// journalEntries.remove(myId);
// return true;
// }

// @PutMapping("update/{myId}")
// public boolean updateByid(@PathVariable Long myId, @RequestBody JournalEntry
// newEntry) {
// JournalEntry je = journalEntries.get(myId);
// if (newEntry.getTitle() != null) {
// je.setTitle(newEntry.getTitle());
// }
// if (newEntry.getContent() != null) {
// je.setContent(newEntry.getContent());
// }

// // Save updated entry back
// journalEntries.put(myId, je);
// return true;

// }
// }
