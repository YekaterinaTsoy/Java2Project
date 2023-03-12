package db.daos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import db.entities.Person;
/**
 * @author 	Julia RUIZ CARRERO, Carlos JIMÉNEZ GARCÍA, Yekaterina TSOY
 *
 */

public class PersonDao { 
	
	public static List<Person> listPersons() {
		List<Person> listOfPerson = new ArrayList<>();

		try(Connection connection = DataSourceFactory.getDataSource().getConnection()){
			try(Statement statement = connection.createStatement()){
				try(ResultSet results = statement.executeQuery("SELECT * FROM person")){
					while(results.next()){
						listOfPerson.add( new Person(
								results.getInt("idperson"),
								results.getString("lastname"),
								results.getString("firstname"),
								results.getString("nickname"),
								results.getString("phone_number"),
								results.getString("address"),
								results.getString("email_address"),
								results.getDate("birth_date")));
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listOfPerson;

	}

   public Person getPerson(String nickname) {
		try (Connection connection = DataSourceFactory.getDataSource().getConnection()) {
	        try (PreparedStatement statement = connection.prepareStatement(
	                    "select * from person where nickname=?")) {
	            statement.setString(1, nickname);
	            try (ResultSet results = statement.executeQuery()) {
	                if (results.next()) {
	                    return new Person(results.getInt("idperson"), 
	                    		results.getString("lastname"), results.getString("firstname"), 
	                    		results.getString("nickname"), results.getString("phone_number"), 
	                    		results.getString("address"),results.getString("email_address"), 
	                    		results.getDate("birth_date"));
	                }
	            }
	        }
	    } catch (SQLException e) {
	        // Manage Exception
	        e.printStackTrace();
	    }
		return null;
	}
	
	public static Person addPerson(Person person) {
		 try (Connection connection = DataSourceFactory.getDataSource().getConnection()) {
		        String sqlQuery = "INSERT INTO person(lastname, firstname, nickname, phone_number, address, email_address, birth_date) "
		        		+ "VALUES(?, ?, ?, ?, ?, ?, ?)";
		        try (PreparedStatement statement = connection.prepareStatement(
		                        sqlQuery, Statement.RETURN_GENERATED_KEYS)) {
		            statement.setString(1, person.getLastName());
		            statement.setString(2, person.getFirstName());
		            statement.setString(3, person.getNickName());
		            statement.setString(4, person.getPhone_number());
		            statement.setString(5, person.getAddress());
		            statement.setString(6, person.getEmail_address());
		            statement.setDate(7, person.getBirth_date());
		            
		            statement.executeUpdate();
		            ResultSet ids = statement.getGeneratedKeys();
		            if (ids.next()) {
		                return new Person(ids.getInt(1), person.getLastName(), person.getFirstName(), 
		                		person.getNickName(), person.getPhone_number(), person.getAddress(), 
		                		person.getEmail_address(), person.getBirth_date());
		            }
		        }
		    }catch (SQLException e) {
		        // Manage Exception
		        e.printStackTrace();
		    }
		    return null;
	}
	


	public static void deletePerson(int id) throws SQLException {
		 try (Connection connection = DataSourceFactory.getDataSource().getConnection()) {
			 try (PreparedStatement statement = connection.prepareStatement(
	                 "delete from person where idperson = ?")) {
				 statement.setInt(1, id);
		         statement.executeUpdate();
		        }
		    }catch (SQLException e) {
		        // Manage Exception
		        e.printStackTrace();
		    }
	}
	
	public int findId(List<Person> list, String lastName) {
		Person person = findPerson(list, lastName);
		return person.getIdPerson();
	}
	
	public Person findPerson(List<Person> list, String lastName) {
		
		int i=0;
		while(i<list.size()) {
			if(lastName.equals(list.get(i).getLastName())) {
				return list.get(i);
			}
			else {i++;}
		}
		return null;
		
	}

	
	public void updatePerson(int id, Person person) throws SQLException {
		String sqlQuery = "UPDATE person SET lastName=?, firstName=?, nickName=?, phone_number=?, address=?, email_address=?, birth_date=? WHERE idperson = ?";
	    try (Connection connection = DataSourceFactory.getDataSource().getConnection()) {
	    	try (PreparedStatement statement = connection.prepareStatement(
                    sqlQuery, Statement.RETURN_GENERATED_KEYS)) {
	            statement.setString(1, person.getLastName());
	            statement.setString(2, person.getFirstName());
	            statement.setString(3, person.getNickName());
	            statement.setString(4, person.getPhone_number());
	            statement.setString(5, person.getAddress());
	            statement.setString(6, person.getEmail_address());
	            statement.setDate(7, person.getBirth_date());
	            statement.setInt(8, id);
	            statement.executeUpdate();
	            
	    	} catch (SQLException e) {
	    		System.out.println(e.getMessage());
	    	}
	    }
	}
}