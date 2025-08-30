package com.farfor.journalApp.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.farfor.journalApp.entity.UserEntry;

public interface UserEntryRepo extends MongoRepository <UserEntry,ObjectId> {

    UserEntry findByuserName(String userName);
    void deleteByuserName(String userName);
    //Jab ham indexing karegay toh ham ye username wala function bana ke username ke basis par search kar sakte hai
}
