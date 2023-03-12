package isen.project.view;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.scene.Parent;
import db.daos.PersonDao;
import db.entities.Person;
import javafx.stage.Stage;

import isen.project.VCard;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import javafx.scene.Scene;

/**
 * @author 	Julia RUIZ CARRERO, Carlos JIMÉNEZ GARCÍA, Yekaterina TSOY
 *
 */ 

public class FirstViewController implements Initializable{

    @FXML
    private Button addButton;

    @FXML
    private Button showAllPersonButton;
  
    @FXML
    private Button eraseButton;
    
    @FXML
    private Button deleteButton;
    
    @FXML
    private Button saveButton;

    @FXML
    private Button selectButton;
    
    @FXML
    private Button updateButton;

    @FXML
    private TextField addressText;

    @FXML
    private DatePicker birthDateText;   

    @FXML
    private TextField emailAdressText;

    @FXML
    private TextField firstNameText;

    @FXML
    private TextField lastNameText;

    @FXML
    private TextField nickNameText;

    @FXML
    private TextField phoneNumberText;
    
    @FXML
    private Label warningText;

    @FXML
    private ComboBox<String> selectPersonBar;

    private ObservableList<String> listPerson = FXCollections.observableArrayList();
    
    public List<Person> listPeople;
    
    private PersonDao personDao = new PersonDao();
    
    private Person personToDisplay = new Person();
    
    private int index;
    
    private Pattern mailRestrictions = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
    
	private Pattern phoneRestrictions = Pattern.compile("[+]+[0-9]{11,12}");
	
	AllPersonController add=new AllPersonController();
    
	/**
	 * Method used to initialize the list of people that will be shown in the select bar and
	 * to add a functionality to each button
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		listPeople = personDao.listPersons();
				
		while(index < listPeople.size()) {
			listPerson.add(listPeople.get(index).getLastName()+", "+listPeople.get(index).getFirstName()+" - "+listPeople.get(index).getPhone_number());
			index++;
		}
		
		selectPersonBar.setItems(listPerson);
		selectButton.setOnAction(this::displayInfo);
		eraseButton.setOnAction(this::eraseInfo);
		addButton.setOnAction(this::addPerson);
		deleteButton.setOnAction(this::deletePerson);
		updateButton.setOnAction(this::updatePerson);
		saveButton.setOnAction(this::saveAsVCard);
	}
	
	/**
	 * Method used to display the information of the person chosen in the text boxes.
	 * @param event
	 */
	
	@FXML
	private void displayInfo(ActionEvent event) {
		String data = "";
		
		if(selectPersonBar.getSelectionModel().getSelectedItem() != null) {
			warningText.setText("");
			data = selectPersonBar.getSelectionModel().getSelectedItem().toString();
			data = data.substring(0,data.indexOf(","));
			getPerson(findPerson(listPeople, data), findId(listPeople, data));
		}
	}
	
	/**
	 * Method used to erase the information displayed in the text boxes so they can be filled with new information.
	 * @param event
	 */
	public void eraseInfo(ActionEvent event) {
    	lastNameText.setText("");
    	firstNameText.setText("");
    	nickNameText.setText("");
    	phoneNumberText.setText("");
    	addressText.setText("");
    	emailAdressText.setText("");
    	birthDateText.setValue(null);	
	}
	
	/**
	 * Method used to initialize the person that is going to be displayed in the text boxes and
	 * to put the information of the person in the text boxes
	 * @param person
	 * @param id
	 */
	public void getPerson(Person person, int id) {
    	this.personToDisplay = person;
    	lastNameText.setText(personToDisplay.getLastName());
    	firstNameText.setText(personToDisplay.getFirstName());
    	nickNameText.setText(personToDisplay.getNickName());
    	phoneNumberText.setText(personToDisplay.getPhone_number());
    	addressText.setText(personToDisplay.getAddress());
    	emailAdressText.setText(personToDisplay.getEmail_address());
    	birthDateText.setValue(personToDisplay.getBirth_date().toLocalDate());	
	}
	
