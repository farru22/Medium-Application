package com.farfor.journalApp.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.farfor.journalApp.entity.JournalEntry;


public interface JournalEntryRepo extends MongoRepository<JournalEntry, ObjectId> {
}



//Mongo repo this repo will communicate with the mongodb and service will call it to make mongodb communication

//Repo ko banane ke baad ismei kuch nahi karna just isko inject karna hai services mei


// This interface is a Repository for JournalEntry.
// By extending MongoRepository, it gives us built-in methods 
// to perform CRUD (Create, Read, Update, Delete) operations on MongoDB.
//
// How it works:
// - At compile time, Spring Data uses proxy generation and reflection to 
//   automatically create a class that implements this interface.
// - You don’t write the implementation yourself; Spring generates it for you.
// - During application startup, Spring scans this interface, detects it as a 
//   repository, and wires it into the application context as a Spring Bean.
// - When you call methods (like save, findAll, deleteById), Spring Data 
//   translates those method calls into the correct MongoDB queries.
