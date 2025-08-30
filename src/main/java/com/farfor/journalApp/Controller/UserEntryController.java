package com.farfor.journalApp.Controller;


import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.farfor.journalApp.entity.UserEntry;
import com.farfor.journalApp.repository.UserEntryRepo;
import com.farfor.journalApp.services.UserEntryService;

@RestController
@RequestMapping("/user")
public class UserEntryController {
    
    @Autowired
    private UserEntryService userEntryService;
    @Autowired
    private UserEntryRepo userEntryRepo;


    @DeleteMapping
    public ResponseEntity<?> delById() {
        Authentication authentication= (Authentication) SecurityContextHolder.getContext().getAuthentication();
        String username=((Principal) authentication).getName();
        userEntryRepo.deleteByuserName(username);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<?> updateByUsername(@RequestBody UserEntry myEntry){
        Authentication authentication= (Authentication) SecurityContextHolder.getContext().getAuthentication();
        String username=((Principal) authentication).getName();
        UserEntry user=userEntryService.findByUsername(username);
        user.setUserName(myEntry.getUserName());
        user.setPassWord(myEntry.getPassWord());
        userEntryService.saveNewUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
} 



//Ye wali line hamko direct user ka authentication lake uska username aur password lake degi authentication se
//Kuch nahi karna hai just vo dedega
//Authentication authentication= (Authentication) SecurityContextHolder.getContext().getAuthentication();
//Bas ham uska username lengay aur UserEntrService mei jake jo karna hai voh kardengay
//Hamne user create karna wala endpoint public kardiya hai aur inko authenticate kardiya hai