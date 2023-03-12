package isen.project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

import db.entities.Person;
/**
 * @author 	Julia RUIZ CARRERO, Carlos JIMÉNEZ GARCÍA, Yekaterina TSOY
 *
 */
public class VCard {

	private Person person;
	
	public VCard(Person person) {
		this.person=person;
	}
	
	public String generateStr() {
		String str="BEGIN:VCARD\n" + 
			     "VERSION:4.0\n" +
				"N:"+person.getLastName()+";"+person.getFirstName()+";;;\n"+
			     "NICKNAME:"+person.getNickName()+"\n"+
				"FN:"+person.getFirstName()+" "+person.getLastName()+"\n"+
				"TEL;TYPE=cell:"+person.getPhone_number()+"\n"+
				"ADR;TYPE=home:;;"+person.getAddress()+"\n"+
				"EMAIL:"+person.getEmail_address()+"\n"+
				"BDAY:"+dateToStr(person.getBirth_date())+"\n"+
				"END:VCARD";
		
		return str;
	}
	
	public String dateToStr(Date date) {
		String str=this.person.getBirth_date().toString();
		return str;
	}
	
	public void createFile() throws IOException {
		File f=new File(this.person.getFirstName()+"_"+this.person.getLastName()+".vcf");
		
		try {
			FileOutputStream fop=new FileOutputStream(f);
			
			if(f.exists())
			  {String str=this.generateStr();
			   fop.write(str.getBytes());
			   fop.flush();
			   fop.close();
			   System.out.println("Successfully written");
			  } else 
			   System.out.println("The file does not exist");
			 
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
}
