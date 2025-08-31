package com.farfor.journalApp.services;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.farfor.journalApp.entity.JournalEntry;
import com.farfor.journalApp.repository.JournalEntryRepo;

//@Component Isko componenet karegay jisse ki dependencyinject kar paye
@Service
public class JournalEntryService {

    @Autowired
    private JournalEntryRepo journalEntryRepo;

    public JournalEntry saveEntry(JournalEntry je) {
        journalEntryRepo.save(je);
        return je;
    }

    public List<JournalEntry> getAll(){
        return journalEntryRepo.findAll();
    }

    public Optional<JournalEntry> getbyID(ObjectId myid){
        return journalEntryRepo.findById(myid);
    }

    public boolean deleteById(ObjectId id){
        journalEntryRepo.deleteById(id);
        return true;
    }
}



//this is the bridge between controller and repo for mongodb 
//All business logic is written here just we are calling it from controller