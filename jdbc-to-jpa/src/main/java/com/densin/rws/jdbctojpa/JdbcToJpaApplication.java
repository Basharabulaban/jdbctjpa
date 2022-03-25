package com.densin.rws.jdbctojpa;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
		logger.info("userid 1 -> {} ",	dao.findbyId(1));
		logger.info("users by -> {} ",	dao.findbylocation("Amman"));
	}

}
