package isen.project.util;

import java.sql.Date;

import db.entities.Person;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.Callback;
/**
 * @author 	Julia RUIZ CARRERO, Carlos JIMÉNEZ GARCÍA, Yekaterina TSOY
 *
 */
public class BirthValueFactory
implements Callback<CellDataFeatures<Person, Date>, ObservableValue<Date>> {

	@Override
	public ObservableValue<Date> call(CellDataFeatures<Person, Date> cellData) {
	return new SimpleObjectProperty<Date>(cellData.getValue().getBirth_date());
	}
}
