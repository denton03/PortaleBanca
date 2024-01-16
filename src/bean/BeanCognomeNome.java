package bean;

import org.apache.struts.action.ActionForm;

public class BeanCognomeNome extends ActionForm{
	private String cognome;
	private String nome;

	public BeanCognomeNome() {
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
	
	
}
