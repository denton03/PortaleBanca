package repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bean.BeanAnagrafica;
import model.Anagrafica;

public class AnagraficaDAO {
	private ConnessioneDBPostgre con;
	private Connection connection;
	
	public AnagraficaDAO() {
		con= new ConnessioneDBPostgre();
		try {
			connection=con.connetti();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public int save(Anagrafica anagrafica) {
    String sql = "INSERT INTO bancaintesa.anagrafica (cod_anag, cognome, nome, rag_soc, indirizzo, id_comune, sesso, data_nascita, luogo_nascita, cod_fiscale, partita_iva, telefono1, telefono2, email) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    int codAnag = getLastId() + 1;
    String cognome = anagrafica.getCognome();
    String nome = anagrafica.getNome();
    String ragSoc = anagrafica.getRagSoc();
    String indirizzo = anagrafica.getIndirizzo();
    int idComune = anagrafica.getIdComune();
    String sesso = anagrafica.getSesso();
    Date dataNascita = anagrafica.getDataNascita();
    String luogoNascita = anagrafica.getLuogoNascita();
    String codFiscale = anagrafica.getCodFiscale();
    String partitaIva = anagrafica.getPartitaIva();
    String telefono1 = anagrafica.getTelefono1();
    String telefono2 = anagrafica.getTelefono2();
    String email = anagrafica.getEmail();

    try {
        PreparedStatement st = connection.prepareStatement(sql);
        st.setInt(1, codAnag);
        st.setString(2, cognome);
        st.setString(3, nome);
        st.setString(4, ragSoc);
        st.setString(5, indirizzo);
        st.setInt(6, idComune);
        st.setString(7, sesso);
        st.setDate(8, new java.sql.Date(dataNascita.getTime())); 
        st.setString(9, luogoNascita);
        st.setString(10, codFiscale);
        st.setString(11, partitaIva);
        st.setString(12, telefono1);
        st.setString(13, telefono2);
        st.setString(14, email);

        int rowsAffected = st.executeUpdate();
        System.out.println(rowsAffected);
        if (rowsAffected > 0) {
            System.out.println("Anagrafica record inserted successfully.");
            return codAnag;  // Return the ID of the inserted record
        } else {
            System.out.println("Anagrafica record insertion failed.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return 0;
}

	private int getCodAnagByNomeCognome(String nome, String cognome) {
		String sql = "SELECT cod_anag FROM bancaintesa.anagrafica where nome=? and cognome=?;";
		int id=0;
		try {
			PreparedStatement st = connection.prepareStatement(sql);
			st.setString(1, nome);
			st.setString(2, cognome);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				id = rs.getInt(1);				
			}
			st.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return id;
	}
	private int getLastId() {
		String sql = "SELECT MAX(cod_anag) FROM bancaintesa.anagrafica;";
		int id=0;
		try {
			PreparedStatement st = connection.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				id = rs.getInt(1);				
			}
			st.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return id;
	}
	public int updateAnagrafica(Anagrafica anagrafica) {
        int idComune = getIdComuneByLuogoNascita(anagrafica.getLuogoNascita());
        System.out.println("updateAnagrafica idComune : "+idComune);
        System.out.println("updateAnagrafica luogodiNascita: "+anagrafica.getLuogoNascita());

        // Then, update Anagrafica table
        String sql = "UPDATE bancaintesa.anagrafica SET " +
                "cognome = ?, " +
                "nome = ?, " +
                "rag_soc = ?, " +
                "indirizzo = ?, " +
                "sesso = ?, " +
                "data_nascita = ?, " +
                "luogo_nascita = ?, " +
                "cod_fiscale = ?, " +
                "partita_iva = ?, " +
                "telefono1 = ?, " +
                "telefono2 = ?, " +
                "email = ? " +
                "WHERE cod_anag = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, anagrafica.getCognome());
            preparedStatement.setString(2, anagrafica.getNome());
            preparedStatement.setString(3, anagrafica.getRagSoc());
            preparedStatement.setString(4, anagrafica.getIndirizzo()); 
            preparedStatement.setString(5, anagrafica.getSesso());
            preparedStatement.setDate(6, new java.sql.Date(anagrafica.getDataNascita().getTime()));
            preparedStatement.setString(7, anagrafica.getLuogoNascita());
            preparedStatement.setString(8, anagrafica.getCodFiscale());
            preparedStatement.setString(9, anagrafica.getPartitaIva());
            preparedStatement.setString(10, anagrafica.getTelefono1());
            preparedStatement.setString(11, anagrafica.getTelefono2());
            preparedStatement.setString(12, anagrafica.getEmail());
            preparedStatement.setInt(13, anagrafica.getCodAnag());

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();  
            return 0; 
        }
    }
	
	public int deleteAnagraficaByCodAnag(int codAnag) {
		String sqlConto = "delete from bancaintesa.conti_correnti where cod_anag_cc=?";
		PreparedStatement stConto;
		int rsConto=0;
		try {
			stConto = connection.prepareStatement(sqlConto);
			stConto.setInt(1, codAnag);
			rsConto = stConto.executeUpdate();
			System.out.println("DAO DELETE FROM bancaintesa.conti_correnti where cod_anag_cc="+codAnag);
			System.out.println("DAO result: "+rsConto);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		
		String sql = "delete from bancaintesa.anagrafica where cod_anag=?";
		PreparedStatement st;
		int rs=0;
		try {
			st = connection.prepareStatement(sql);
			st.setInt(1, codAnag);
			rs = st.executeUpdate();
			System.out.println("DAO DELETE FROM bancaintesa.anagrafica where cod_anag="+codAnag);
			System.out.println("DAO result: "+rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(rsConto>0&&rs>0) {
			return 3;
		}else if(rsConto>0&&rs<=0){
			return 2;
		}else if(rsConto<=0&&rs>0) {
			return 1;
		}
		return 0;
	}
	public Anagrafica getAnagraficaByCodAnag(int codAnag) {
		Anagrafica anagrafica=null;
		String sql = "select cognome,nome,cod_anag,rag_soc, indirizzo,a.id_comune as idComune,sesso,data_nascita,luogo_nascita,cod_fiscale,partita_iva,telefono1,telefono2,email,c.descrizione AS citta from bancaintesa.anagrafica a inner join bancaintesa.comuni c on a.id_comune=c.id_comune  where a.cod_anag=? ";
		String sqlConto = "select conti.cod_cc as codCC from bancaintesa.conti_correnti conti inner join bancaintesa.anagrafica a on a.cod_anag = conti.cod_anag_cc where a.cod_anag=?";
		try {
			long codCC = 0;
			PreparedStatement stConto = connection.prepareStatement(sqlConto);
			stConto.setInt(1, codAnag);
			ResultSet rsConto = stConto.executeQuery();
			if(rsConto.next()) {
				long codCCValue = rsConto.getLong("codCC");
	            if (!rsConto.wasNull()) {
	                codCC = codCCValue;
	            }
			}

			
			PreparedStatement st = connection.prepareStatement(sql);
			st.setInt(1, codAnag);
			ResultSet rs = st.executeQuery();
			
			
			while(rs.next()) {
				System.out.println();
				String cognome = rs.getString("cognome");
				String nome = rs.getString("nome");
				String ragSoc=rs.getString("rag_soc");
				String indirizzo = rs.getString("indirizzo");
				int idComune = rs.getInt("idComune");
				String sesso = rs.getString("sesso");
				Date dataNascita = rs.getDate("data_nascita");
				String luogoNascita = rs.getString("luogo_nascita");
				String codFiscale = rs.getString("cod_fiscale");
				String partitaIva = rs.getString("partita_iva");
				String telefono1 = rs.getString("telefono1");
				String telefono2 = rs.getString("telefono2");
				String email = rs.getString("email");
				String citta = rs.getString("citta");
				anagrafica=new Anagrafica(codAnag, cognome, nome, ragSoc, indirizzo, idComune, sesso, dataNascita, luogoNascita, codFiscale, partitaIva, telefono1, telefono2, email, citta, codCC);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return anagrafica;
	}
	public Anagrafica getAnagraficaByCodCC(long codCC) {
		Anagrafica anagrafica=null;
		String sql = "select cod_anag,cognome,nome,rag_soc, indirizzo,a.id_comune,sesso,data_nascita,luogo_nascita,cod_fiscale,partita_iva,telefono1,telefono2,email,c.descrizione AS citta from bancaintesa.anagrafica a inner join bancaintesa.conti_correnti conti on a.cod_anag = conti.cod_anag_cc inner join bancaintesa.comuni c on a.id_comune=c.id_comune where conti.cod_cc=? ";
		try {
			PreparedStatement st = connection.prepareStatement(sql);
			st.setLong(1, codCC);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				int codAnag = rs.getInt("cod_anag");
				String cognome = rs.getString("cognome");
				String nome = rs.getString("nome");
				String ragSoc=rs.getString("rag_soc");
				String indirizzo = rs.getString("indirizzo");
				int idComune = rs.getInt("id_comune");
				String sesso = rs.getString("sesso");
				Date dataNascita = rs.getDate("data_nascita");
				String luogoNascita = rs.getString("luogo_nascita");
				String codFiscale = rs.getString("cod_fiscale");
				String partitaIva = rs.getString("partita_iva");
				String telefono1 = rs.getString("telefono1");
				String telefono2 = rs.getString("telefono2");
				String email = rs.getString("email");
				String citta = rs.getString("citta");
				anagrafica=new Anagrafica(codAnag, cognome, nome, ragSoc, indirizzo, idComune, sesso, dataNascita, luogoNascita, codFiscale, partitaIva, telefono1, telefono2, email, citta, codCC);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return anagrafica;
	}
	public Anagrafica getAnagraficaByCognomeNome(String cognome,String nome){
		System.out.println("DAO nome: "+nome+" cognome: "+cognome);
		Anagrafica anagrafica = null;
		String sql = "select cod_anag,rag_soc, indirizzo,a.id_comune as idComune,sesso,data_nascita,luogo_nascita,cod_fiscale,partita_iva,telefono1,telefono2,email,c.descrizione AS citta from bancaintesa.anagrafica a inner join bancaintesa.comuni c on a.id_comune=c.id_comune  where a.cognome=? and a.nome=?";
		String sqlConto = "select conti.cod_cc as codCC from bancaintesa.conti_correnti conti inner join bancaintesa.anagrafica a on a.cod_anag = conti.cod_anag_cc where a.cod_anag=?";
		try {
			PreparedStatement st = connection.prepareStatement(sql);
			st.setString(1, cognome);
			st.setString(2, nome);
			ResultSet rs = st.executeQuery();

			
			while(rs.next()) {
				int codAnag=rs.getInt("cod_anag");
				long codCC = 0;
				PreparedStatement stConto = connection.prepareStatement(sqlConto);
				stConto.setInt(1, codAnag);
				ResultSet rsConto = stConto.executeQuery();
				if(rsConto.next()) {
					long codCCValue = rsConto.getLong("codCC");
		            if (!rsConto.wasNull()) {
		                codCC = codCCValue;
		            }
				}

				String ragSoc=rs.getString("rag_soc");
				String indirizzo = rs.getString("indirizzo");
				int idComune = rs.getInt("idComune");
				String sesso = rs.getString("sesso");
				Date dataNascita = rs.getDate("data_nascita");
				String luogoNascita = rs.getString("luogo_nascita");
				String codFiscale = rs.getString("cod_fiscale");
				String partitaIva = rs.getString("partita_iva");
				String telefono1 = rs.getString("telefono1");
				String telefono2 = rs.getString("telefono2");
				String email = rs.getString("email");
				String citta = rs.getString("citta");

				
				anagrafica=new Anagrafica(codAnag, cognome, nome, ragSoc, indirizzo, idComune, sesso, dataNascita, luogoNascita, codFiscale, partitaIva, telefono1, telefono2, email, citta, codCC);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return anagrafica;
	}
	public ArrayList<Anagrafica> getAllAnagrafica(){
		ArrayList<Anagrafica> anagrafiche = new ArrayList<Anagrafica>();
		String sqlConto = "select conti.cod_cc as codCC from bancaintesa.conti_correnti conti inner join bancaintesa.anagrafica a on a.cod_anag = conti.cod_anag_cc where a.cod_anag=?";
		String sql = "select cod_anag, cognome, nome, rag_soc, indirizzo, a.id_comune as idComune, sesso, data_nascita, luogo_nascita, cod_fiscale, partita_iva, telefono1, telefono2, email, c.descrizione as citta from bancaintesa.anagrafica a inner join bancaintesa.comuni c on a.id_comune=c.id_comune order by cod_anag";
		try {
			
			
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				int codAnag = rs.getInt("cod_anag");
	            
				long codCC = 0;
				PreparedStatement stConto = connection.prepareStatement(sqlConto);
				stConto.setInt(1, codAnag);
				ResultSet rsConto = stConto.executeQuery();
				if(rsConto.next()) {
					long codCCValue = rsConto.getLong("codCC");
		            if (!rsConto.wasNull()) {
		                codCC = codCCValue;
		            }
				}
				String cognome = rs.getString("cognome");
	            String nome = rs.getString("nome");
	            String ragSoc = rs.getString("rag_soc");
	            String indirizzo = rs.getString("indirizzo");
	            String sesso = rs.getString("sesso");
	            Date dataNascita = rs.getDate("data_nascita");
	            int idComune = rs.getInt("idComune");
	            String luogoNascita = rs.getString("luogo_nascita");
	            String codFiscale = rs.getString("cod_fiscale");
	            String partitaIva = rs.getString("partita_iva");
	            String telefono1 = rs.getString("telefono1");
	            String telefono2 = rs.getString("telefono2");
	            String email = rs.getString("email");
	            String citta = rs.getString("citta");
				anagrafiche.add(new Anagrafica(codAnag, cognome, nome, ragSoc, indirizzo, idComune, sesso, dataNascita, luogoNascita, codFiscale, partitaIva, telefono1, telefono2, email, citta, codCC));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return anagrafiche;
	}
	
	public ArrayList<String> getCognomi(){
		ArrayList<String> cognomi = new ArrayList<String>();
		String sql = "select distinct cognome from bancaintesa.anagrafica order by cognome";
		try {
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				cognomi.add(rs.getString("cognome"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return cognomi;
	}

	public ArrayList<String> getNomi(String cognome) {
		ArrayList<String> nomi = new ArrayList<String>();
		String sql = "select nome from bancaintesa.anagrafica where cognome=?";
		
		try {
			PreparedStatement st = connection.prepareStatement(sql);
			st.setString(1, cognome);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				nomi.add(rs.getString(1));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return nomi;
	}
    private int getIdComuneByLuogoNascita(String luogoNascita) {
        String sql = "SELECT id_comune FROM bancaintesa.comuni WHERE descrizione = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, luogoNascita);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("id_comune");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();  
        }

        return 0; 
    }
}
