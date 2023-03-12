package isen.project.util;


import db.entities.Person;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.Callback;
/**
 * @author 	Julia RUIZ CARRERO, Carlos JIMÉNEZ GARCÍA, Yekaterina TSOY
 *
 */
public class EmailValueFactory implements Callback<CellDataFeatures<Person, String>, ObservableValue<String>> {

@Override
	public ObservableValue<String> call(CellDataFeatures<Person, String> cellData) {
	return new SimpleStringProperty(cellData.getValue().getEmail_address());
	}
}