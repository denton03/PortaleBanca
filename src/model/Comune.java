package model;

public class Comune {
	private int id_comune;
	private String descrizione;
	
	public Comune(int id_comune, String descrizione) {
		this.id_comune = id_comune;
		this.descrizione = descrizione;
	}
	
	public int getId_comune() {
		return id_comune;
	}
	public void setId_comune(int id_comune) {
		this.id_comune = id_comune;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	
}
