package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnessioneDBPostgre {
	

	private String serverName = "192.244.44.55";
	private String portNumber = "1111";
	private String sid = "dbone";
	private String userName = "dbone";
	private String password = "password";

	public Connection connetti () throws ClassNotFoundException, SQLException {
		
		//carica il file di classe del driver per il collegamento  al database con il ponte Odbc
		Class.forName("org.postgresql.Driver");
		String url = "jdbc:postgresql://" + serverName + ":" + portNumber + "/" +  sid+ "?charSet=UTF-8";
		
		//apri la connessione con il database
		Connection con = DriverManager.getConnection(url,userName,password);
		System.out.println("\nConnessione avvenuta\n" + "*** url: " + url + " ***\n");
		return con;
		
	}
}
