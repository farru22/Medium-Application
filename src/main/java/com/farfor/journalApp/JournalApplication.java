package com.farfor.journalApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication //sab autoConfigure karlega ye
@EnableTransactionManagement //This is the transcational Atomity achiever
public class JournalApplication {

	public static void main(String[] args) {
		SpringApplication.run(JournalApplication.class, args);
	}

	@Bean
	public PlatformTransactionManager add(MongoDatabaseFactory dbfactory){
		return new MongoTransactionManager(dbfactory);
	}
	//Ye implementation banana padta hai transaction isolation karne ke liye

}
//Main application yahi se chalgi
//Application properties mei mongo url dengay aur sab dengay aur pojo mei Document annotation deke usko collection ka name bata sakte hai
//pom.xml mei sari dependency dalengay like uska cordinates aur usko pel ke use karegay project mei
//Aur kuch zayada hai nahi step wise sab add aur connect karte hai 
//Controller hi sab control karegay call karega api mei 