	/**
	 * Method used to find the person being looked for in a list
	 * @param list
	 * @param lastname
	 * @return the person looked for by their last name
	 */
	public Person findPerson(List<Person> list, String lastname) {
		int i=0;
		while(i<list.size()) {
			if(lastname.equals(list.get(i).getLastName())) {
				return list.get(i);
			}
			else {i++;}
		}
		return null;
			
	}
		
	/**
	 * Method used to find the id of the person that is being looked for
	 * @param list
	 * @param lastname
	 * @return the id of the person
	 */
	public int findId(List<Person> list, String lastname) {
		int i=0;
		while(i<list.size()) {
			if(lastname.equals(list.get(i).getLastName())) {
				return i+1;
			}
			else {i++;}
		}
		return 0;
	}
	
	/**
	 * Method used to check if the text box is filled out 
	 * @param txt
	 * @return true if it is filled
	 */
	public boolean isFilled(TextField txt) {
	    	if(!txt.getText().equals("")) {return true;}
	    	return false;
	}
	
	/**
	 * Method used to check if the birth date is filled out 
	 * @param txt
	 * @return true if it is filled
	 */ 
	public boolean isDateFilled(DatePicker date) {
    	if(date.getValue()!=null) {return true;}
    	return false;
    }
	
	/**
	 * Method used to check if the email text box is filled out 
	 * @param txt
	 * @return true if it is filled
	 */
    public boolean isEmailFilled(TextField str) {
    	Matcher mat = mailRestrictions.matcher(str.getText());
    	if(mat.matches()) {return true;}
    	return false;
    }
    
    /**
	 * Method used to check if the phone number text box is filled out 
	 * @param txt
	 * @return true if it is filled
	 */
    public boolean isPhoneFilled(TextField str) {
    	Matcher mat = phoneRestrictions.matcher(str.getText());
    	if(mat.matches()) {return true;}
    	return false;
    }
	
	/**
	 * Method used to add a person into when all of the information about them has been filled out and 
	 * the button ADD has been clicked
	 * @param event
	 */
	@FXML
    private void addPerson(ActionEvent event){
		if(isFilled(lastNameText)&&isFilled(firstNameText)&&isFilled(nickNameText)&&isFilled(phoneNumberText)&&isFilled(addressText)&&isFilled(emailAdressText)&&isDateFilled(birthDateText)) {
			if(isEmailFilled(emailAdressText)) {
				if(isPhoneFilled(phoneNumberText)) {
					Person person = new Person(lastNameText.getText(),firstNameText.getText(),nickNameText.getText(),phoneNumberText.getText(),addressText.getText(),emailAdressText.getText(),birthDateText.getValue());
					personDao.addPerson(person);
					warningText.setTextFill(Color.web("#008000"));
					warningText.setText(lastNameText.getText()+", "+firstNameText.getText()+" has been added");
					
					listPeople = personDao.listPersons();			
					while(index < listPeople.size()) {
						listPerson.add(listPeople.get(index).getLastName()+", "+listPeople.get(index).getFirstName()+" - "+listPeople.get(index).getPhone_number());
						index++;
					}
					
					selectPersonBar.setItems(listPerson);
					
				}
				else {
					warningText.setTextFill(Color.web("#ff0000"));
					warningText.setText("Please, enter a valid phone number (+XXxxxxxxxxx)");
				}
			}
			else {
				warningText.setTextFill(Color.web("#ff0000"));
				warningText.setText("Please, enter a valid email address (xxx@gmail.com)");
			}
		}
		else {
			warningText.setTextFill(Color.web("#ff0000"));
			warningText.setText("Please fill out the blanks");
		}
		
		}
	 
