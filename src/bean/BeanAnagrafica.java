package bean;

import java.sql.Date;

import org.apache.struts.action.ActionForm;

public class BeanAnagrafica extends ActionForm {
	
	private int codAnag;
	private String cognome;
	private String nome;
	private String ragSoc;
	private String indirizzo;
	private int idComune;
	private String sesso;
	private String dataNascita;
	private String luogoNascita;
	private String codFiscale;
	private String partitaIva;
	private String telefono1;
	private String telefono2;
	private String email;
	private String citta;
	private long codCC;
	
	public BeanAnagrafica() {
	}
	
	public int getCodAnag() {
		return codAnag;
	}
	public void setCodAnag(int codAnag) {
		this.codAnag = codAnag;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getRagSoc() {
		return ragSoc;
	}
	public void setRagSoc(String ragSoc) {
		this.ragSoc = ragSoc;
	}
	public String getIndirizzo() {
		return indirizzo;
	}
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}
	public int getIdComune() {
		return idComune;
	}
	public void setIdComune(int idComune) {
		this.idComune = idComune;
	}
	
	public String getSesso() {
		return sesso;
	}
	public void setSesso(String sesso) {
		this.sesso = sesso;
	}
	public String getDataNascita() {
		return dataNascita;
	}
	public void setDataNascita(String dataNascita) {
		this.dataNascita = dataNascita;
	}
	public String getLuogoNascita() {
		return luogoNascita;
	}
	public void setLuogoNascita(String luogoNascita) {
		this.luogoNascita = luogoNascita;
	}
	public String getCodFiscale() {
		return codFiscale;
	}
	public void setCodFiscale(String codFiscale) {
		this.codFiscale = codFiscale;
	}
	public String getPartitaIva() {
		return partitaIva;
	}
	public void setPartitaIva(String partitaIva) {
		this.partitaIva = partitaIva;
	}
	public String getTelefono1() {
		return telefono1;
	}
	public void setTelefono1(String telefono1) {
		this.telefono1 = telefono1;
	}
	public String getTelefono2() {
		return telefono2;
	}
	public void setTelefono2(String telefono2) {
		this.telefono2 = telefono2;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getCitta() {
		return citta;
	}

	public void setCitta(String citta) {
		this.citta = citta;
	}

	public long getCodCC() {
		return codCC;
	}

	public void setCodCC(long codCC) {
		this.codCC = codCC;
	}
	
	
}
