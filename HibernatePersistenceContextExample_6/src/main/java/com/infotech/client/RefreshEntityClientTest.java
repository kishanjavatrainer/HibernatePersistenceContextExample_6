package com.infotech.client;

import java.sql.Statement;

import org.hibernate.Session;

import com.infotech.entities.Person;
import com.infotech.util.HibernateUtil;

public class RefreshEntityClientTest {

	public static void main(String[] args) {
		
		try(Session session = HibernateUtil.getSessionFactory().openSession() ) {
			Person person = session.byId(Person.class).load(1L);
			
			session.doWork(connection->{
				try(Statement statement = connection.createStatement()){
					statement.executeUpdate("UPDATE Person SET name=LOWER(name)");
				}
			});
			
			session.refresh(person);
			System.out.println(person.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
