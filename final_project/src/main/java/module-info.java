module isen.project {
    requires javafx.controls;
    requires javafx.fxml;
	requires java.sql;
	requires org.xerial.sqlitejdbc;
	requires junit;
	requires javafx.base;

	opens isen.project to javafx.xml;
    opens isen.project.view to javafx.fxml;
    exports isen.project;
}
