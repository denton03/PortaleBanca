package service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import bean.BeanAnagrafica;
import model.Anagrafica;
import repository.AnagraficaDAO;
import repository.ComuneDAO;

public class AnagraficaService {
    private AnagraficaDAO dao;
    private ComuneDAO comuneDAO;

    public AnagraficaService() {
        this.dao = new AnagraficaDAO();
        comuneDAO = new ComuneDAO();
    }
    public int save(BeanAnagrafica bean) {
    	String dataNascitaString = bean.getDataNascita();
    	java.sql.Date dataNascita = java.sql.Date.valueOf(dataNascitaString);
    	String citta=primaLetteraMaiuscola(bean.getCitta());
    	String codFiscale = bean.getCodFiscale().toUpperCase();
    	String cognome="";
    	String nome="";
    	String ragSoc="";
    	String indirizzo="";
    	String luogoNascita="";
    	try {
			cognome= new String(bean.getCognome().getBytes("ISO-8859-1"), "UTF-8");
			nome= new String(bean.getNome().getBytes("ISO-8859-1"), "UTF-8");
			ragSoc=new String(bean.getRagSoc().getBytes("ISO-8859-1"), "UTF-8");
			indirizzo= new String(bean.getIndirizzo().getBytes("ISO-8859-1"), "UTF-8");
			luogoNascita=new String(bean.getLuogoNascita().getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	Anagrafica anagrafica = new Anagrafica(0, primaLetteraMaiuscola(cognome), primaLetteraMaiuscola(nome), primaLetteraMaiuscola(ragSoc), primaLetteraMaiuscola(indirizzo), comuneDAO.getIdComune(citta), bean.getSesso(), dataNascita, primaLetteraMaiuscola(luogoNascita), codFiscale, bean.getPartitaIva(), bean.getTelefono1(), bean.getTelefono2(), bean.getEmail(), citta, bean.getCodCC());
    	
    	return dao.save(anagrafica);
    }
    public ArrayList<String> getCognomi() {
        return dao.getCognomi();
    }
    public ArrayList<String> getNomi(String cognome){
    	return dao.getNomi(cognome);
    }
    public Anagrafica getAnagraficaByCodAnag(int codAnag) {
    	return dao.getAnagraficaByCodAnag(codAnag);
    }
    public Anagrafica getAnagraficaByCognomeNome(String cognome, String nome) {
    	return dao.getAnagraficaByCognomeNome(cognome, nome);
    }
    public ArrayList<Anagrafica> getAllAnagrafica(){
    	return dao.getAllAnagrafica();
    }
    public int updateAnagrafica(Anagrafica anagrafica) {
    	return dao.updateAnagrafica(anagrafica);
    }
    public int deleteAnagraficaByCodAnag(int codAnag) {
    	return dao.deleteAnagraficaByCodAnag(codAnag);
    }
    public Anagrafica getAnagraficaByCodCC(long codCC) {
    	return dao.getAnagraficaByCodCC(codCC);
    }
    private String primaLetteraMaiuscola(String str) {
        if (str == null || str.isEmpty()) {
            return str; // Return the input string if it's null or empty
        }

        return Character.toUpperCase(str.charAt(0)) + str.substring(1);
    }
}