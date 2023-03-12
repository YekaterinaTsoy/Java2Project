package db.entities;

import java.sql.Date; 
import java.time.LocalDate;
/**
 * @author 	Julia RUIZ CARRERO, Carlos JIMÉNEZ GARCÍA, Yekaterina TSOY
 *
 */
public class Person {
	
	private Integer idperson;
	private String lastname;
	private String firstname;
	private String nickname;
	private String phone_number;
	private String address;
	private String email_address;
	private Date birth_date;

	public Person() {
	}

	public Person(Integer idperson, String lastname, String firstname, String nickname, 
			String phone_number, String address, String email_address, Date birth_date) {
		super();
		this.idperson = idperson;
		this.firstname = firstname;
		this.lastname = lastname;
		this.nickname = nickname;
		this.phone_number = phone_number;
		this.address = address;
		this.email_address = email_address;
		this.birth_date = birth_date;
	}
	
	public Person(String lastname, String firstname, String nickname, String phone_number, String address, String email_address, LocalDate birth_date) {
		this.lastname=lastname;
		this.firstname=firstname;
		this.nickname=nickname;
		this.phone_number=phone_number;
		this.address=address;
		this.email_address=email_address;
		this.birth_date = Date.valueOf(birth_date);
		
	}

	public Integer getIdPerson() {
		return idperson;
	}

	public void setIdPerson(Integer idperson) {
		this.idperson = idperson;
	}

	public String getLastName() {
		return lastname;
	}

	public void setLastName(String lastname) {
		this.lastname = lastname;
	}

	public String getFirstName() {
		return firstname;
	}

	public void setFirstName(String firstname) {
		this.firstname = firstname;
	}

	public String getNickName() {
		return nickname;
	}

	public void setNickName(String nickname) {
		this.nickname = nickname;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail_address() {
		return email_address;
	}

	public void setEmail_address(String email_address) {
		this.email_address = email_address;
	}

	public Date getBirth_date() {
		return birth_date;
	}

	public void setBirth_date(Date birth_date) {
		this.birth_date = birth_date;
	}

}
