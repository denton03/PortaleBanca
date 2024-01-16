package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.ContoCorrente;

public class ContoCorrenteDAO {

	private ConnessioneDBPostgre connessione;
	private Connection con;
	
	public ContoCorrenteDAO() {
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
	private long getLastId() {
		String sql = "SELECT MAX(cod_cc) FROM bancaintesa.conti_correnti;";
		long id=0;
		try {
			PreparedStatement st = con.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				id = rs.getLong(1);				
			}
			st.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return id;
	}
	public long getLastIdPlus1() {
		String sql = "SELECT MAX(cod_cc) FROM bancaintesa.conti_correnti;";
		long id=0;
		try {
			PreparedStatement st = con.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				id = rs.getLong(1);				
			}
			st.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return id+1;
	}
	public boolean codCCExists(long codCC) {
		String sql = "select * from bancaintesa.conti_correnti where cod_cc=?";
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setLong(1, codCC);
			ResultSet rs = st.executeQuery();
			if(rs.next()) {
				return true;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public long insertContoCorrente(ContoCorrente contoCorrente) {
	    long codCC = contoCorrente.getCodCC();
	    if (!codCCExists(codCC)) {
	        String sql = "INSERT INTO bancaintesa.conti_correnti (iso, num_controllo, cin, abi, cab, cod_cc, cod_anag_cc, importo_saldo, importo_fido) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	        
	        try {
	            PreparedStatement st = con.prepareStatement(sql);
	            st.setString(1, contoCorrente.getIso());
	            st.setInt(2, contoCorrente.getNumControllo());
	            st.setString(3, contoCorrente.getCin());
	            st.setInt(4, contoCorrente.getAbi());
	            st.setInt(5, contoCorrente.getCab());
	            st.setLong(6, codCC);
	            st.setInt(7, contoCorrente.getCodAnagCC());
	            st.setBigDecimal(8, contoCorrente.getImportoSaldo());
	            st.setBigDecimal(9, contoCorrente.getImportoFido());

	            int rowsAffected = st.executeUpdate();

	            if (rowsAffected > 0) {
	                return codCC;
	            } else {
	                return 0;
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return 0;
	}
	
    public ContoCorrente getContoCorrenteByCodCC(long codCC) {
        String sql = "SELECT * FROM bancaintesa.conti_correnti WHERE cod_cc = ?";
        ContoCorrente contoCorrente = null;

        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setLong(1, codCC);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                contoCorrente = new ContoCorrente();
                contoCorrente.setIso(rs.getString("iso"));
                contoCorrente.setNumControllo(rs.getInt("num_controllo"));
                contoCorrente.setCin(rs.getString("cin"));
                contoCorrente.setAbi(rs.getInt("abi"));
                contoCorrente.setCab(rs.getInt("cab"));
                contoCorrente.setCodCC(rs.getLong("cod_cc"));
                contoCorrente.setCodAnagCC(rs.getInt("cod_anag_cc"));
                contoCorrente.setImportoSaldo(rs.getBigDecimal("importo_saldo"));
                contoCorrente.setImportoFido(rs.getBigDecimal("importo_fido"));
            }

            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return contoCorrente;
    }

    public ArrayList<ContoCorrente> getAllContiCorrenti() {
        String sql = "SELECT * FROM bancaintesa.conti_correnti";
        ArrayList<ContoCorrente> contiCorrenti = new ArrayList<>();

        try {
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                ContoCorrente contoCorrente = new ContoCorrente();
                contoCorrente.setIso(rs.getString("iso"));
                contoCorrente.setNumControllo(rs.getInt("num_controllo"));
                contoCorrente.setCin(rs.getString("cin"));
                contoCorrente.setAbi(rs.getInt("abi"));
                contoCorrente.setCab(rs.getInt("cab"));
                contoCorrente.setCodCC(rs.getLong("cod_cc"));
                contoCorrente.setCodAnagCC(rs.getInt("cod_anag_cc"));
                contoCorrente.setImportoSaldo(rs.getBigDecimal("importo_saldo"));
                contoCorrente.setImportoFido(rs.getBigDecimal("importo_fido"));
                contiCorrenti.add(contoCorrente);
            }

            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return contiCorrenti;
    }
    public ArrayList<Long> getAllCodCC() {
        String sql = "SELECT cod_cc FROM bancaintesa.conti_correnti";
        ArrayList<Long> codCC = new ArrayList<>();

        try {
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                ContoCorrente contoCorrente = new ContoCorrente();
                codCC.add(rs.getLong("cod_cc"));
            }

            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return codCC;
    }
    public ArrayList<ContoCorrente> getContiCorrentiByCodAnagCC(int codAnagCC) {
        String sql = "SELECT * FROM bancaintesa.conti_correnti WHERE cod_anag_cc = ?";
        ArrayList<ContoCorrente> contiCorrenti = new ArrayList<>();

        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, codAnagCC);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                ContoCorrente contoCorrente = new ContoCorrente();
                contoCorrente.setIso(rs.getString("iso"));
                contoCorrente.setNumControllo(rs.getInt("num_controllo"));
                contoCorrente.setCin(rs.getString("cin"));
                contoCorrente.setAbi(rs.getInt("abi"));
                contoCorrente.setCab(rs.getInt("cab"));
                contoCorrente.setCodCC(rs.getLong("cod_cc"));
                contoCorrente.setCodAnagCC(rs.getInt("cod_anag_cc"));
                contoCorrente.setImportoSaldo(rs.getBigDecimal("importo_saldo"));
                contoCorrente.setImportoFido(rs.getBigDecimal("importo_fido"));
                contiCorrenti.add(contoCorrente);
            }

            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return contiCorrenti;
    }

}
