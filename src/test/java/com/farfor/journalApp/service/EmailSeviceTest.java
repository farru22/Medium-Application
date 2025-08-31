package com.farfor.journalApp.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.farfor.journalApp.services.EmailService;

@SpringBootTest
public class EmailSeviceTest {
    @Autowired
    private EmailService emailService;

    @Test
    void testSendMail(){
        emailService.sendEmail("ayaanakhtar1010@gmail.com", "Signature paan masala", "Khalo signature karlo duniya muthi mei!!!!");
    }
}
