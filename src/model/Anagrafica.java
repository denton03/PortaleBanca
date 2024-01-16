package model;

import java.sql.Date;

public class Anagrafica {
	private int codAnag;
	private String cognome;
	private String nome;
	private String ragSoc;
	private String indirizzo;
	private int idComune;
	private String sesso;
	private Date dataNascita;
	private String luogoNascita;
	private String codFiscale;
	private String partitaIva;
	private String telefono1;
	private String telefono2;
	private String email;
	private String citta;
	private long codCC;
	
	public Anagrafica() {
		
	}
	
	public Anagrafica(int codAnag, String cognome, String nome, String ragSoc, String indirizzo, int idComune,
			String sesso, Date dataNascita, String luogoNascita, String codFiscale, String partitaIva, String telefono1,
			String telefono2, String email, String citta, long codCC) {
		this.codAnag = codAnag;
		this.cognome = cognome;
		this.nome = nome;
		this.ragSoc = ragSoc;
		this.indirizzo = indirizzo;
		this.idComune = idComune;
		this.sesso = sesso;
		this.dataNascita = dataNascita;
		this.luogoNascita = luogoNascita;
		this.codFiscale = codFiscale;
		this.partitaIva = partitaIva;
		this.telefono1 = telefono1;
		this.telefono2 = telefono2;
		this.email = email;
		this.citta = citta;
		this.codCC = codCC;
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
	public Date getDataNascita() {
		return dataNascita;
	}
	public void setDataNascita(Date dataNascita) {
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

	@Override
	public String toString() {
		return "Anagrafica [codAnag=" + codAnag + ", cognome=" + cognome + ", nome=" + nome + ", ragSoc=" + ragSoc
				+ ", indirizzo=" + indirizzo + ", idComune=" + idComune + ", sesso=" + sesso + ", dataNascita="
				+ dataNascita + ", luogoNascita=" + luogoNascita + ", codFiscale=" + codFiscale + ", partitaIva="
				+ partitaIva + ", telefono1=" + telefono1 + ", telefono2=" + telefono2 + ", email=" + email + ", citta="
				+ citta + ", codCC=" + codCC + "]";
	}
	
}
