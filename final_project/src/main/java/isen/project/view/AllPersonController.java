package isen.project.view;

import java.io.IOException;
import java.sql.Date;

import db.daos.PersonDao;
import db.entities.Person;
import isen.project.service.PersonService;
import isen.project.util.*;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
/**
 * @author 	Julia RUIZ CARRERO, Carlos JIMÉNEZ GARCÍA, Yekaterina TSOY
 *
 */
public class AllPersonController {
	
	 @FXML
	private Button backButton;
	
    @FXML
    private TableView <Person> personTable;
    
    @FXML
    private TableColumn<Person, String> lastNameColumn;

    @FXML
    private TableColumn<Person, String> firstNameColumn;

    @FXML
    private TableColumn<Person, Date> birthDateColumn;


    @FXML
    private TableColumn<Person, String> phoneNumberColumn;

    @FXML
    private TableColumn<Person, String> addressColumn;

    @FXML
    private TableColumn<Person, String> nickNameColumn;

    @FXML
    private TableColumn<Person, String> emailAddressColumn;
    
    
    @FXML
    private TextField searchField;
    
    // Refresh the table with contacts and clear selection
    @FXML
    public void refreshPersonTable(){
        // Contact table refresh
        personTable.refresh();
        personTable.getSelectionModel().clearSelection();

    }

    // Populate list with contacts inside the database
    @FXML
    public void populatePersonTable(){
    	personTable.setItems(FXCollections.observableArrayList(PersonDao.listPersons()));
        refreshPersonTable();
    }
 // Method called on load of the controller
    @FXML
    private void initialize(){
        // Setting up the columns
        addressColumn.setCellValueFactory(new AddressValueFactory());
        birthDateColumn.setCellValueFactory(new BirthValueFactory());
        firstNameColumn.setCellValueFactory(new FirstNameValueFactory());
        emailAddressColumn.setCellValueFactory(new EmailValueFactory());
        lastNameColumn.setCellValueFactory(new LastNameValueFactory());
        nickNameColumn.setCellValueFactory(new NicknameValueFactory());
        phoneNumberColumn.setCellValueFactory(new PhoneValueFactory());

        populatePersonTable();

    
   
  //Initial search list
        searchField.textProperty().addListener((observable,oldValue,newValue)->{
            personTable.setItems(PersonService.getPersons().filtered(person -> {
                    //If no search value is entered then it displays everything
                    if(newValue.isEmpty() || newValue.isBlank() || newValue==null) {
                        return true;
                    }

                    String searchKeyword = newValue.toLowerCase();
                    if(person.getLastName().toLowerCase().contains(searchKeyword)) {
                        return true; //Found a match in Lastname
                    } else if(person.getFirstName().toLowerCase().contains(searchKeyword)) {
                        return true; //Found a match in Firstname
                    }else if(person.getNickName().toLowerCase().contains(searchKeyword)) {
                        return true; //Found a match in Nickname
                    }else if(person.getPhone_number().toLowerCase().contains(searchKeyword)) {
                        return true; //Found a match in PhoneNumber
                    }else {
                        return false; //no match found
                    }
                })
            );
        refreshPersonTable();

        }
        		);
    }
    public void goToFirstView() throws IOException {
        Parent root=FXMLLoader.load(getClass().getResource("firstView.fxml"));
        Stage window=(Stage)backButton.getScene().getWindow();
        window.setScene(new Scene(root,600,600));
        
    }

}
