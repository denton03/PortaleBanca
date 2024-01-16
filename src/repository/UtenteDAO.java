package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UtenteDAO {
	
	private ConnessioneDBPostgre connessione;
	private Connection con;
	
	public UtenteDAO() {
		this.connessione=new ConnessioneDBPostgre();
		try {
			con=connessione.connetti();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int getUtenteByNomeUserPassword(String nomeUser, String password) {
		String queryUsername = "select * from bancaintesa.utenti_admin where nomeuser=?";
		PreparedStatement st;
		try {
			st = con.prepareStatement(queryUsername);
			st.setString(1, nomeUser);
			ResultSet rsUsername = st.executeQuery();
			if(!(rsUsername.next())) {
				return 1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 3;
		}
		String queryPassword = "select * from bancaintesa.utenti_admin where password=?";
		try {
			st = con.prepareStatement(queryPassword);
			st.setString(1, password);
			ResultSet rsUsername = st.executeQuery();
			if(!(rsUsername.next())) {
				return 2;
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return 3;
		}
		return 0;
	}
}
