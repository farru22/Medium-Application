package com.farfor.journalApp.services;

//Ye jo hai voh user ki information fetch karne ke liye banaya hai
//Spring Security ki taraf se
//Yaha pe pehle se hi UserDetailsService naam ki ek interface hai jo ham use karegay user ko dhundne ke liye username se
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.farfor.journalApp.entity.UserEntry;
import com.farfor.journalApp.repository.UserEntryRepo;

@Service
public class UserDetailServiceImpl implements UserDetailsService{
    @Autowired
    private UserEntryRepo userEntryRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntry user=userEntryRepo.findByuserName(username);
        if(user!=null){
            return User.builder()
            .username(user.getUserName())
            .password(user.getPassWord())
            .roles(user.getRoles().toArray(new String[0]))
            .build();
        }
        throw new UsernameNotFoundException("User Not found with Username"+ username);
}
}










// User sends credentials → username + password.

// Spring Security calls UserDetailServiceImpl.loadUserByUsername(username).

// Our method:

// Finds user in DB.

// Creates a UserDetails object with username, encrypted password, roles.

// Returns this UserDetails to Spring Security.

// AuthenticationManager:

// Takes password from request and encrypted password from UserDetails.

// Uses PasswordEncoder to compare them.

// If match → user is authenticated ✅.




// What Spring Security does:

// When you send username & password, Spring Security calls loadUserByUsername(username) internally.

// But it doesn’t know your database or your UserEntry table.

// So we implement UserDetailsService to tell Spring Security how to find users.

// ✅ What this class does:

// Takes username from login request.

// Searches user in DB using userEntryRepo.findByuserName(username).

// If user exists → builds a Spring Security UserDetails object with:

// Username

// Encrypted password

// Roles (converted to String[])

// If user not found → throws UsernameNotFoundException.