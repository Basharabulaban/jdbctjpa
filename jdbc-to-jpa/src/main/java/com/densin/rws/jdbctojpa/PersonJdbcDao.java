package com.densin.rws.jdbctojpa;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


import com.densin.rws.jdbctojpa.entity.Person;

@Repository
public class PersonJdbcDao{
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	
	class PersonRowMapper implements RowMapper<Person>{

		@Override
		public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
			Person person= new Person();
			
			person.setId(rs.getInt("id"));	
			person.setName(rs.getString("name"));	
			person.setLocation(rs.getString("location"));	
			person.setBirthDate(rs.getTimestamp("birth_date"));	
			return person;
		}
		
	}
	// you can chnage new BeanPropertyRowMapper<Person>(Person.class)    with       new PersonRowMapper()
	//select * from person
	public List<Person> findAll(){
	return	jdbcTemplate.query("select * from person",new PersonRowMapper());
		
	}
	
	public Person findbyId(int id){
	return	jdbcTemplate.queryForObject
			("select * from person where id=?", new Object[] {id},
					new BeanPropertyRowMapper<Person>(Person.class) ) ;
			
		
	}

	public List<Person> findbylocation(String location){
		return	jdbcTemplate.query
				("select * from person where location=?", new Object[] {location},
						new BeanPropertyRowMapper<Person>(Person.class) ) ;
				
			
		}
	public List<Person> findbyname(String name){
		return	jdbcTemplate.query
				("select * from person where name=?", new Object[] {name},
						new BeanPropertyRowMapper<Person>(Person.class) ) ;
		}

	public int deletebyname(String name) {
	
		return jdbcTemplate.update
				("delete from person where name=?", new Object[] {name} ) ;
	}

	public Object deleteby_ID_name(int id, String name) {
		return jdbcTemplate.update
				("delete from person where id =? and name=?", new Object[] {id,name} ) ;
	}

	public Object insertrecord(int id, String name, String location, Date birth_date) {
		return	jdbcTemplate.update
		("insert into person  (id,name,location,birth_date) values(?,?,?,?)" , new Object[] {id,name,location,birth_date} ) ;

	
	}
	
	public Object insert_by_object(Person person) {
		return	jdbcTemplate.update
		("insert into person  (id,name,location,birth_date) values(?,?,?,?)" , new Object[] {person.getId(),person.getName(),person.getLocation(),new Timestamp(person.getBirthDate().getTime()) } ) ;

	
	}

	public Object update_by_id(Person person) {
		return	jdbcTemplate.update
		("update person  set   name= ?,location= ?,birth_date= ?   where id= ?" , new Object[] {person.getName(),person.getLocation(),new Timestamp(person.getBirthDate().getTime()) ,person.getId() } ) ;
	}
	

	
	
}
