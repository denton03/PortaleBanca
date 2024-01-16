package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ComuneDAO {
	ConnessioneDBPostgre connessione;
	Connection con;
	
	public ComuneDAO() {
		connessione = new ConnessioneDBPostgre();
		try {
			con = connessione.connetti();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public int getIdComune(String citta) {
		String sql="select id_comune from bancaintesa.comuni where descrizione=?";
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, citta);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				return rs.getInt("id_comune");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public ArrayList<String> getDescrizioneComuni(){
		ArrayList<String> comuni = new ArrayList<String>();
		String sql = "select descrizione from bancaintesa.comuni";
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				comuni.add(rs.getString("descrizione"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return comuni;
		
	}
}
