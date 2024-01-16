package bean;

import java.util.ArrayList;

import org.apache.struts.action.ActionForm;

import model.Anagrafica;

public class Correntisti extends ActionForm {
	
	private ArrayList<Anagrafica> elencoCorrentisti;

	public ArrayList<Anagrafica> getElencoCorrentisti() {
		return elencoCorrentisti;
	}

	public void setElencoCorrentisti(ArrayList<Anagrafica> elencoCorrentisti) {
		this.elencoCorrentisti = elencoCorrentisti;
	}
	
	
}
