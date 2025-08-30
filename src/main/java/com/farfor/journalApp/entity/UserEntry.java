package com.farfor.journalApp.entity;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Document(collection = "u_e") //This will be use to map pojo to mongo
@Data //Lombok to make getter setter sab automatically
@NoArgsConstructor
@AllArgsConstructor
public class UserEntry {
    @Id //Id will be mapped to the Mongo database from this pojo same for this class object
    private ObjectId id;
    @Indexed(unique = true)//Indexing given to make username unique plus make searching faster aur ye automatically create nahi hoga ye application properties mei add karna hoga
    @NonNull
    private String userName;
    @NonNull
    private String passWord;
    @DBRef //Iska matlab bas yahi hai ki ye JournalEntry ka reference rakhega ye List na ki pura Jo urnalEntry
    private List<JournalEntry> journalEntries=new ArrayList<>();
    private List<String> roles;
}
