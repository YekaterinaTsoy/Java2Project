package db.daos;


import java.sql.SQLException;

import javax.sql.DataSource;

import org.sqlite.SQLiteDataSource;

/**
 * @author 	Julia RUIZ CARRERO, Carlos JIMÉNEZ GARCÍA, Yekaterina TSOY
 *
 */ 

class DataSourceFactory {

	private static SQLiteDataSource dataSource;
	

	private DataSourceFactory() {
		// This is a static class that should not be instantiated.
		// Here's a way to remember it when this class will have 2K lines and you come
		// back to it in 2 years
		throw new IllegalStateException("This is a static class that should not be instantiated");
	}

	/**
	 * @return a connection to the SQLite Database
	 * @throws SQLException 
	 * 
	 */
	public static DataSource getDataSource() throws SQLException {
		if (dataSource == null) {
			dataSource = new SQLiteDataSource();
			dataSource.setUrl("jdbc:sqlite:sqliteP.db");
			
			
		}
		return dataSource;
	}
}


