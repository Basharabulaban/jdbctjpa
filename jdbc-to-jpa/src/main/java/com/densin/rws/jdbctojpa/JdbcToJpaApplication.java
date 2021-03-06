package com.densin.rws.jdbctojpa;



import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.densin.rws.jdbctojpa.entity.Person;

import net.bytebuddy.asm.Advice.This;

@SpringBootApplication
public class JdbcToJpaApplication implements CommandLineRunner {
	
	private Logger logger = LoggerFactory.getLogger(This.class);
@Autowired
	PersonJdbcDao dao;
	public static void main(String[] args) {
		SpringApplication.run(JdbcToJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	
		logger.info("All Users -> {}",	dao.findAll());
		logger.info("specfic User by ID 1 -> {} ",	dao.findbyId(1));
		logger.info("users by LOcation -> {} ",	dao.findbylocation("Amman"));
		logger.info("users by name -> {} ",	dao.findbyname("bashar"));
		logger.info("number of ROWS DELETED -> - {} ",		dao.deletebyname("bashar"));
		logger.info("users by name -> {} ",	dao.findbyname("bashar"));
		logger.info("uinsert by Person  -> {} ",	dao.insert_by_object(new Person(5,"Abood","Bagdad",new Date())));
//		logger.info("delete name based on id and name -> - ",	dao.deleteby_ID_name(2,"Ahmad"));
		logger.info("All Users -> {}",	dao.findAll());
		logger.info("update by name -> {} ",	dao.update_by_id(new Person(5,"Sami","Bagdad",new Date())));
//		logger.info("insert name based on id and name -> - ",	dao.insertrecord(6,"Ahmad","Riyadh",new Date()));
		logger.info("All Users -> {}",	dao.findAll());
	}

}
