package com.farfor.journalApp.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.farfor.journalApp.entity.UserEntry;
import com.farfor.journalApp.repository.UserEntryRepo;

@Component//Isko componenet karegay jisse ki dependencyinject kar paye
public class UserEntryService {

    @Autowired
    private UserEntryRepo userEntryRepo;

    private static final PasswordEncoder passwordEncode= new BCryptPasswordEncoder();

    //Get all user by admin
    public List<UserEntry> getAll(){
        return userEntryRepo.findAll();
    }

    //Save User with password encoded in db
    public boolean saveNewUser(UserEntry ue) {
        passwordEncode.encode(ue.getPassWord());
        ue.setPassWord(passwordEncode.encode(ue.getPassWord()));
        ue.setRoles(Arrays.asList("User"));
        userEntryRepo.save(ue);
        return true;
    }
    public boolean saveEntry(UserEntry ue) {
        userEntryRepo.save(ue);
        return true;
    }
    //Save Admin with its role
     public boolean saveAdminUser(UserEntry ue) {
        passwordEncode.encode(ue.getPassWord());
        ue.setPassWord(passwordEncode.encode(ue.getPassWord()));
        ue.setRoles(Arrays.asList("User","ADMIN"));
        userEntryRepo.save(ue);
        return true;
    }

    public UserEntry findByUsername(String userName){
        return userEntryRepo.findByuserName(userName);
    }
}



//this is the bridge between controller and repo for mongodb 
//All business logic is written here just we are calling it from controller