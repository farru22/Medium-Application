package com.farfor.journalApp.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.farfor.journalApp.entity.JournalEntry;

public interface JournalEntryRepo extends MongoRepository<JournalEntry, ObjectId> {
}



//Mongo repo this repo will communicate with the mongodb and service will call it to make mongodb communication

//Repo ko banane ke baad ismei kuch nahi karna just isko inject karna hai services mei