    /**
     * Method used to delete a person when the button DELETE is clicked
     * @param event
     */
    @FXML
    private void deletePerson(ActionEvent event){	
		String data="";
		
		if(selectPersonBar.getSelectionModel().getSelectedItem()!=null) {
			warningText.setText("");
	    	data = selectPersonBar.getSelectionModel().getSelectedItem().toString();
	    	data = data.substring(0,data.indexOf(","));
	    	try {
				personDao.deletePerson(personDao.findId(listPeople, data));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    	listPerson.clear();
	    	listPeople = personDao.listPersons();
	    	int i=0;
	    	while(i < listPeople.size()) {
				listPerson.add(listPeople.get(i).getLastName()+", "+listPeople.get(i).getFirstName()+" - "+listPeople.get(i).getPhone_number());
				i++;
			}
			selectPersonBar.setItems(listPerson);
			
			
	    	warningText.setTextFill(Color.web("#008000"));
	    	warningText.setText(lastNameText.getText()+", "+firstNameText.getText()+" has been deleted");
		}
		else {
			warningText.setTextFill(Color.web("#ff0000"));
            warningText.setText("Please, choose a person");
		}
		
	}
    
	/**
	 * Method used to update the information of the chosen person when the UPDATE button has been clicked
	 * @param event
	 */
    @FXML
    private void updatePerson(ActionEvent event){
		
		if(isFilled(lastNameText)&&isFilled(firstNameText)&&isFilled(nickNameText)&&isFilled(phoneNumberText)&&isFilled(addressText)&&isFilled(emailAdressText)&&isDateFilled(birthDateText)) {
			if(isEmailFilled(emailAdressText)) {
				if(isPhoneFilled(phoneNumberText)) {
					
					Person person = new Person(lastNameText.getText(),firstNameText.getText(),nickNameText.getText(),phoneNumberText.getText(),addressText.getText(),emailAdressText.getText(),birthDateText.getValue());
					
					try {
						personDao.updatePerson(this.personToDisplay.getIdPerson(), person);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					listPerson.clear();
			    	listPeople = personDao.listPersons();
			    	int i=0;
			    	while(i < listPeople.size()) {
						listPerson.add(listPeople.get(i).getLastName()+", "+listPeople.get(i).getFirstName()+" - "+listPeople.get(i).getPhone_number());
						i++;
					}
					selectPersonBar.setItems(listPerson);

					
					warningText.setTextFill(Color.web("#008000"));
					warningText.setText(lastNameText.getText()+", "+firstNameText.getText()+ " has been updated");
					
				}
				else {
					warningText.setTextFill(Color.web("#ff0000"));
					warningText.setText("Please, enter a valid phone number (e.g. : +33634297715)");
				}
			}
			else {
				warningText.setTextFill(Color.web("#ff0000"));
				warningText.setText("Please, enter a valid email address (e.g. : toto@gmail.com)");
			}
		}
		else {
			warningText.setTextFill(Color.web("#ff0000"));
			warningText.setText("Please fill out the blanks");
		}

	}
    
    /**
     * Method used to save the information of the people as VCards
     * @param event
     */
    private void saveAsVCard(ActionEvent event) {
		List<Person> listOfPersons = personDao.listPersons();
		int i=0;
		while(i<listOfPersons.size()) {
			try {
				VCard vcard = new VCard(listOfPersons.get(i));
				vcard.createFile();
				warningText.setTextFill(Color.web("#008000"));
				warningText.setText("Successfully saved as VCard");
			} catch (IOException e) {
				e.printStackTrace();
			}
			i++;
		}
		
		
	}
    
    // Change view to the FXML linked to the controller AllPersonController
    @FXML
  
    public void goToAllPersonList() throws IOException {
        Parent root=FXMLLoader.load(getClass().getResource("AllPerson.fxml"));
        Stage window=(Stage)showAllPersonButton.getScene().getWindow();
        window.setScene(new Scene(root,1000,1000));
        
    }

		
	
		
	
    

}
